package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.PaymentAuthorizationResponse;
import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.AuthorizePayment;
import io.starfish.simplifiedpaymentgateway.modules.payment.validate.ValidateCard;
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

    public PaymentAuthorizationResponse initializePayment(PaymentRequestDto paymentRequestDto) {
        if (validateCard.isValid(paymentRequestDto)) {
            PaymentRequest paymentRequest = mapper.toEntity(paymentRequestDto);
            return authorizePayment.authorize(paymentRequest);
        } else {
            throw new CardValidationException("Invalid card");
        }
    }
}
