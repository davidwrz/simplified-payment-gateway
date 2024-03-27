package io.starfish.simplifiedpaymentgateway.security;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class JWTUtilTest {

    @Test
    void shouldIssueValidTokenWithSubject() {
        // Given
        JWTUtil jwtUtil = new JWTUtil();
        String subject = "testuser";

        // When
        String token = jwtUtil.issueToken(subject);

        // Then
        assertTrue(jwtUtil.isTokenValid(token, subject));
    }

    @Test
    void shouldReturnSubjectFromToken() {
        // Given
        JWTUtil jwtUtil = new JWTUtil();
        String subject = "testuser";
        String token = jwtUtil.issueToken(subject);

        // When
        String extractedSubject = jwtUtil.getSubject(token);

        // Then
        assertEquals(subject, extractedSubject);
    }

    @Test
    void shouldValidateValidTokenForSameSubject() {
        // Given
        JWTUtil jwtUtil = new JWTUtil();
        String subject = "testuser";
        String token = jwtUtil.issueToken(subject);

        // When
        boolean isValid = jwtUtil.isTokenValid(token, subject);

        // Then
        assertTrue(isValid);
    }
}

