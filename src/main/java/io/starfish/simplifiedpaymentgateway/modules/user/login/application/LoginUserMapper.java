package io.starfish.simplifiedpaymentgateway.modules.user.login.application;

import io.starfish.simplifiedpaymentgateway.modules.user.register.domain.User;
import io.starfish.simplifiedpaymentgateway.utils.mapper.DtoMapper;
import org.springframework.stereotype.Service;

@Service
class LoginUserMapper implements DtoMapper<User, LoggedUserDto> {

    @Override
    public LoggedUserDto toDto(User user) {
        return new LoggedUserDto(user.getName());
    }
}
