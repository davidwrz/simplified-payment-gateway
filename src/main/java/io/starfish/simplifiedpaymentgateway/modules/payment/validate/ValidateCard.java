package io.starfish.simplifiedpaymentgateway.modules.payment.validate;

import io.starfish.simplifiedpaymentgateway.modules.payment.pay.PaymentRequestDto;
import io.starfish.simplifiedpaymentgateway.utils.date.DateProvider;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class ValidateCard {

    private final DateProvider dateProvider;

    public ValidateCard(DateProvider dateProvider) {
        this.dateProvider = dateProvider;
    }

    public boolean isValid(PaymentRequestDto paymentRequestDto) {
        PaymentRequestDto.CardDto cardDetails = paymentRequestDto.cardDto();
        return isCardNumberValid(cardDetails.number())
                && isDateNotExpired(cardDetails.expiryMonth(), cardDetails.expiryYear());
    }

    private boolean isCardNumberValid(String cardNumber) {
        int sum = 0;
        boolean alternate = false;
        for (int i = cardNumber.length() - 1; i >= 0; i--) {
            int digit = Character.getNumericValue(cardNumber.charAt(i));
            if (alternate) {
                digit *= 2;
                if (digit > 9) {
                    digit = digit % 10 + 1;
                }
            }
            sum += digit;
            alternate = !alternate;
        }
        return sum % 10 == 0;
    }

    private boolean isDateNotExpired(String expiryMonth, String expiryYear) {
        LocalDate currentDate = dateProvider.currentZonedDateTime().toLocalDate();
        int month = Integer.parseInt(expiryMonth);
        int year = Integer.parseInt(expiryYear);
        LocalDate expiryDate = LocalDate.of(year, month, 1).plusMonths(1).minusDays(1);

        if (expiryDate.getYear() == currentDate.getYear() && expiryDate.getMonth() == currentDate.getMonth()) {
            return true;
        }
        return currentDate.isBefore(expiryDate);
    }
}
