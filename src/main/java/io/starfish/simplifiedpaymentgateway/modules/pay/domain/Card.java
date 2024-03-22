package io.starfish.simplifiedpaymentgateway.modules.pay.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import io.starfish.simplifiedpaymentgateway.utils.configuration.database.MonthYearDeserializer;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Card {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String number;
    @JsonDeserialize(using = MonthYearDeserializer.class)
    private LocalDate expirationDate;
    private String cvv;
}
