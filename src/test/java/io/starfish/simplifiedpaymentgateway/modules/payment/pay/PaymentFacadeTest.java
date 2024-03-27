package io.starfish.simplifiedpaymentgateway.modules.payment.pay;

import io.starfish.simplifiedpaymentgateway.modules.external.adyen.authorize.AuthorizePayment;
import io.starfish.simplifiedpaymentgateway.modules.payment.validate.CardValidationException;
import io.starfish.simplifiedpaymentgateway.modules.payment.validate.ValidateCard;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

public class PaymentFacadeTest {

    PaymentFacade paymentFacade;
    ValidateCard validateCard;
    AuthorizePayment authorizePayment;
    InitializePayment initializePayment;

    @BeforeEach
    void setUp() {
        validateCard = Mockito.mock(ValidateCard.class);
        authorizePayment = Mockito.mock(AuthorizePayment.class);
        initializePayment = Mockito.mock(InitializePayment.class);
        paymentFacade = new PaymentFacade(validateCard, authorizePayment, initializePayment);
    }


    @Test
    void pay_WhenInvalidCard_ThrowsCardValidationException() {
        //given
        PaymentRequestDto.AmountDto amount = new PaymentRequestDto.AmountDto("USD", 1L);
        PaymentRequestDto.CardDto paymentMethodDto = new PaymentRequestDto.CardDto("4007702835532454", "12", "2023", "Test test", "123");
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(amount, paymentMethodDto);

        //when
        when(validateCard.isValid(paymentRequestDto)).thenReturn(false);

        //then
        assertThrows(CardValidationException.class, () -> {
            paymentFacade.pay(paymentRequestDto);
        });
    }


}
