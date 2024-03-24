package io.starfish.simplifiedpaymentgateway.utils.date;

import org.springframework.stereotype.Service;

import java.time.ZonedDateTime;

@Service
public class DateProvider {

    public ZonedDateTime currentZonedDateTime() {
        return ZonedDateTime.now();
    }
}
