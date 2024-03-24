package io.starfish.simplifiedpaymentgateway.integrationtests.helpers;

import io.starfish.simplifiedpaymentgateway.modules.user.register.RegisterUserDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

@Service
public class RegisterUserHelper {

    private final String REGISTER_USER_URL = "/api/v1/register";

    public String getJwtToken(WebTestClient webTestClient) {
        RegisterUserDto user = new RegisterUserDto("testuser", "TestPassword1!");
        String jwt = webTestClient.post()
                .uri(REGISTER_USER_URL)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), RegisterUserDto.class)
                .exchange()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        return String.format("Bearer %s", jwt);
    }

    public void registerUser(WebTestClient webTestClient, String name, String password) {
        RegisterUserDto user = new RegisterUserDto(name, password);
        webTestClient.post()
                .uri(REGISTER_USER_URL)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), RegisterUserDto.class)
                .exchange();
    }
}
