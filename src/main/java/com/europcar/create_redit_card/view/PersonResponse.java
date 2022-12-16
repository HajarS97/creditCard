package com.europcar.create_redit_card.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PersonResponse {

    private String firstName;
    private String lastName;
    private String email;
}
