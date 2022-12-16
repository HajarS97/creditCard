package com.europcar.create_redit_card.exception;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ErrorResponseTest {

    ErrorResponse errorResponse;

    @BeforeEach
    public void setUp() {
        errorResponse = ErrorResponse.builder()
                .build();
    }

    @Test
    void testId() {
        errorResponse.setId("id");
        Assertions.assertSame("id", errorResponse.getId());
    }


    @Test
    void testMessage() {
        errorResponse.setMessage("message");
        Assertions.assertSame("message", errorResponse.getMessage());
    }
}
