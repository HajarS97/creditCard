package com.europcar.create_redit_card.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;
import java.util.Date;

//usufull
@Getter
@Setter
@Builder
@AllArgsConstructor
@Validated
public class CreditCardDto {

    private static final int FIRST_6_NUMBER = 6;
    private static final int LAST_4_NUMBER = 4;
    private static final String MASK_TOKEN = "X";

    private Long id;
    private String cardNumber;
    private String expiredDate;
    private String creditCardVerification;
    private CreditCardStatus creditCardStatus;
    private Date blockDate;


    public static String applyMask(String value) {
        char[] chars = value.toCharArray();
        StringBuilder builder = new StringBuilder();
        for (int index = 0; index < chars.length; index++) {
            int curr = value.length() - index;
            if (index < FIRST_6_NUMBER || curr <= LAST_4_NUMBER) {
                builder.append(chars[index]);
            } else {
                builder.append(MASK_TOKEN);
            }
        }
        return builder.toString();
    }


}
