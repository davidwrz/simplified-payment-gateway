package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.domain;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.application.AuthorizationResponseDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorizePayment {

    private final AuthorizationCaller authorizationCaller;

    AuthorizePayment(AuthorizationCaller authorizationCaller) {
        this.authorizationCaller = authorizationCaller;
    }

    public AuthorizationResponseDto authorize() {
        return authorizationCaller.authorizePaymentFromAdyenSandbox();
    }
}
