package io.starfish.simplifiedpaymentgateway.integrationtests.user.login;

import io.starfish.simplifiedpaymentgateway.integrationtests.helpers.RegisterUserHelper;
import io.starfish.simplifiedpaymentgateway.modules.user.login.LoginUserDto;
import io.starfish.simplifiedpaymentgateway.modules.user.login.LoginUserResponse;
import io.starfish.simplifiedpaymentgateway.security.JWTUtil;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class LoginUserITCase {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RegisterUserHelper registerUserHelper;
    @Autowired
    private JWTUtil jwtUtil;

    private static final String LOGIN_USER_URL = "/api/v1/auth/login";
    private static final String USER_NAME = "testuser";
    private static final String USER_PASSWORD = "Testpassword1!";

    @BeforeEach
    void registerUser() {
        registerUserHelper.registerUser(webTestClient, USER_NAME, USER_PASSWORD);
    }

    @Test
    @Sql(scripts = {"/db/integrationtests/deleteData.sql"}, executionPhase = AFTER_TEST_METHOD)
    void shouldLoginWhenUserAndPasswordAreCorrect() {
        LoginUserDto loginUserDto = new LoginUserDto(USER_NAME, USER_PASSWORD);

        LoginUserResponse loginUserResponse = webTestClient.post()
                .uri(LOGIN_USER_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginUserDto), LoginUserDto.class)
                .exchange()
                .expectStatus().isOk()
                .expectBody(new ParameterizedTypeReference<LoginUserResponse>() {
                }).returnResult()
                .getResponseBody();

        assertThat(jwtUtil.isTokenValid(loginUserResponse.token(), loginUserDto.name())).isTrue();
        assertEquals(loginUserResponse.user().name(), USER_NAME);
    }

    @Test
    @Sql(scripts = {"/db/integrationtests/deleteData.sql"}, executionPhase = AFTER_TEST_METHOD)
    void shouldThrowExceptionWhenUserDoesNotExist() {
        LoginUserDto loginUserDto = new LoginUserDto("usernotexists", USER_PASSWORD);

        webTestClient.post()
                .uri(LOGIN_USER_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginUserDto), LoginUserDto.class)
                .exchange()
                .expectStatus().isBadRequest();
    }

    @Test
    @Sql(scripts = {"/db/integrationtests/deleteData.sql"}, executionPhase = AFTER_TEST_METHOD)
    void shouldThrowExceptionWhenWrongPassword() {
        LoginUserDto loginUserDto = new LoginUserDto(USER_NAME, "Wrongpassword1!");

        webTestClient.post()
                .uri(LOGIN_USER_URL)
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(loginUserDto), LoginUserDto.class)
                .exchange()
                .expectStatus().isUnauthorized();
    }
}
