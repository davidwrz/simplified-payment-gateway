package io.starfish.simplifiedpaymentgateway.modules.payment.validate;

import io.starfish.simplifiedpaymentgateway.modules.payment.pay.PaymentRequestDto;
import io.starfish.simplifiedpaymentgateway.utils.date.DateProvider;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.ZoneId;
import java.time.ZonedDateTime;

import static org.junit.jupiter.api.Assertions.assertTrue;

class ValidateCardTest {

    ValidateCard validateCard;
    DateProvider dateProvider;

    @BeforeEach
    void before() {
        dateProvider = Mockito.mock(DateProvider.class);
        validateCard = new ValidateCard(dateProvider);
    }

    @Test
    void shouldReturnTrueWhenCardNumberIsValid() {
        //given
        PaymentRequestDto.AmountDto amount = new PaymentRequestDto.AmountDto("USD", 1L);
        PaymentRequestDto.CardDto paymentMethod = new PaymentRequestDto.CardDto("4007702835532454", "12", "2023", "Test test", "123");
        PaymentRequestDto paymentRequestDto = new PaymentRequestDto(amount, paymentMethod);

        Mockito.when(dateProvider.currentZonedDateTime()).thenReturn(ZonedDateTime.of(2023, 11, 2, 6, 5, 4, 0, ZoneId.systemDefault()));
        //when
        boolean isValid = validateCard.isValid(paymentRequestDto);
        //then
        assertTrue(isValid);
    }

    @Test
    void shouldReturnFalseWhenCardNumberIsInvalid() {
        //given

        //when

        //then
    }

    @Test
    void shouldReturnFalseWhenDateIsExpired() {
        //given

        //when

        //then
    }
}