package io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize;

public record PaymentAuthorizationResponse (
        String message,
        String reference
) {
}
