package com.europcar.create_redit_card.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ErrorResponse{

    public static final String ID_VALIDATION_ERROR = "VALIDATION ERROR";
    private String id;
    private String message;

}
