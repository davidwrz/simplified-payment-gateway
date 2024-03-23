package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

class PaymentMethod {
    private String number;
    private String expiryMonth;
    private String expiryYear;
    private String holderName;
    private String cvc;

    public PaymentMethod() {
    }
    public PaymentMethod(String number, String expiryMonth, String expiryYear, String holderName, String cvc) {
        this.number = number;
        this.expiryMonth = expiryMonth;
        this.expiryYear = expiryYear;
        this.holderName = holderName;
        this.cvc = cvc;
    }
}
