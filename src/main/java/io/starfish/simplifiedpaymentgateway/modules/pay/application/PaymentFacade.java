package io.starfish.simplifiedpaymentgateway.modules.pay.application;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.PaymentAuthorizationResponseDto;
import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.AuthorizePayment;
import io.starfish.simplifiedpaymentgateway.modules.validate.ValidateCard;
import org.springframework.stereotype.Service;

@Service
public class PaymentFacade {

    private final ValidateCard validateCard;
    private final AuthorizePayment authorizePayment;
    private final PaymentAuthorizationMapper mapper;

    public PaymentFacade(ValidateCard validateCard, AuthorizePayment authorizePayment, PaymentAuthorizationMapper mapper) {
        this.validateCard = validateCard;
        this.authorizePayment = authorizePayment;
        this.mapper = mapper;
    }

    public PaymentAuthorizationResponseDto initializePayment(PaymentRequestDto paymentRequestDto) {
        if (validateCard.isValid(paymentRequestDto)) {
            return authorizePayment.authorize(paymentRequestDto);
        } else {
            throw new CardValidationException("Invalid card");
        }
    }
}
