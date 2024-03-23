package io.starfish.simplifiedpaymentgateway.modules.user.exists.infrastructure.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("existsUserRepositoryGateway")
public class RepositoryGateway {

    @Qualifier("existsUserRepository")
    private final UserRepository repository;

    RepositoryGateway(UserRepository repository) {
        this.repository = repository;
    }

    public boolean existsUser(String name) {
        return repository.findByName(name).isPresent();
    }
}
