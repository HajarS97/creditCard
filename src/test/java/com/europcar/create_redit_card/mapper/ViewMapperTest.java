package com.europcar.create_redit_card.mapper;

import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.view.CreditCardRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class ViewMapperTest {

    @Test
    void requestToDtoTest() {
        //given
        CreditCardRequest creditCardRequest = CreditCardRequest.builder().
                id(1L)
                .cardNumber("123456789")
                .expiredDate("12/12/2023")
                .creditCardVerification("123").
                build();

        //when
        CreditCardDto creditCardDto = ViewMapper.INSTANCE.requestToDto(creditCardRequest);


        //then
        Assertions.assertThat(creditCardDto).isNotNull();
        Assertions.assertThat(creditCardDto.getCardNumber()).isEqualTo("123456789");
        Assertions.assertThat(creditCardDto.getCreditCardVerification()).isEqualTo("123");
        Assertions.assertThat(creditCardDto.getExpiredDate()).isEqualTo("12/12/2023");

    }
}


