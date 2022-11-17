package com.europcar.create_redit_card.repositoryTest;

import com.europcar.create_redit_card.entity.CreditCardEntity;
import com.europcar.create_redit_card.repository.ICreditCardRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class CreditCardReposirotyTest {

    @Autowired
    private ICreditCardRepository iCreditCardRepo;

    private CreditCardEntity creditCardEntity;

    @BeforeEach
    public void setUp(){
         creditCardEntity = CreditCardEntity.builder()
                .id(1L)
                .cardNumber("129")
                .creditCardVerification("121")
                .expiredDate("12/03/2030")
                .build();
    }

    @Test
     void creatCreditCardTest(){
        //given

        //when
        CreditCardEntity creditCardEntity2 = iCreditCardRepo.save(creditCardEntity);

        //then
        Assertions.assertThat(creditCardEntity2).isNotNull();
    }

    @Test
     void findByCardNumberTest(){

        //given
        CreditCardEntity creditCardEntity2 = iCreditCardRepo.save(creditCardEntity);

        //when
        CreditCardEntity creditCardEntityF = iCreditCardRepo.findBycardNumber(creditCardEntity2.getCardNumber()).get();

        //then
        Assertions.assertThat(creditCardEntityF).isNotNull();
    }



}

