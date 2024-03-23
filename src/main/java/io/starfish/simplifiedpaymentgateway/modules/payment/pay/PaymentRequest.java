package io.starfish.simplifiedpaymentgateway.modules.payment.pay;


public class PaymentRequest {
    private Amount amount;
    private PaymentMethod paymentMethod;

    public PaymentRequest(Amount amount, PaymentMethod paymentMethod) {
        this.amount = amount;
        this.paymentMethod = paymentMethod;
    }

}
