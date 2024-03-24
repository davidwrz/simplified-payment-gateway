package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

public record PaymentRequest(
        Amount amount,
        String reference,
        PaymentMethod paymentMethod,
        String merchantAccount
) {
}

