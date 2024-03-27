package io.starfish.simplifiedpaymentgateway.modules.user.login;

import io.starfish.simplifiedpaymentgateway.modules.user.register.User;
import io.starfish.simplifiedpaymentgateway.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginUserFacade {

    private final AuthenticationManager authenticationManager;
    private final LoginUserMapper loginUserMapper;
    private final JWTUtil jwtUtil;

    public LoginUserFacade(AuthenticationManager authenticationManager,
                           LoginUserMapper loginUserMapper,
                           JWTUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.loginUserMapper = loginUserMapper;
        this.jwtUtil = jwtUtil;
    }

    public LoginUserResponse login(LoginUserDto userDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(userDto.name(), userDto.password())
        );
        User principal = (User) authentication.getPrincipal();
        LoggedUserDto user = loginUserMapper.toDto(principal);

        String jwt = jwtUtil.issueToken(user.name());
        log.info(String.format("User: %s requested new JWT token", userDto.name()));
        return new LoginUserResponse(jwt, user);
    }

}
