package com.europcar.create_redit_card.mapper;

import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.dto.CreditCardStatus;
import com.europcar.create_redit_card.entity.CreditCardEntity;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Date;

class CreditCardMapperTest {

    @Test
    void creditCardEntityTest() {
        //given
        CreditCardEntity creditCardEntity = CreditCardEntity.builder().
                id(1L)
                .cardNumber("123456789")
                .creditCardStatus(CreditCardStatus.ACTIVE)
                .expiredDate("12/12/2023")
                .blockDate(new Date())
                .maxTentative(2)
                .personEntity(null)
                .creditCardVerification("123").
                build();

        //when
        CreditCardDto creditCardDto = CreditCardMapper.INSTANCE.creditCardToDto(creditCardEntity);


        //then
        Assertions.assertThat(creditCardDto).isNotNull();
        Assertions.assertThat(creditCardDto.getCardNumber()).isEqualTo("123456789");
        Assertions.assertThat(creditCardDto.getCreditCardStatus()).isEqualTo(CreditCardStatus.ACTIVE);
        Assertions.assertThat(creditCardDto.getId()).isEqualTo(1L);
        Assertions.assertThat(creditCardDto.getCreditCardVerification()).isEqualTo("123");
        Assertions.assertThat(creditCardDto.getExpiredDate()).isEqualTo("12/12/2023");

    }
}

