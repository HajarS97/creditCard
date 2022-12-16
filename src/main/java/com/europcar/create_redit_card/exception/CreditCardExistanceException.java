package com.europcar.create_redit_card.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreditCardExistanceException extends RuntimeException{

    private final String cardNumber;
    public static final String CARD_ALREADY_EXIST = "This card already exist";
    public static final String CARD_DOESNT_EXIST = "This card doesnt exist";

    public CreditCardExistanceException(String message, String cardNumber){
        super(message);
        this.cardNumber=cardNumber;
    }

}



