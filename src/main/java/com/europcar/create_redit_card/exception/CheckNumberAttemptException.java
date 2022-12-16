package com.europcar.create_redit_card.exception;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CheckNumberAttemptException extends RuntimeException{

    private final int attempt;

    public CheckNumberAttemptException(String message, int attempt){
        super(message);
        this.attempt=attempt;
    }

}
