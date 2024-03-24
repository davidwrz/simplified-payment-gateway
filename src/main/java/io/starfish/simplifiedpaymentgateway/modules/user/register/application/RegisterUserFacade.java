package io.starfish.simplifiedpaymentgateway.modules.user.register.application;

import io.starfish.simplifiedpaymentgateway.modules.user.register.domain.User;
import io.starfish.simplifiedpaymentgateway.modules.user.register.infrastructure.db.RepositoryGateway;
import io.starfish.simplifiedpaymentgateway.security.JWTUtil;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
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
            throw new AlreadyRegisteredUserException(name);
        }
        User user = registerUserMapper.toEntity(userDto);
        repositoryGateway.registerUser(user);
        return jwtUtil.issueToken(name, "ROLE_USER");
    }

    public Optional<User> findUserByName(String name) {
        return repositoryGateway.findUserByName(name);
    }
}
