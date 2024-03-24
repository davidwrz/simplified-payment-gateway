package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
class InitializePayment {

    @Value("${adyen.merchant-account}")
    private String merchantAccount;

    public PaymentRequest initialize(PaymentRequestDto paymentRequestDto) {
        Amount amount = getAmount(paymentRequestDto);
        PaymentMethod paymentMethod = getPaymentMethod(Type.SCHEME, paymentRequestDto.paymentMethodDto());

        return new PaymentRequest(amount, UUID.randomUUID().toString(), paymentMethod, merchantAccount);
    }

    private Amount getAmount(PaymentRequestDto paymentRequestDto) {
        return new Amount(
                paymentRequestDto.amountDto().currency(),
                paymentRequestDto.amountDto().value()
        );
    }

    private PaymentMethod getPaymentMethod(Type type, PaymentRequestDto.PaymentMethodDto paymentMethodDto) {
        return new PaymentMethod(
                type.getValue(),
                paymentMethodDto.number(),
                paymentMethodDto.expiryMonth(),
                paymentMethodDto.expiryYear(),
                paymentMethodDto.holderName(),
                paymentMethodDto.cvc()
        );
    }
}
