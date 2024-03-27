package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import io.starfish.simplifiedpaymentgateway.modules.user.register.User;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1")
@Slf4j
class PointOfSalesController {

    private final PaymentFacade paymentFacade;

    PointOfSalesController(PaymentFacade paymentFacade) {
        this.paymentFacade = paymentFacade;
    }

    @PostMapping("/pay")
    ResponseEntity<?> pay(@Valid @RequestBody PaymentRequestDto paymentRequestDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User) authentication.getPrincipal();
        log.info(String.format("%s has initialized payment", user.getName()));
        return ResponseEntity.ok(paymentFacade.pay(paymentRequestDto));
    }
}
