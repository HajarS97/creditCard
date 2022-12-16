package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.client.NotificationClient;
import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.dto.NotificationValue;
import com.europcar.create_redit_card.entity.CreditCardEntity;
import com.europcar.create_redit_card.entity.PersonEntity;
import com.europcar.create_redit_card.exception.ValidationException;
import com.europcar.create_redit_card.messaging.producer.NotificationProducerEvent;
import com.europcar.create_redit_card.repository.ICreditCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationServiceImpl implements INotificationService {


    private final NotificationProducerEvent notificationProducerEvent;
    private final ICreditCardRepository creditCardRepository;
    private final NotificationClient notificationClient;

    public NotificationServiceImpl(NotificationProducerEvent notificationProducerEvent, ICreditCardRepository creditCardRepository,NotificationClient notificationClient) {
        this.notificationProducerEvent = notificationProducerEvent;
        this.creditCardRepository = creditCardRepository;
        this.notificationClient = notificationClient;
    }

    public void sendNotification(CreditCardDto creditCardDto){
        Optional<CreditCardEntity> optionalCreditCardEntity= creditCardRepository.findBycardNumber(creditCardDto.getCardNumber());
        if(optionalCreditCardEntity.isPresent()){
           CreditCardEntity creditCardEntity= optionalCreditCardEntity.get();
           PersonEntity personEntity= creditCardEntity.getPersonEntity();
           if(personEntity == null){
               throw new ValidationException(ValidationException.PERSON_DOESNT_LINKED_TO_CARD);
           }
            NotificationValue notificationValue=NotificationValue.builder()
                    .cardNumber(creditCardEntity.getCardNumber())
                    .expiryDate(creditCardEntity.getExpiredDate())
                    .firstName(personEntity.getFirstName())
                    .lastName(personEntity.getLastName())
                    .email(personEntity.getEmail())
                    .build();
            //notificationProducerEvent.sendMessage(notificationValue);
            notificationClient.sendEmail(notificationValue);
        }else {
            throw new ValidationException(ValidationException.CARD_DOESNT_EXIST);
        }
    }


}
