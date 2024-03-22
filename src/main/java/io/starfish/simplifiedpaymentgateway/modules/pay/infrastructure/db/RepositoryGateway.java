package io.starfish.simplifiedpaymentgateway.modules.pay.infrastructure.db;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("saveCardRepositoryGateway")
public class RepositoryGateway {

    private final CardRepository cardRepository;

    public RepositoryGateway(CardRepository cardRepository) {
        this.cardRepository = cardRepository;
    }

}
