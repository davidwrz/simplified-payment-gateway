package io.starfish.simplifiedpaymentgateway.modules.validate;

import io.starfish.simplifiedpaymentgateway.modules.pay.application.CardDto;

abstract class CardValidator {
    abstract boolean isValid(CardDto data);
}
