package io.starfish.simplifiedpaymentgateway.modules.user.login;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.Test;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LoginUserDtoTest {

    private Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void shouldPassValidationWhenNameAndPasswordAreValid() {
        // Given
        LoginUserDto validDto = new LoginUserDto("ValidUser123", "ValidPassword1!");

        // When
        Set<ConstraintViolation<LoginUserDto>> violations = validator.validate(validDto);

        // Then
        assertTrue(violations.isEmpty());
    }

    @Test
    public void shouldFailValidationWhenNameIsNull() {
        // When
        Set<ConstraintViolation<LoginUserDto>> violations = validator.validate(new LoginUserDto(null, "ValidPassword1!"));

        // Then
        assertEquals(1, violations.size());
        assertEquals("may not be null", violations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailValidationWhenNameLengthIsInvalid() {
        // When
        Set<ConstraintViolation<LoginUserDto>> violations = validator.validate(new LoginUserDto("12", "ValidPassword1!"));

        // Then
        assertEquals(1, violations.size());
        assertEquals("size must be between 3 and 20", violations.iterator().next().getMessage());
    }

    @Test
    public void shouldFailValidationWhenPasswordIsNull() {
        // When
        Set<ConstraintViolation<LoginUserDto>> violations = validator.validate(new LoginUserDto("ValidUser123", null));

        // Then
        assertEquals(1, violations.size());
        assertEquals("may not be null", violations.iterator().next().getMessage());
    }
}