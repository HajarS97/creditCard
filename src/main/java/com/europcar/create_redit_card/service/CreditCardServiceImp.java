package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.dto.CreditCardStatus;
import com.europcar.create_redit_card.entity.CreditCardEntity;
import com.europcar.create_redit_card.entity.PersonEntity;
import com.europcar.create_redit_card.exception.CheckNumberAttemptException;
import com.europcar.create_redit_card.exception.CreditCardExistanceException;
import com.europcar.create_redit_card.exception.ValidationException;
import com.europcar.create_redit_card.mapper.CreditCardMapper;
import com.europcar.create_redit_card.repository.ICreditCardRepository;
import com.europcar.create_redit_card.repository.IPersonRepository;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.time.DateUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.Optional;

@Service
@Slf4j
@NoArgsConstructor
public class CreditCardServiceImp implements ICreditCardService{


    private  ICreditCardRepository creditCardRepository;
    private  CreditCardMapper creditCardMapper;
    private  INotificationService notificationService;
    private  IPersonRepository personRepository;
    private  String cvcRegex;
    private  String cardNumberRegex;
    private  int numberMaxAttempt;
    private int numberMinutesBlock;


    public CreditCardServiceImp(ICreditCardRepository creditCardRepository, CreditCardMapper creditCardMapper,
                                INotificationService notificationService, IPersonRepository personRepository,
                                @Value("${cvc.format}") String cvcRegex,  @Value("${card.number.format}") String cardNumberRegex,
                                @Value("${number.max.attempt}") int numberMaxAttempt,
                                @Value("${number.minutes.block}") int numberMinutesBlock ) {
        this.creditCardRepository = creditCardRepository;
        this.creditCardMapper = creditCardMapper;
        this.notificationService = notificationService;
        this.personRepository = personRepository;
        this.cvcRegex = cvcRegex;
        this.cardNumberRegex = cardNumberRegex;
        this.numberMaxAttempt = numberMaxAttempt;
        this.numberMinutesBlock= numberMinutesBlock;
    }

    @Override
    public CreditCardDto createCreditCard(CreditCardDto creditCardDto,Long id) {

        Optional<CreditCardEntity> optionalCreditCardEntity= creditCardRepository.findBycardNumber(creditCardDto.getCardNumber());
        if(optionalCreditCardEntity.isPresent()) {
            throw new CreditCardExistanceException(CreditCardExistanceException.CARD_ALREADY_EXIST,optionalCreditCardEntity.get().getCardNumber());
        }
        Optional<PersonEntity> optionalPersonEntity=  personRepository.findById(id);
        if(!optionalPersonEntity.isPresent()){
            throw new ValidationException(ValidationException.PERSON_DOESNT_EXISTD);
        }
        PersonEntity personEntity= optionalPersonEntity.get();
        creditCardDto.checkCreditCard(cvcRegex,cardNumberRegex);
        CreditCardEntity creditCardEntity = creditCardMapper.dtoToCreditCard(creditCardDto);
        creditCardEntity.setCreditCardStatus(CreditCardStatus.ACTIVE);
        creditCardEntity.setBlockDate(new Date());
        creditCardEntity.setPersonEntity(personEntity);
        return creditCardMapper.creditCardToDto(creditCardRepository.save(creditCardEntity));
    }

    @Override
    public String checkCreditCardVerification(String numberCard, String cvc) {
        Optional<CreditCardEntity> optionalCreditCardEntity = creditCardRepository.findBycardNumber(numberCard);
        if (optionalCreditCardEntity.isPresent()) {
            CreditCardEntity creditCardEntity = optionalCreditCardEntity.get();
            if (creditCardEntity.getBlockDate().compareTo(new Date())<0) {
            CreditCardDto creditCardDto = creditCardMapper.creditCardToDto(creditCardEntity);
            if (creditCardEntity.getMaxTentative() < numberMaxAttempt - 1 && !creditCardEntity.isEqualCvc(cvc)) {
                creditCardEntity.incrementTentativeNumber();
                creditCardRepository.save(creditCardEntity);
                throw new CheckNumberAttemptException("You still have " + (3 - (creditCardEntity.getMaxTentative())) + " attempt", creditCardEntity.getMaxTentative());

            } else if (creditCardEntity.getMaxTentative() >= numberMaxAttempt - 1 && !(creditCardEntity.isEqualCvc(cvc))) {
                creditCardEntity.setBlockDate((DateUtils.addMinutes(new Date(),numberMinutesBlock)));
                creditCardRepository.save(creditCardEntity);
                notificationService.sendNotification(creditCardDto);
                throw new CheckNumberAttemptException("You have no attempt left, we will send you a mail to explain the procedure", creditCardEntity.getMaxTentative());
            }
            return CreditCardDto.applyMask(creditCardDto.getCardNumber());
         }  creditCardEntity.setMaxTentative(0);
            creditCardRepository.save(creditCardEntity);
            throw new ValidationException("you are blocked");
        }throw new CreditCardExistanceException(CreditCardExistanceException.CARD_DOESNT_EXIST, numberCard);
    }




}

