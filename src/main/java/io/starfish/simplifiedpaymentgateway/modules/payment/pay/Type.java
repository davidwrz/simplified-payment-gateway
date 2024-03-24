package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

public enum Type {
    SCHEME("scheme");

    private final String value;

    Type(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
