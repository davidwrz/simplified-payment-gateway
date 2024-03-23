package io.starfish.simplifiedpaymentgateway.security;

import io.starfish.simplifiedpaymentgateway.modules.user.register.application.RegisterUserFacade;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomUserDetailsService implements UserDetailsService {

    private final RegisterUserFacade registerUserFacade;

    CustomUserDetailsService(RegisterUserFacade registerUserFacade) {
        this.registerUserFacade = registerUserFacade;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
        return registerUserFacade
                .findUserByName(name)
                .orElseThrow(() -> new UserNotFoundException(name));
    }

    private static class UserNotFoundException extends RuntimeException {
        public UserNotFoundException(String name) {
            super(String.format("User %s not found!", name));
        }
    }
}
