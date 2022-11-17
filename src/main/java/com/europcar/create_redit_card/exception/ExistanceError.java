package com.europcar.create_redit_card.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ExistanceError {

    public static final String ID_EXISTANCE_ERROR ="ID_EXISTANCE_ERROR ";
    private String id;
    private String message;
    private String cardNumber;

}
