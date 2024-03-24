package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record PaymentRequestDto(
        @Valid @JsonProperty("amount") @NotNull PaymentRequestDto.AmountDto amountDto,
        @Valid @JsonProperty("paymentMethod") @NotNull PaymentMethodDto paymentMethodDto
//        @NotNull @Pattern(regexp = "\\d{2}", message = "Wrong expiry month") String expiryMonth,
//        @NotNull @Pattern(regexp = "\\d{4}", message = "Wrong expiry month") String expiryYear
) {
    public record AmountDto(
            @NotNull String currency,
            @NotNull Long value) {
    }

    public record PaymentMethodDto(
            @NotNull @Size(min = 13, max = 16) String number,
            @NotNull @Pattern(regexp = "\\d{2}", message = "Wrong expiry month") String expiryMonth,
            @NotNull @Size(min = 1, max = 1) String expiryYear,
            @NotNull @Size(min = 3, max = 50) String holderName,
            @NotNull @Size(min = 3, max = 3) String cvc
    ) {
    }
}
