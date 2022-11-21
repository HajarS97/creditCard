
package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.dto.CreditCardStatus;
import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.entity.CreditCardEntity;
import com.europcar.create_redit_card.entity.PersonEntity;
import com.europcar.create_redit_card.exception.CheckNumberAttemptException;
import com.europcar.create_redit_card.exception.CreditCardExistanceException;
import com.europcar.create_redit_card.exception.ValidationException;
import com.europcar.create_redit_card.mapper.CreditCardMapper;
import com.europcar.create_redit_card.repository.ICreditCardRepository;
import com.europcar.create_redit_card.repository.IPersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Date;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class CreditCardServiceTest {

    @Mock
    private ICreditCardRepository creditCardRepo;
    @InjectMocks
    private CreditCardServiceImp creditCardService;
    CreditCardDto creditCardDto;
    @Mock
    CreditCardMapper creditCardMapper;
    @Mock
    NotificationServiceImpl notificationService;
    @Mock
    private IPersonRepository personRepository;
    private PersonEntity personEntity;
    private Person person;



    @BeforeEach
    public void setUp(){
        creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("124")
                .creditCardVerification("122")
                .expiredDate("12/03/2030")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();
        personEntity = PersonEntity.builder()
                .id(1L)
                .email("test@gmail.com")
                .firstName("firstname")
                .lastName("lastname")
                .creditCardEntityList(null)
                .build();
        person = Person.builder()
                .email("test@gmail.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();
    }

    @Test
    void creditCardAlreadyExistTest(){

        //given
        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.dtoToCreditCard(creditCardDto);
        //creditCardEntity.setStatus(Status.ACTIVE);


        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.of(creditCardEntity));
        try {
            creditCardService.createCreditCard(creditCardDto,1L);
        }catch (CreditCardExistanceException creditCardExistanceException){
            Assertions.assertThat(creditCardExistanceException).isNotNull();
            Assertions.assertThat(creditCardExistanceException.getMessage()).isEqualTo(CreditCardExistanceException.CARD_ALREADY_EXIST);
        }

    }

    @Test
    void personDoesntExistException(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("1212")
                .expiredDate("12/03/2030")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardService, "cvcRegex", "^[0-9][0-9][0-9]$");

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        Mockito.when(personRepository.findById(personEntity.getId())).thenReturn(Optional.empty());
        try {
            creditCardService.createCreditCard(creditCardDto,1L);
        }catch (ValidationException validationException){
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.PERSON_DOESNT_EXISTD);
        }

    }

    @Test
    void checkWrongCvcVerification(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("1212")
                .expiredDate("12/03/2030")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardService, "cvcRegex", "^[0-9][0-9][0-9]$");

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        Mockito.when(personRepository.findById(personEntity.getId())).thenReturn(Optional.of(personEntity));
        try {
            creditCardService.createCreditCard(creditCardDto,1L);
        }catch (ValidationException validationException){
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.CVC_WRONG_FORMAT);
        }

    }

    @Test
    void checkWrongCreditCardNumber(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("47617390010100190")
                .creditCardVerification("112")
                .expiredDate("12/03/2030")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        Mockito.when(personRepository.findById(personEntity.getId())).thenReturn(Optional.of(personEntity));
        try {
            creditCardService.createCreditCard(creditCardDto,1L);
        }catch (ValidationException validationException){
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.CREDIT_CARD_NUMBER_WRONG_FORMAT);
        }

    }

    @Test
    void checkWrongDate(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("112")
                .expiredDate("03/11/2022")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");


        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        Mockito.when(personRepository.findById(personEntity.getId())).thenReturn(Optional.of(personEntity));
        try {
            creditCardService.createCreditCard(creditCardDto,1L);
        }catch (ValidationException validationException){
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.WRONG_DATE);
        }

    }

    @Test
    void checkWrongDateFormat(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("112")
                .expiredDate("03/11/2023/P")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        ReflectionTestUtils.setField(creditCardService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");


        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        Mockito.when(personRepository.findById(personEntity.getId())).thenReturn(Optional.of(personEntity));
        try {
            creditCardService.createCreditCard(creditCardDto,1L);
        }catch (ValidationException validationException){
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo(ValidationException.WRONG_DATE_FORMAT);
        }

    }

    @Test
    void creatCreditCardTest(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .cardNumber("4761739001010019")
                .creditCardVerification("112")
                .expiredDate("03/11/2023")
                .build();
        CreditCardDto creditCardDtoFromEntity = CreditCardDto.builder()
                .cardNumber("4761739001010019")
                .creditCardVerification("112")
                .expiredDate("03/11/2023")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .build();

        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.dtoToCreditCard(creditCardDto);
        creditCardEntity.setCreditCardStatus(CreditCardStatus.ACTIVE);
        ReflectionTestUtils.setField(creditCardService, "cvcRegex", "^[0-9][0-9][0-9]$");
        ReflectionTestUtils.setField(creditCardService, "cardNumberRegex", "^4[0-9]{12}(?:[0-9]{3})?$");

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        Mockito.when(personRepository.findById(personEntity.getId())).thenReturn(Optional.of(personEntity));
        Mockito.when(creditCardRepo.save(creditCardEntity)).thenReturn(creditCardEntity);
        Mockito.when(creditCardMapper.creditCardToDto(creditCardEntity)).thenReturn(creditCardDtoFromEntity);
        Mockito.when(creditCardMapper.dtoToCreditCard(creditCardDto)).thenReturn(creditCardEntity);
        CreditCardDto creditCardDtoResponse = creditCardService.createCreditCard(creditCardDto,1L);

        //then
        Assertions.assertThat(creditCardDtoResponse).isNotNull();
        Assertions.assertThat(creditCardDtoResponse.getCreditCardStatus()).isEqualTo(CreditCardStatus.ACTIVE);

    }

    @Test
    void createCreditCardDoesntExistTest(){

        //given
        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.dtoToCreditCard(creditCardDto);
        creditCardEntity.setCreditCardStatus(CreditCardStatus.ACTIVE);

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.empty());
        try {
            creditCardService.checkCreditCardVerification(creditCardDto.getCardNumber(),creditCardDto.getCreditCardVerification());
        }catch (CreditCardExistanceException creditCardExistanceException){
            Assertions.assertThat(creditCardExistanceException).isNotNull();
            Assertions.assertThat(creditCardExistanceException.getMessage()).isEqualTo(CreditCardExistanceException.CARD_DOESNT_EXIST);
        }

    }
