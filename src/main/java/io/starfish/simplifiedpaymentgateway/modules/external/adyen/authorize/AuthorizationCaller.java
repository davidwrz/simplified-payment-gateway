package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

import io.starfish.simplifiedpaymentgateway.modules.pay.application.PaymentRequestDto;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
class AuthorizationCaller {

    private final WebClient webClient;

    private static final String ADYEN_AUTHORIZE_PAYMENT_URL = "https://checkout-test.adyen.com/v71/payments";
    private static final String API_KEY = "AQEvhmfuXNWTK0Qc+iSYl2oqrvWOTahIH4JfXWxEx1Svn8hraJ64gQ5iXQug5TyeDo0QwV1bDb7kfNy1WIxIIkxgBw==-G4N6q9q+auj9gXvSRNVxGYijOk35fUvEtaRMl0RWnPQ=-;t{@Vg6@,T.D@A^5";

    AuthorizationCaller(WebClient.Builder webClientBuilder) {
        this.webClient = webClientBuilder.baseUrl(ADYEN_AUTHORIZE_PAYMENT_URL).build();
    }

    PaymentAuthorizationResponseDto authorizePaymentFromAdyenSandbox(PaymentRequestDto paymentRequestDto) {
        return webClient.post()
                .uri(ADYEN_AUTHORIZE_PAYMENT_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .headers(headers -> headers.set("X-API-Key", API_KEY))
                .bodyValue(paymentRequestDto)
                .retrieve()
                .bodyToMono(PaymentAuthorizationResponseDto.class)
                .block();
    }
}
