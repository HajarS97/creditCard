package com.europcar.create_redit_card.dto;
import lombok.*;

@Getter
@Setter
@EqualsAndHashCode
@Builder
@AllArgsConstructor
public class NotificationValue {

    private String firstName;
    private String lastName;
    private String email;
    private String cardNumber;
    private String expiryDate;
}
