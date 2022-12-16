package com.europcar.create_redit_card.exception;

import com.europcar.create_redit_card.controllers.ApiExceptionHandler;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
class ApiExceptionHandlerTest {

    private ApiExceptionHandler apiExceptionHandler = new ApiExceptionHandler();


    @Test
    void handleValidationExceptionTest() {

        //given
        ValidationException validationException = new ValidationException(ValidationException.PERSON_DOESNT_EXISTD);

        //when
        ResponseEntity<ErrorResponse> responseResponseEntity = apiExceptionHandler.handleValidationException(validationException);

        //then
        Assertions.assertThat(responseResponseEntity).isNotNull();
        Assertions.assertThat(responseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(responseResponseEntity.getBody().getMessage()).isEqualTo(ValidationException.PERSON_DOESNT_EXISTD);

    }

    @Test
    void handleExistanceExceptionTest() {

        //given
        CreditCardExistanceException creditCardExistanceException = new CreditCardExistanceException(CreditCardExistanceException.CARD_ALREADY_EXIST, "4761739001010019");

        //when
        ResponseEntity<ExistanceError> responseResponseEntity = apiExceptionHandler.handleExistanceException(creditCardExistanceException);

        //then
        Assertions.assertThat(responseResponseEntity).isNotNull();
        Assertions.assertThat(responseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(responseResponseEntity.getBody().getMessage()).isEqualTo(CreditCardExistanceException.CARD_ALREADY_EXIST);
    }

    @Test
    void handleCheckServiceExceptionTest() {

        //given
        CheckNumberAttemptException checkNumberAttemptException = new CheckNumberAttemptException("message", 2);

        //when
        ResponseEntity<NumberAttempt> responseResponseEntity = apiExceptionHandler.handleCheckServiceException(checkNumberAttemptException);

        //then
        Assertions.assertThat(responseResponseEntity).isNotNull();
        Assertions.assertThat(responseResponseEntity.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        Assertions.assertThat(responseResponseEntity.getBody().getMessage()).isEqualTo("message");
    }

}

