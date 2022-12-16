package com.europcar.create_redit_card.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class NumberAttempt {

    public static final String ID_NUMBER_TENTATIVE_ERROR ="ID_NUMBER_TENTATIVE_ERROR ";
    private String id;
    private String message;
    private int nbTentatives;

}
