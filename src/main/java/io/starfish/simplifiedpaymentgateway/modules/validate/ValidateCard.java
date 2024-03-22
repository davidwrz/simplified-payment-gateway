package io.starfish.simplifiedpaymentgateway.modules.validate;

import io.starfish.simplifiedpaymentgateway.modules.pay.application.CardDto;
import io.starfish.simplifiedpaymentgateway.modules.pay.application.PaymentRequestDto;
import org.springframework.stereotype.Service;

@Service
public class ValidateCard {

    private final ValidateCardNumber validateCardNumber;
    private final ValidateExpiringDate validateExpiringDate;
    private final ValidateCvv validateCvv;

    public ValidateCard(ValidateCardNumber validateCardNumber, ValidateExpiringDate validateExpiringDate, ValidateCvv validateCvv) {
        this.validateCardNumber = validateCardNumber;
        this.validateExpiringDate = validateExpiringDate;
        this.validateCvv = validateCvv;
    }

    public boolean isValid(PaymentRequestDto paymentRequestDto) {
        return true;
    }

}
