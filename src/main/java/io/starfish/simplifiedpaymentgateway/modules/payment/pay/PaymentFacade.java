package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.AuthorizePayment;
import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.PaymentAuthorizationResponse;
import io.starfish.simplifiedpaymentgateway.modules.payment.validate.CardValidationException;
import io.starfish.simplifiedpaymentgateway.modules.payment.validate.ValidateCard;
import org.springframework.stereotype.Service;

@Service
public class PaymentFacade {

    private final ValidateCard validateCard;
    private final AuthorizePayment authorizePayment;
    private final InitializePayment initializePayment;

    public PaymentFacade(ValidateCard validateCard, AuthorizePayment authorizePayment, InitializePayment initializePayment) {
        this.validateCard = validateCard;
        this.authorizePayment = authorizePayment;
        this.initializePayment = initializePayment;
    }

    public PaymentAuthorizationResponse pay(PaymentRequestDto paymentRequestDto) {
        if (validateCard.isValid(paymentRequestDto)) {
            PaymentRequest paymentRequest = initializePayment.initialize(paymentRequestDto);
            return authorizePayment.authorize(paymentRequest);
        } else {
            throw new CardValidationException("Invalid card");
        }
    }
}
