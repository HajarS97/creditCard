package com.europcar.create_redit_card.exception;



public class ValidationException extends RuntimeException{

    public static final String CARD_ALREADY_EXIST = "This card already exist";
    public static final String CARD_DOESNT_EXIST = "This card doesnt exist";
    public static final String PERSON_DOESNT_LINKED_TO_CARD = "aucune persoone sur cette carte";
    public static final String PERSON_DOESNT_EXISTD = "Person doesnt exist ";
    public static final String CVC_WRONG_FORMAT = "Wrong cvc format ";
    public static final String CREDIT_CARD_NUMBER_WRONG_FORMAT = "Wrong card format";
    public static final String CREDIT_CARD_BLOCKED = "Credit Card blocked";
    public static final String WRONG_DATE = "Wrong date";
    public static final String WRONG_DATE_FORMAT = "Wrong date format";

    public ValidationException(String message){
        super(message);
    }
}

