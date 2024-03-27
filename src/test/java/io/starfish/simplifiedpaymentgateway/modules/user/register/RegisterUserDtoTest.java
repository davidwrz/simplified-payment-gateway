package io.starfish.simplifiedpaymentgateway.modules.user.register;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.Test;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RegisterUserDtoTest {

    Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void shouldPassValidationWhenNameAndPasswordAreValid() {
        // Given
        RegisterUserDto validDto = new RegisterUserDto("ValidUser123", "ValidPassword1!");

        // When
        Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(validDto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenLengthOfNameIsInvalid() {
        // When
        Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(new RegisterUserDto("12", "ValidPassword1!"));

        // Then
        assertEquals(1, violations.size());
        assertEquals("size must be between 3 and 20", violations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailValidationWhenPasswordIsInvalid() {
        // When
        Set<ConstraintViolation<RegisterUserDto>> violations = validator.validate(new RegisterUserDto("ValidUser123", "invalidpassword"));

        // Then
        assertEquals(1, violations.size());
        assertEquals("Password is invalid! Password must be at least 8 characters long and contain at least one digit, one uppercase letter, and one special character.", violations.iterator().next().getMessage());
    }
}
