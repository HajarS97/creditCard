package com.europcar.create_redit_card.view;

import com.europcar.create_redit_card.view.CheckCardRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)

class CheckCardRequestTest {

    CheckCardRequest checkCardRequest;

    @BeforeEach
    public void setUp() {
        checkCardRequest = CheckCardRequest.builder()
                .build();
    }

    @Test
    void testcardNumber() {
        checkCardRequest.setCardNumber("123456789");
        Assertions.assertSame("123456789", checkCardRequest.getCardNumber());
    }

    @Test
    void testcreditCardVerification() {
        checkCardRequest.setCreditCardVerification("234");
        Assertions.assertSame("234",checkCardRequest.getCreditCardVerification());
    }
}
