package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.AuthorizePayment;
import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.PaymentAuthorizationResponse;
import io.starfish.simplifiedpaymentgateway.modules.payment.validate.CardValidationException;
import io.starfish.simplifiedpaymentgateway.modules.payment.validate.ValidateCard;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
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
            log.info(String.format("Card number:%s is valid", paymentRequestDto.cardDto().number()));
            PaymentRequest paymentRequest = initializePayment.initialize(paymentRequestDto);
            return authorizePayment.authorize(paymentRequest);
        } else {
            log.error(String.format("Card number:%s is invalid", paymentRequestDto.cardDto().number()));
            throw new CardValidationException("Invalid card");
        }
    }
}
