package io.starfish.simplifiedpaymentgateway.modules.user.login;

import jakarta.validation.Valid;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
class LoginUserController {

    private final LoginUserFacade service;

    LoginUserController(LoginUserFacade service) {
        this.service = service;
    }

    @PostMapping("/auth/login")
    ResponseEntity<LoginUserResponse> login(@Valid @RequestBody LoginUserDto userDto) {
        LoginUserResponse response = service.login(userDto);
        return ResponseEntity
                .status(HttpStatus.OK)
                .header(HttpHeaders.AUTHORIZATION, response.token())
                .body(response);
    }
}
