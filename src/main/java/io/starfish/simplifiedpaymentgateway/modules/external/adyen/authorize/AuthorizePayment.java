package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

import io.starfish.simplifiedpaymentgateway.modules.payment.pay.PaymentRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class AuthorizePayment {

    private final AuthorizationCaller authorizationCaller;

    AuthorizePayment(AuthorizationCaller authorizationCaller) {
        this.authorizationCaller = authorizationCaller;
    }

    public PaymentAuthorizationResponse authorize(PaymentRequest paymentRequest) {
        var paymentAuthorizationResponseDto = authorizationCaller.authorizePaymentFromAdyenSandbox(paymentRequest);
        log.info(String.format("Card: %s is: %s", paymentRequest.paymentMethod().number(), paymentAuthorizationResponseDto.resultCode()));
        return new PaymentAuthorizationResponse(paymentAuthorizationResponseDto.resultCode(), paymentAuthorizationResponseDto.merchantReference());
    }
}
