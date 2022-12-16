package com.europcar.create_redit_card.entity;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class PersonEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    @OneToMany(fetch = FetchType.LAZY)
    private List<CreditCardEntity> creditCardEntityList;

}
