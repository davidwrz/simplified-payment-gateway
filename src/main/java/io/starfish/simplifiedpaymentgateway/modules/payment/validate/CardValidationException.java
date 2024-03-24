package io.starfish.simplifiedpaymentgateway.modules.payment.validate;

public class CardValidationException extends RuntimeException {

    public CardValidationException(String message) {
        super(message);
    }
}
