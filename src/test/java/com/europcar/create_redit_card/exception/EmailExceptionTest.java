package com.europcar.create_redit_card.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class EmailExceptionTest {
    EmailException emailException;

    @BeforeEach
    public void setUp() {
        emailException = EmailException.builder()
                .build();
    }

    @Test
    void testId() {
        emailException.setId("id");
        Assertions.assertSame("id", emailException.getId());
    }


/*    @Test
    void testMessage() {
        emailException.setMessage("message");
        Assertions.assertSame("message", emailException.getMessage());
    }*/
}
