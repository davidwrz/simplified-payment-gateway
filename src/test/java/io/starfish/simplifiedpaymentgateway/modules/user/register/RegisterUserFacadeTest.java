package io.starfish.simplifiedpaymentgateway.modules.user.register;

import io.starfish.simplifiedpaymentgateway.security.JWTUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class RegisterUserFacadeTest {

    private RepositoryGateway gateway;
    private RegisterUserMapper mapper;
    private JWTUtil jwtUtil;
    private RegisterUserFacade facade;

    @BeforeEach
    public void setUp() {
        gateway = mock(RepositoryGateway.class);
        mapper = mock(RegisterUserMapper.class);
        jwtUtil = mock(JWTUtil.class);
        facade = new RegisterUserFacade(gateway, mapper, jwtUtil);
    }

    @Test
    public void shouldReturnValidTokenAfterRegistration() {
        // Given
        String name = "testuser";
        String password = "Testpassword1!";
        User user = User.of(name, password);
        RegisterUserDto userDto = new RegisterUserDto(name, password);

        // When
        when(gateway.existsUser(userDto.name())).thenReturn(false);
        when(mapper.toEntity(userDto)).thenReturn(user);
        when(jwtUtil.issueToken(name, "ROLE_USER")).thenReturn("example_token");
        String resultToken = facade.register(userDto);

        // Then
        Assertions.assertEquals("example_token", resultToken);
    }

    @Test()
    public void shouldThrowExceptionWhenUserAlreadyRegistered() {
        // Given
        String name = "testuser";
        String password = "Testpassword1!";
        RegisterUserDto userDto = new RegisterUserDto(name, password);

        // When
        when(gateway.existsUser(userDto.name())).thenReturn(true);

        // Then
        assertThrows(AlreadyRegisteredUserException.class, () -> {
            facade.register(userDto);
        });
    }
}