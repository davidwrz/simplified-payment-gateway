package io.starfish.simplifiedpaymentgateway.modules.user.register;

import io.starfish.simplifiedpaymentgateway.security.JWTUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
public class RegisterUserFacade {

    @Qualifier("createUserGateway")
    private final RepositoryGateway repositoryGateway;
    private final RegisterUserMapper registerUserMapper;
    private final JWTUtil jwtUtil;

    public RegisterUserFacade(RepositoryGateway repositoryGateway,
                              RegisterUserMapper registerUserMapper,
                              JWTUtil jwtUtil) {
        this.repositoryGateway = repositoryGateway;
        this.registerUserMapper = registerUserMapper;
        this.jwtUtil = jwtUtil;
    }

    public String register(RegisterUserDto userDto) {
        String name = userDto.name();
        if (repositoryGateway.existsUser(name)) {
            log.error(String.format("User: %s already exists", name));
            throw new AlreadyRegisteredUserException(name);
        }
        User user = registerUserMapper.toEntity(userDto);
        repositoryGateway.registerUser(user);
        log.error(String.format("User: %s is created", name));
        return jwtUtil.issueToken(name, "ROLE_USER");
    }

    public Optional<User> findUserByName(String name) {
        return repositoryGateway.findUserByName(name);
    }
}
