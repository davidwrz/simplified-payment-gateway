package io.starfish.simplifiedpaymentgateway.integrationtests.modules.payment;

import io.starfish.simplifiedpaymentgateway.integrationtests.helpers.RegisterUserHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.RANDOM_PORT;
import static org.springframework.test.context.jdbc.Sql.ExecutionPhase.AFTER_TEST_METHOD;

@SpringBootTest(webEnvironment = RANDOM_PORT)
class AuthorizePaymentFromAdyenITCase {

    @Autowired
    private WebTestClient webTestClient;
    @Autowired
    private RegisterUserHelper registerUserHelper;
    @Value("${adyen.api-key}")
    private String apiKey;

    private static final String AUTHORIZE_PAYMENT_URL = "/api/v1/pay";
    private String jwt;

    @BeforeEach
    void registerUser() {
        jwt = registerUserHelper.getJwtToken(webTestClient);
    }

    @Test
    @Sql(scripts = {"/db/integrationtests/deleteData.sql"}, executionPhase = AFTER_TEST_METHOD)
    void shouldAuthorizePayment() {
        String jsonRequest = """
                {
                  "amount" : {
                    "currency" : "USD",
                    "value" : 1
                  },
                  "paymentMethod" : {
                    "number" : "4111111111111111",
                    "expiryMonth" : "12",
                    "expiryYear" : "2030",
                    "holderName" : "John Smith",
                    "cvc" : "737"
                  }
                }
                """;

        webTestClient.post()
                .uri(AUTHORIZE_PAYMENT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .body(Mono.just(jsonRequest), String.class)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers -> {
                    headers.set("X-API-Key", apiKey);
                    headers.set(HttpHeaders.AUTHORIZATION, jwt);
                })
                .exchange()
                .expectStatus()
                .isOk();
    }
}
