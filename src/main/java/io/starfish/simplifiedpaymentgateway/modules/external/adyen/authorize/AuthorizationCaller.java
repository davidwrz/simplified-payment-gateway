package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

import io.starfish.simplifiedpaymentgateway.modules.pay.application.PaymentRequestDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
class AuthorizationCaller {

    private final WebClient webClient;
    @Value("${adyen.url}")
    private String authorizePaymentUrl;
    @Value("${adyen.api-key}")
    private String apiKey;

    AuthorizationCaller(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(authorizePaymentUrl).build();
    }

    PaymentAuthorizationResponseDto authorizePaymentFromAdyenSandbox(PaymentRequestDto paymentRequestDto) {
        return webClient.post()
                .uri(authorizePaymentUrl)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers -> headers.set("X-API-Key", apiKey))
                .bodyValue(paymentRequestDto)
                .retrieve()
                .bodyToMono(PaymentAuthorizationResponseDto.class)
                .block();
    }

}
