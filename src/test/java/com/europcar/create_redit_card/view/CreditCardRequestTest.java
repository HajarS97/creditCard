package com.europcar.create_redit_card.view;
import com.europcar.create_redit_card.view.CreditCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class CreditCardRequestTest {

    CreditCardRequest creditCardRequest;

    @BeforeEach
    public void setUp() {
        creditCardRequest = CreditCardRequest.builder()
                .build();
    }

    @Test
    void testId() {
        creditCardRequest.setId(1L);
        Assertions.assertSame(1L, creditCardRequest.getId());
    }

    @Test
    void testcardNumber() {
        creditCardRequest.setCardNumber("123456789");
        Assertions.assertSame("123456789" , creditCardRequest.getCardNumber());
    }

    @Test
    void testexpiredDate() {
        creditCardRequest.setExpiredDate("12/12/2022");
        Assertions.assertSame("12/12/2022" , creditCardRequest.getExpiredDate());
    }

    @Test
    void testcreditCardVerification() {
        creditCardRequest.setCreditCardVerification("123");
        Assertions.assertSame("123" , creditCardRequest.getCreditCardVerification());
    }

}
