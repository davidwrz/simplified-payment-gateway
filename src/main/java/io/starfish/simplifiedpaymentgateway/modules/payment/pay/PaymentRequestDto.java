package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record PaymentRequestDto(
        @NotNull Amount amount,
//        String reference,
        PaymentMethod paymentMethod
//        String merchantAccount
) {
    public record Amount(
            @NotNull String currency,
            @NotNull int value) {
    }

    public record PaymentMethod(
//            String type,
            @NotNull @Size(min = 13, max = 16) String number,
            @NotNull @Size(min = 1, max = 1) String expiryMonth,
            @NotNull @Size(min = 1, max = 1) String expiryYear,
            @NotNull String holderName,
            @NotNull @Size(min = 3, max = 3) String cvc
    ) {
    }
}

