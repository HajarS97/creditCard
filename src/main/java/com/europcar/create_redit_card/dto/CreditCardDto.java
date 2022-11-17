package com.europcar.create_redit_card.dto;
import com.europcar.create_redit_card.exception.ValidationException;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.validation.annotation.Validated;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.regex.Pattern;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Validated
public class CreditCardDto {

    private static final int FIRST_6_NUMBER=6;
    private static final int LAST_4_NUMBER=4;
    private static final String MASK_TOKEN="X";

    private Long id;
    private String cardNumber;
    private String expiredDate ;
    private String creditCardVerification;
    private CreditCardStatus creditCardStatus;
    private Date blockDate;


    public void checkCreditCard(String cvcRegex, String cardNumberRegex){

        if(!checkFormat(this.getCreditCardVerification(),cvcRegex)){
            throw new ValidationException(ValidationException.CVC_WRONG_FORMAT);}

        if(!checkFormat(this.getCardNumber(),cardNumberRegex)){
            throw new ValidationException(ValidationException.CREDIT_CARD_NUMBER_WRONG_FORMAT);}

        if(checkExpirationDate(this.getExpiredDate())){
            throw new ValidationException(ValidationException.WRONG_DATE);}
    }


    public boolean checkExpirationDate(String date) {
        try {
            LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ofPattern("d/MM/yyyy"));
            return localDate.compareTo(LocalDate.now())<=0;
        }catch (Exception e){
           throw new ValidationException(ValidationException.WRONG_DATE_FORMAT);
        }

    }

    public boolean checkFormat(String field, String format) {
        return Pattern.compile(format).matcher(field).matches();
    }


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
