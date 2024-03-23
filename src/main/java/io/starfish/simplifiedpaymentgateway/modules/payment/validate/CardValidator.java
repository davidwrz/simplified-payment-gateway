package io.starfish.simplifiedpaymentgateway.modules.payment.validate;

import io.starfish.simplifiedpaymentgateway.modules.payment.pay.CardDto;

abstract class CardValidator {
    abstract boolean isValid(CardDto data);
}
