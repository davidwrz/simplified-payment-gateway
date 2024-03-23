package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

import io.starfish.simplifiedpaymentgateway.modules.payment.pay.PaymentRequest;
import io.starfish.simplifiedpaymentgateway.modules.payment.pay.PaymentRequestDto;
import org.springframework.stereotype.Service;

@Service
public class AuthorizePayment {

    private final AuthorizationCaller authorizationCaller;

    AuthorizePayment(AuthorizationCaller authorizationCaller) {
        this.authorizationCaller = authorizationCaller;
    }

    public PaymentAuthorizationResponse authorize(PaymentRequest paymentRequest) {
        var paymentAuthorizationResponseDto = authorizationCaller.authorizePaymentFromAdyenSandbox(paymentRequest);
        return new PaymentAuthorizationResponse(paymentAuthorizationResponseDto.resultCode());
    }
}
