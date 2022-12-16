package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.exception.ValidationException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.regex.Pattern;

@Service
public class CreditCardCheckService implements ICreditCardCheckService{

    @Value("${cvc.format}")
    private String cvcRegex;
    @Value("${card.number.format}")
    private String cardNumberRegex;

    public void checkCreditCard(CreditCardDto creditCardDto){

        if(!checkFormat(creditCardDto.getCreditCardVerification(),cvcRegex)){
            throw new ValidationException(ValidationException.CVC_WRONG_FORMAT);}

        if(!checkFormat(creditCardDto.getCardNumber(),cardNumberRegex)){
            throw new ValidationException(ValidationException.CREDIT_CARD_NUMBER_WRONG_FORMAT);}

        if(checkExpirationDate(creditCardDto.getExpiredDate())){
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
}
