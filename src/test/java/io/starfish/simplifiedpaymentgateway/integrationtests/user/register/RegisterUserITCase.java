package io.starfish.simplifiedpaymentgateway.integrationtests.user.register;

import io.starfish.simplifiedpaymentgateway.modules.user.register.RegisterUserDto;
import io.starfish.simplifiedpaymentgateway.security.JWTUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.http.HttpHeaders.AUTHORIZATION;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class RegisterUserITCase {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private JWTUtil jwtUtil;

    private static final String REGISTER_USER_URL = "/api/v1/register";
    private static final String USER_NAME = "testuser";
    private static final String USER_PASSWORD = "Testpassword1!";

    @Test
    @Sql(scripts = {"/db/integrationtests/deleteData.sql"}, executionPhase = AFTER_TEST_METHOD)
    void shouldRegisterUser() {
        RegisterUserDto user = new RegisterUserDto(USER_NAME, USER_PASSWORD);

        String jwt = webTestClient.post()
                .uri(REGISTER_USER_URL)
                .accept(MediaType.APPLICATION_JSON)
                .body(Mono.just(user), RegisterUserDto.class)
                .exchange()
                .returnResult(Void.class)
                .getResponseHeaders()
                .get(AUTHORIZATION)
                .get(0);

        assertThat(jwtUtil.isTokenValid(jwt, USER_NAME)).isTrue();
    }
}
