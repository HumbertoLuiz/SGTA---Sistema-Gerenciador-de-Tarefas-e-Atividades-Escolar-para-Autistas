package br.com.nofrontier.sgetea.api.v1.services;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nofrontier.sgetea.api.v1.dtos.responses.UserResponse;
import br.com.nofrontier.sgetea.core.utils.SecurityUtils;

@Service
public class ApiMeService {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private SecurityUtils securityUtils;

    public UserResponse getLoggedUser() {
        var userLogged= securityUtils.getLoggedUser();
        return mapper.map(userLogged, UserResponse.class);
    }

}
