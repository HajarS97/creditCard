package com.europcar.create_redit_card.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@AllArgsConstructor
@Builder
public class EmailException extends RuntimeException{
    private final String id;
    private final String message;


}
