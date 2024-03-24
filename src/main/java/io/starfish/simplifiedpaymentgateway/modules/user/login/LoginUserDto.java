package io.starfish.simplifiedpaymentgateway.modules.user.login;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginUserDto(
        @Size(min = 3, max = 20, message = "size must be between 3 and 20") @NotNull(message = "may not be null") String name,
        @NotNull(message = "may not be null") String password) {
}
