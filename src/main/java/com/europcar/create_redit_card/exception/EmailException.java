package com.europcar.create_redit_card.exception;


import lombok.*;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class EmailException extends RuntimeException{

    private String id;
    public static final String ID_INTERNAL_ERROR="ID_INTERNAL_ERROR";
    public static final String INTERNAL_ERROR = "INTERNAL_ERROR";

    public EmailException(String message,String id){
        super(message);
        this.id=id;
    }


}
