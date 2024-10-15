package br.com.nofrontier.sgetea.core.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import br.com.nofrontier.sgetea.core.exceptions.UserNotFoundException;
import br.com.nofrontier.sgetea.core.models.User;
import br.com.nofrontier.sgetea.core.repository.UserRepository;

@Component
public class SecurityUtils {

    @Autowired
    private UserRepository userRepository;

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public String getEmailLoggedUser() {
        return getAuthentication().getName();
    }

    public User getLoggedUser() {
        var email= getEmailLoggedUser();
        var message= String.format("User with email %s not found", email);
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserNotFoundException(message));

    }
}
