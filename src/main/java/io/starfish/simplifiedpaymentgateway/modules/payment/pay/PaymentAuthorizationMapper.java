package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import io.starfish.simplifiedpaymentgateway.utils.mapper.EntityMapper;
import org.springframework.stereotype.Service;

@Service
class PaymentAuthorizationMapper implements EntityMapper<PaymentRequest, PaymentRequestDto> {

    @Override
    public PaymentRequest toEntity(PaymentRequestDto paymentRequestDto) {
        return null;
    }
}
