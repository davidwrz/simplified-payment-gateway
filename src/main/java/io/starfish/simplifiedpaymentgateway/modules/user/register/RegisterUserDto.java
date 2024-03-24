package io.starfish.simplifiedpaymentgateway.modules.user.register;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record RegisterUserDto(
        @Size(min = 3, max = 20, message = "size must be between 3 and 20") @NotNull String name,
        @Pattern(regexp = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,}$",
                message = "Password is invalid!" +
                        " Password must be at least 8 characters long and contain at least one digit," +
                        " one uppercase letter, and one special character."
        )
        String password
) {

}
