package io.starfish.simplifiedpaymentgateway.modules.pay.infrastructure.db;

import io.starfish.simplifiedpaymentgateway.modules.pay.domain.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

interface CardRepository extends JpaRepository<Card, Long> {

}
