package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import jakarta.validation.Valid;
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
    ResponseEntity<?> pay(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        return ResponseEntity.ok(paymentFacade.pay(paymentRequestDto));
    }
}
