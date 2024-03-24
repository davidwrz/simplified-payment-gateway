package io.starfish.simplifiedpaymentgateway.modules.user.register;

import io.starfish.simplifiedpaymentgateway.utils.mapper.EntityMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
class RegisterUserMapper implements EntityMapper<User, RegisterUserDto> {

    private final PasswordEncoder passwordEncoder;

    RegisterUserMapper(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User toEntity(RegisterUserDto userDto) {
        return User.of(userDto.name(), passwordEncoder.encode(userDto.password()));
    }
}
