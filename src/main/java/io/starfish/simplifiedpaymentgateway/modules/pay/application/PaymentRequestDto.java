package io.starfish.simplifiedpaymentgateway.modules.pay.application;

public record PaymentRequestDto(
        Amount amount,
        String reference,
        PaymentMethod paymentMethod,
        String merchantAccount
) {
    public record Amount(String currency, int value) {
    }

    public record PaymentMethod(
            String type,
            String number,
            String expiryMonth,
            String expiryYear,
            String holderName,
            String cvc
    ) {
    }
}

