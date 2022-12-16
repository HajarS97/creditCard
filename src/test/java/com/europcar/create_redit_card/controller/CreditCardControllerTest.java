package com.europcar.create_redit_card.controller;

import com.europcar.create_redit_card.controllers.CreditCardController;
import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.dto.CreditCardStatus;
import com.europcar.create_redit_card.mapper.ViewMapper;
import com.europcar.create_redit_card.service.CreditCardServiceImp;
import com.europcar.create_redit_card.view.CheckCardRequest;
import com.europcar.create_redit_card.view.CreditCardRequest;
import com.europcar.create_redit_card.view.CreditCardResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class CreditCardControllerTest {

    @Mock
    CreditCardServiceImp creditCardServiceImp;
    @InjectMocks
    CreditCardController createCreditCardController;
    @Mock
    ViewMapper viewMapper;
    CreditCardRequest creditCardRequest;
    CreditCardDto creditCardDto;
    CreditCardResponse creditCardResponse;
    CheckCardRequest checkCardRequest;

    @BeforeEach
    public void setUp() {
        creditCardRequest = CreditCardRequest.builder()
                .cardNumber("123456789")
                .expiredDate("12/12/2022")
                .creditCardVerification("123")
                .build();
        creditCardDto = CreditCardDto.builder()
                .cardNumber("123456789")
                .creditCardVerification("123")
                .expiredDate("12/12/2022")
                .build();
        creditCardResponse = CreditCardResponse.builder()
                .id(1L)
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .cardNumber("123456789")
                .expiredDate("12/12/2022")
                .creditCardVerification("123")
                .build();
        checkCardRequest = CheckCardRequest.builder()
                .cardNumber("12345678")
                .creditCardVerification("234")
                .build();
    }

    @Test
    void createCreditCardcontrollerTest(){

        //when
        Mockito.when(viewMapper.requestToDto(creditCardRequest)).thenReturn(creditCardDto);
        Mockito.when(creditCardServiceImp.createCreditCard(creditCardDto,1L)).thenReturn(creditCardDto);
        Mockito.when(viewMapper.dtoToResponse(creditCardDto)).thenReturn(creditCardResponse);

        //then
        Assertions.assertThat(createCreditCardController.createCreditCard(creditCardRequest, 1L)).isNotNull();
        Assertions.assertThat(createCreditCardController.createCreditCard(creditCardRequest, 1L)).isEqualTo(creditCardResponse);

    }

    @Test
    void checkCreditCardVerificationTest(){

        //when
        Mockito.when(creditCardServiceImp.checkCreditCardVerification(checkCardRequest.getCardNumber(),checkCardRequest.getCreditCardVerification())).thenReturn("string");

        //then
        Assertions.assertThat(createCreditCardController.checkCreditCardVerification(checkCardRequest)).isNotNull();
        Assertions.assertThat(createCreditCardController.checkCreditCardVerification(checkCardRequest)).isEqualTo("string");

    }
}





