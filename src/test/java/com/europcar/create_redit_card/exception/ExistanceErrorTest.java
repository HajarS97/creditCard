package com.europcar.create_redit_card.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ExistanceErrorTest {

    ExistanceError existanceError;

    @BeforeEach
    public void setUp() {
        existanceError = ExistanceError.builder()
                .build();
    }

    @Test
    void testId() {
        existanceError.setId("id");
        Assertions.assertSame("id", existanceError.getId());
    }

    @Test
    void testcardNumber() {
        existanceError.setCardNumber("123456789");
        Assertions.assertSame("123456789", existanceError.getCardNumber());
    }

    @Test
    void testMessage() {
        existanceError.setMessage("message");
        Assertions.assertSame("message", existanceError.getMessage());
    }
}
