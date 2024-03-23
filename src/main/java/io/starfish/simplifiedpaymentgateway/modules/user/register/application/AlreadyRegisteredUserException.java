package io.starfish.simplifiedpaymentgateway.modules.user.register.application;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class AlreadyRegisteredUserException extends RuntimeException {

    public AlreadyRegisteredUserException(String name) {
        super(String.format("User %s already registered!", name));
    }
}
