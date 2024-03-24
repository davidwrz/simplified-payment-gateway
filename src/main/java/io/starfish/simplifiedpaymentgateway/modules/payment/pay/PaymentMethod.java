package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

public record PaymentMethod(
        String type,
        String number,
        String expiryMonth,
        String expiryYear,
        String holderName,
        String cvc
) {
}
