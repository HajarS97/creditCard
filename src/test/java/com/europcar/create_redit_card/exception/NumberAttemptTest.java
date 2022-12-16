package com.europcar.create_redit_card.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NumberAttemptTest {

    NumberAttempt numberAttempt;

    @BeforeEach
    public void setUp() {
        numberAttempt = NumberAttempt.builder()
                .build();
    }

    @Test
    void testId() {
        numberAttempt.setId("id");
        Assertions.assertSame("id", numberAttempt.getId());
    }

    @Test
    void testcardNumber() {
        numberAttempt.setNbTentatives(1);
        Assertions.assertSame(1, numberAttempt.getNbTentatives());
    }

    @Test
    void testMessage() {
        numberAttempt.setMessage("message");
        Assertions.assertSame("message", numberAttempt.getMessage());
    }
}
