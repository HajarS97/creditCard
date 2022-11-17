package com.europcar.create_redit_card.controllers;
import com.europcar.create_redit_card.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(ValidationException validationException){
        ErrorResponse errorResponse = new ErrorResponse(ErrorResponse.ID_VALIDATION_ERROR,validationException.getMessage());
        return new ResponseEntity<>(errorResponse,HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(CreditCardExistanceException.class)
    public ResponseEntity<ExistanceError> handleExistanceException(CreditCardExistanceException creditCardExistanceException){
        ExistanceError existanceError = new ExistanceError(ExistanceError.ID_EXISTANCE_ERROR,creditCardExistanceException.getMessage(),creditCardExistanceException.getCardNumber());
        return new ResponseEntity<>(existanceError,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(CheckNumberAttemptException.class)
    public ResponseEntity<NumberAttempt> handleCheckServiceException(CheckNumberAttemptException checkNumberAttemptException){
        NumberAttempt numberTentativeException = new NumberAttempt(NumberAttempt.ID_NUMBER_TENTATIVE_ERROR,checkNumberAttemptException.getMessage(),checkNumberAttemptException.getAttempt());
        return new ResponseEntity<>(numberTentativeException,HttpStatus.BAD_REQUEST);
    }






}

