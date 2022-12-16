package com.europcar.create_redit_card.dto;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class Person {

    private String firstName;
    private String lastName;
    private String email;
}
