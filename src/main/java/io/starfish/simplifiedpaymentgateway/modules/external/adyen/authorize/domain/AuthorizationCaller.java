package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.domain;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.application.AuthorizationResponseDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
class AuthorizationCaller {

    private final WebClient webClient;

    private static final String ADYEN_AUTHORIZE_PAYMENT_URL = "";

    AuthorizationCaller(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ADYEN_AUTHORIZE_PAYMENT_URL).build();
    }

    AuthorizationResponseDto authorizePaymentFromAdyenSandbox() {
        return webClient.get()
                .uri(ADYEN_AUTHORIZE_PAYMENT_URL)
                .accept(MediaType.APPLICATION_JSON)
                .retrieve()
                .bodyToMono(AuthorizationResponseDto.class)
                .block();
    }
}
