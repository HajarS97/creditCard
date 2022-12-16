package com.europcar.create_redit_card.client;

import com.europcar.create_redit_card.client.config.MockConfig;
import com.europcar.create_redit_card.dto.NotificationValue;
import com.europcar.create_redit_card.exception.EmailException;
import com.europcar.create_redit_card.exception.ValidationException;
import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
@ActiveProfiles("test")
@EnableConfigurationProperties
@WireMockTest(httpPort = 8888)
class clientTest {

    private NotificationValue notificationValue;
    @Autowired
    private NotificationClient notificationClient;

    @BeforeEach
    public void setup() {
        notificationValue= NotificationValue.builder()
                .cardNumber("12345668787")
                .email("test@gmail.com")
                .expiryDate("12/11/2090")
                .firstName("firstname")
                .lastName("lastname")
                .build();
    }

    @Test
    void checkSuccessResponse() throws IOException {

        MockConfig.mockWithSuccess();
        NotificationValue notificationValueResp = notificationClient.sendEmail(notificationValue);
        Assertions.assertEquals("f", notificationValueResp.getFirstName());

    }

    @Test
    void checkWrongResponse() throws IOException {

        MockConfig.mockWithError();
        try {
            notificationClient.sendEmail(notificationValue);
        } catch (EmailException emailException){
            assertNotNull(emailException);
            assertEquals("id", emailException.getId());
            assertEquals("message", emailException.getMessage());
        }
    }

}
