package com.europcar.create_redit_card.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SendEmailResponseTest {

    private SendEmailResponse sendEmailResponse;

    @BeforeEach
    private void setUp(){
        sendEmailResponse=SendEmailResponse.builder().build();
    }

    @Test
    void messageTest(){
        sendEmailResponse.setMessage("message");
        Assertions.assertSame("message" , sendEmailResponse.getMessage());
    }
}
