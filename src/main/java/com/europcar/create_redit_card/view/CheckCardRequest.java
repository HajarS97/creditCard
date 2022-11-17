package com.europcar.create_redit_card.view;


import lombok.Data;

@Data
public class CheckCardRequest {

    private String cardNumber;
    private String creditCardVerification;
}
