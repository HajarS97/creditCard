package com.europcar.create_redit_card.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

class CreditCardDtoTest {

    CreditCardDto creditCardDto;

    @BeforeEach
    public void setUp() {
        creditCardDto = CreditCardDto.builder()
                .build();
    }

    @Test
    void testcardNumber() {
        creditCardDto.setCardNumber("123456789");
        Assertions.assertSame("123456789" , creditCardDto.getCardNumber());
    }

    @Test
    void testcreditCardStatus() {
        creditCardDto.setCreditCardStatus(CreditCardStatus.ACTIVE);
        Assertions.assertSame(CreditCardStatus.ACTIVE , creditCardDto.getCreditCardStatus());
    }

    @Test
    void testexpiredDate() {
        creditCardDto.setExpiredDate("12/12/2022");
        Assertions.assertSame("12/12/2022" , creditCardDto.getExpiredDate());
    }

    @Test
    void testId() {
        creditCardDto.setId(1L);
        Assertions.assertSame(1L , creditCardDto.getId());
    }

    @Test
    void testcreditCardVerification() {
        creditCardDto.setCreditCardVerification("123");
        Assertions.assertSame("123" , creditCardDto.getCreditCardVerification());
    }

    @Test
    void testBlockDate() {
        Date date = new Date();
        creditCardDto.setBlockDate(date);
        Assertions.assertSame(date , creditCardDto.getBlockDate());
    }
}
