package io.starfish.simplifiedpaymentgateway.modules.pay.infrastructure.web;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.PaymentAuthorizationResponse;
import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.PaymentAuthorizationResponseDto;
import io.starfish.simplifiedpaymentgateway.modules.pay.application.PaymentFacade;
import io.starfish.simplifiedpaymentgateway.modules.pay.application.PaymentRequestDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
class PointOfSalesController {

    private final PaymentFacade paymentFacade;

    PointOfSalesController(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    @PostMapping("/pay")
    ResponseEntity<PaymentAuthorizationResponse> pay(@RequestBody PaymentRequestDto paymentRequestDto) {
        return ResponseEntity.ok(paymentFacade.initializePayment(paymentRequestDto));
    }
}
