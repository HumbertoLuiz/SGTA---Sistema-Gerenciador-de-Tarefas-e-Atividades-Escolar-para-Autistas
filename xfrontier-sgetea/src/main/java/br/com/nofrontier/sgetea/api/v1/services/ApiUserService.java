package br.com.nofrontier.sgetea.api.v1.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.FieldError;

import br.com.nofrontier.sgetea.api.v1.dtos.requests.UserRequest;
import br.com.nofrontier.sgetea.api.v1.dtos.responses.TokenResponse;
import br.com.nofrontier.sgetea.api.v1.dtos.responses.UserRegisterResponse;
import br.com.nofrontier.sgetea.api.v1.dtos.responses.UserResponse;
import br.com.nofrontier.sgetea.core.exceptions.PasswordDoesntMatchException;
import br.com.nofrontier.sgetea.core.models.User;
import br.com.nofrontier.sgetea.core.publishers.NewUserPublisher;
import br.com.nofrontier.sgetea.core.repository.UserRepository;
import br.com.nofrontier.sgetea.core.services.storage.adapters.StorageService;
import br.com.nofrontier.sgetea.core.services.token.adapters.TokenService;
import br.com.nofrontier.sgetea.core.validators.UserValidator;

@Service
public class ApiUserService {

    @Autowired
    private UserRepository repository;

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserValidator validator;

    @Autowired
    private StorageService storageService;

    @Autowired
    private NewUserPublisher newUserPublisher;

    public UserResponse save(UserRequest request) {
        validatePasswordConfirmation(request);
        var userToSave= mapper.map(request, User.class);
        validator.validate(userToSave);
        var passwordEncrypted= passwordEncoder.encode(userToSave.getPassword());
        userToSave.setPassword(passwordEncrypted);
        var documentPicture= storageService.save(request.getDocumentPicture());
        userToSave.setDocumentPicture(documentPicture);

        var userSaved= repository.save(userToSave);
        newUserPublisher.publish(userSaved);
        var response= mapper.map(userSaved, UserRegisterResponse.class);
        var tokenResponse= generateTokenResponse(response);
        response.setToken(tokenResponse);
        return response;
    }

    private void validatePasswordConfirmation(UserRequest request) {
        var password= request.getPassword();
        var passwordConfirmation= request.getPasswordConfirmation();
        if (!password.equals(passwordConfirmation)) {
            var message= "the two password fields do not match";
            var fieldError= new FieldError(request.getClass().getName(), "passwordConfirmation",
                request.getPasswordConfirmation(), false, null, null, message);
            throw new PasswordDoesntMatchException(message, fieldError);
        }
    }

    private TokenResponse generateTokenResponse(UserRegisterResponse response) {
        var token= tokenService.generateAccessToken(response.getEmail());
        var refresh= tokenService.generateRefreshToken(response.getEmail());
        var tokenResponse= new TokenResponse(token, refresh);
        return tokenResponse;
    }
}
