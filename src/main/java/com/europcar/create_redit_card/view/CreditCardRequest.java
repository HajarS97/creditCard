package com.europcar.create_redit_card.view;
import lombok.Data;

@Data
public class CreditCardRequest {

    private Long id;
    private String cardNumber;
    private String expiredDate;
    private String creditCardVerification;

}
