package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

class CardValidationException extends RuntimeException {

    public CardValidationException(String message) {
        super(message);
    }
}
