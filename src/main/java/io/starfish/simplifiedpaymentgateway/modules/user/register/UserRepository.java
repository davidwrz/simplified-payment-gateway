package io.starfish.simplifiedpaymentgateway.modules.user.register;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("registerUserRepository")
interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByName(String name);
}
