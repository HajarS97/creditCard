package com.europcar.create_redit_card.view;

import com.europcar.create_redit_card.dto.CreditCardStatus;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditCardResponse {

    private Long id;
    private String cardNumber;
    private String expiredDate;
    private String creditCardVerification;
    private CreditCardStatus creditCardStatus;
}
