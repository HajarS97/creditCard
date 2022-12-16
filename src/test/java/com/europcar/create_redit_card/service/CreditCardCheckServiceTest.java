package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.dto.CreditCardStatus;
import com.europcar.create_redit_card.exception.ValidationException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;


@ExtendWith(MockitoExtension.class)
class CreditCardCheckServiceTest {

    @InjectMocks
    private CreditCardCheckService creditCardCheckService;
    @Mock
    private CreditCardServiceImp creditCardServiceImp;


    @BeforeEach
    public void setUp() {

    }

    @Test
    void checkWrongCvcVerification() {

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("1212")
                .expiredDate("12/03/2030")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardCheckService, "cvcRegex", "^[0-9][0-9][0-9]$");


        try {
            creditCardCheckService.checkCreditCard(creditCardDto);
        } catch (ValidationException validationException) {
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.CVC_WRONG_FORMAT);
        }

    }

    @Test
    void checkWrongCreditCardNumber() {

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("476173900101001s90")
                .creditCardVerification("112")
                .expiredDate("12/03/2030")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardCheckService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardCheckService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");


        try {
            creditCardCheckService.checkCreditCard(creditCardDto);
        } catch (ValidationException validationException) {
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.CREDIT_CARD_NUMBER_WRONG_FORMAT);
        }

    }

    @Test
    void checkWrongDate() {

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("112")
                .expiredDate("03/11/2022")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardCheckService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardCheckService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");


        //when
        try {
            creditCardCheckService.checkCreditCard(creditCardDto);
        } catch (ValidationException validationException) {
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.WRONG_DATE);
        }

    }


    @Test
    void checkRightDate() {

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("112")
                .expiredDate("03/11/2023")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardCheckService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardCheckService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");


        //when
            creditCardCheckService.checkCreditCard(creditCardDto);


    }




    @Test
    void checkWrongDateFormat() {

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("112")
                .expiredDate("03/11/2023/P")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardCheckService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardCheckService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");


        try {
            creditCardCheckService.checkCreditCard(creditCardDto);
        } catch (ValidationException validationException) {
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.WRONG_DATE_FORMAT);
        }

    }

}


