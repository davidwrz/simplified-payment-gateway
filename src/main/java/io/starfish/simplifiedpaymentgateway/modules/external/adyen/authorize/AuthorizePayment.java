package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

import io.starfish.simplifiedpaymentgateway.modules.pay.application.PaymentRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorizePayment {

    private final AuthorizationCaller authorizationCaller;

    AuthorizePayment(AuthorizationCaller authorizationCaller) {
        this.authorizationCaller = authorizationCaller;
    }

    public PaymentAuthorizationResponse authorize(PaymentRequestDto paymentRequestDto) {
        var paymentAuthorizationResponseDto = authorizationCaller.authorizePaymentFromAdyenSandbox(paymentRequestDto);
        return new PaymentAuthorizationResponse(paymentAuthorizationResponseDto.resultCode());
    }
}
