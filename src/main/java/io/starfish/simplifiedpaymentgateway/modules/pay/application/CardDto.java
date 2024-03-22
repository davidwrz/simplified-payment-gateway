package io.starfish.simplifiedpaymentgateway.modules.pay.application;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record CardDto(
        @NotNull @Size(min = 13, max = 16) String number,
        @NotNull @Size(min = 5, max = 5) String expirationDate,
        @NotNull @Size(min = 3, max = 3) String cvv
) {}

