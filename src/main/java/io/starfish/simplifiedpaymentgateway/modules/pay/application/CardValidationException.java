package io.starfish.simplifiedpaymentgateway.modules.pay.application;

class CardValidationException extends RuntimeException {

    public CardValidationException(String message) {
        super(message);
    }
}