//(creditCardEntity.getBlockDate().compareTo(new Date())<0

    @Test
    void blockdateExceptionTest(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("122")
                .expiredDate("03/11/2023")
                .build();
        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.dtoToCreditCard(creditCardDto);
        creditCardEntity.setBlockDate(new Date(2020,02,3));
        //creditCardEntity.setMaxTentative(-5);

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.of(creditCardEntity));

        try {
            creditCardService.checkCreditCardVerification(creditCardDto.getCardNumber(),"121");
        }catch (ValidationException validationException){
            Assertions.assertThat(validationException).isNotNull();
            Assertions.assertThat(validationException.getMessage()).isEqualTo("you are blocked");
        }
    }

    @Test
    void tentativesExceptionTest(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("122")
                .expiredDate("03/11/2023")
                .build();
        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.dtoToCreditCard(creditCardDto);
        creditCardEntity.setMaxTentative(-2);
        creditCardEntity.setBlockDate(new Date(2023));

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.of(creditCardEntity));

        try {
            creditCardService.checkCreditCardVerification(creditCardDto.getCardNumber(),"121");
        }catch (CheckNumberAttemptException checkNumberAttemptException){
            Assertions.assertThat(checkNumberAttemptException).isNotNull();
            Assertions.assertThat(checkNumberAttemptException.getMessage()).isEqualTo("You still have "+(3 - (creditCardEntity.getMaxTentative()))+" attempt", creditCardEntity.getMaxTentative());
        }
    }

    @Test
    void maxTentativesExceptionTest(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("122")
                .expiredDate("03/11/2023")
                .build();
        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.dtoToCreditCard(creditCardDto);
        creditCardEntity.setMaxTentative(2);
        creditCardEntity.setBlockDate(new Date(2023));
        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.of(creditCardEntity));

        try {
            creditCardService.checkCreditCardVerification(creditCardDto.getCardNumber(),"121");
        }catch (CheckNumberAttemptException checkNumberAttemptException){
            Assertions.assertThat(checkNumberAttemptException).isNotNull();
            Assertions.assertThat(checkNumberAttemptException.getMessage()).isEqualTo("You have no attempt left, we will send you a mail to explain the procedure", creditCardEntity.getMaxTentative());
        }

    }

    @Test
    void checkCreditCodeVerificationTest(){

        //given
        CreditCardDto creditCardDto = CreditCardDto.builder()
                .id(1L)
                .cardNumber("4761739001010019")
                .creditCardVerification("122")
                .expiredDate("03/11/2023")
                .build();
        CreditCardEntity creditCardEntity = CreditCardMapper.INSTANCE.dtoToCreditCard(creditCardDto);
        creditCardEntity.setBlockDate(new Date(2023));

        //when
        Mockito.when(creditCardRepo.findBycardNumber(creditCardDto.getCardNumber())).thenReturn(Optional.of(creditCardEntity));
        Mockito.when(creditCardMapper.creditCardToDto(creditCardEntity)).thenReturn(creditCardDto);
        //then
        Assertions.assertThat(creditCardService.checkCreditCardVerification(creditCardDto.getCardNumber(),creditCardDto.getCreditCardVerification())).isEqualTo("476173XXXXXX0019");

    }

}

