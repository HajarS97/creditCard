package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.CreditCardDto;

public interface ICreditCardCheckService {

    void checkCreditCard(CreditCardDto creditCardDto);
    boolean checkExpirationDate(String date);
    boolean checkFormat(String field, String format);
}
