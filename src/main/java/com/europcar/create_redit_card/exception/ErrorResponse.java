package com.europcar.create_redit_card.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ErrorResponse{

    public static final String ID_VALIDATION_ERROR = "VALIDATION ERROR";
    private String id;
    private String message;

}
