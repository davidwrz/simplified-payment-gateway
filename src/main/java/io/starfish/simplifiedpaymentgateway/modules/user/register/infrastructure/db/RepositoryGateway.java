package io.starfish.simplifiedpaymentgateway.modules.user.register.infrastructure.db;

import io.starfish.simplifiedpaymentgateway.modules.user.register.domain.User;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("registerUserRepositoryGateway")
public class RepositoryGateway {

    @Qualifier("registerUserRepository")
    private final UserRepository repository;

    RepositoryGateway(UserRepository repository) {
        this.repository = repository;
    }

    public void registerUser(User user) {
        repository.save(user);
    }

    public Optional<User> findUserByName(String name) {
        return repository.findByName(name);
    }

    public boolean existsUser(String name) {
        return repository.findByName(name).isPresent();
    }
}
