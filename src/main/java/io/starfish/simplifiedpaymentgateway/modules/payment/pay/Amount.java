package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import java.math.BigDecimal;

class Amount {
    private String currency;
    private BigDecimal value;

    public Amount(String currency, BigDecimal value) {
        this.currency = currency;
        this.value = value;
    }
}