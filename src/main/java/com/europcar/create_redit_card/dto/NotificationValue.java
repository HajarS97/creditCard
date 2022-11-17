package com.europcar.create_redit_card.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class NotificationValue {

    private String firstName;
    private String lastName;
    private String email;
    private String cardNumber;
    private String expiryDate;
}
