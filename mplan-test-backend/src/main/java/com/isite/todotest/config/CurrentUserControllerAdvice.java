package com.isite.todotest.config;

import com.isite.todotest.registration.UserEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;


@ControllerAdvice
public class CurrentUserControllerAdvice {

    @ModelAttribute
    public UserEntity currentUser(Authentication authentication) {
        if (authentication != null &&
                authentication.getPrincipal() != null &&
                authentication.getPrincipal() instanceof UserEntity
                ) {
            return (UserEntity) authentication.getPrincipal();
        }
        return null;
    }
}
