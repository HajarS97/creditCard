package com.europcar.create_redit_card.view;

import com.europcar.create_redit_card.dto.CreditCardStatus;
import com.europcar.create_redit_card.view.CreditCardResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditCardResponseTest {

        CreditCardResponse creditCardResponse;

        @BeforeEach
        public void setUp() {
            creditCardResponse = CreditCardResponse.builder()
                    .build();
        }

    @Test
    void testcardNumber() {
        creditCardResponse.setCardNumber("123456789");
        Assertions.assertSame("123456789" , creditCardResponse.getCardNumber());
    }

    @Test
    void testcreditCardStatus() {
        creditCardResponse.setCreditCardStatus(CreditCardStatus.ACTIVE);
        Assertions.assertSame(CreditCardStatus.ACTIVE , creditCardResponse.getCreditCardStatus());
    }

    @Test
    void testexpiredDate() {
        creditCardResponse.setExpiredDate("12/12/2022");
        Assertions.assertSame("12/12/2022" , creditCardResponse.getExpiredDate());
    }

    @Test
    void testId() {
        creditCardResponse.setId(1L);
        Assertions.assertSame(1L , creditCardResponse.getId());
    }

    @Test
    void testcreditCardVerification() {
        creditCardResponse.setCreditCardVerification("123");
        Assertions.assertSame("123" , creditCardResponse.getCreditCardVerification());
    }
}
