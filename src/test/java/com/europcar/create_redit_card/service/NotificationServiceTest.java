package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.client.NotificationClient;
import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.dto.CreditCardStatus;
import com.europcar.create_redit_card.dto.NotificationValue;
import com.europcar.create_redit_card.entity.CreditCardEntity;
import com.europcar.create_redit_card.entity.PersonEntity;
import com.europcar.create_redit_card.exception.ValidationException;
import com.europcar.create_redit_card.messaging.producer.NotificationProducerEvent;
import com.europcar.create_redit_card.repository.ICreditCardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
class NotificationServiceTest {

    @Mock
    private  NotificationProducerEvent notificationProducerEvent;
    @Mock
    private  ICreditCardRepository creditCardRepository;
    @Mock
    private NotificationClient notificationClient;
    @InjectMocks
    private NotificationServiceImpl notificationService;
    PersonEntity personEntity;
    CreditCardDto creditCardDto;
    CreditCardEntity creditCardEntity;
    NotificationValue notificationValue;

    @BeforeEach
    public void setUp(){
         personEntity = PersonEntity.builder()
                .email("test@gmail.com")
                .firstName("firstname")
                .lastName("lastname")
                .creditCardEntityList(null)
                .build();
         creditCardDto = CreditCardDto.builder()
                .id(1L)
                .expiredDate("12/23/2028")
                .cardNumber("")
                .creditCardVerification("123")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();
         creditCardEntity = CreditCardEntity.builder()
                .id(1L)
                .expiredDate("12/23/2028")
                .cardNumber("")
                .creditCardVerification("123")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .personEntity(personEntity)
                .build();
         notificationValue=NotificationValue.builder()
                .cardNumber(creditCardEntity.getCardNumber())
                .expiryDate(creditCardEntity.getExpiredDate())
                .firstName(personEntity.getFirstName())
                .lastName(personEntity.getLastName())
                .email(personEntity.getEmail())
                .build();

            }


    @Test
    void cardDoesntExistTest() {

        //when
        Mockito.when(creditCardRepository.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        try {
            notificationService.sendNotification(creditCardDto);
        } catch (ValidationException validationException) {
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.CARD_DOESNT_EXIST);
        }

    }


        @Test
        void personNullTest(){

            //given
            creditCardEntity.setPersonEntity(null);
            //when
            Mockito.when(creditCardRepository.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.of(creditCardEntity));
            //then
            try {
                notificationService.sendNotification(creditCardDto);
            }catch (ValidationException validationException){
                Assertions.assertThat(validationException).isNotNull();
                Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.PERSON_DOESNT_LINKED_TO_CARD);
            }
        }



    @Test
    void sendNotificationTest(){

        //when
        Mockito.when(creditCardRepository.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.of(creditCardEntity));
        notificationService.sendNotification(creditCardDto);
        notificationClient.sendEmail(notificationValue);

        //then
        assertNotNull(notificationClient);

    }



}

