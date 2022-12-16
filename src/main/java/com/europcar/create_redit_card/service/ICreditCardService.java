package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.CreditCardDto;



public interface ICreditCardService {

     CreditCardDto createCreditCard( CreditCardDto creditCard,Long id);
     String checkCreditCardVerification(String numberCard, String cvc);
}

