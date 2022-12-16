package com.europcar.create_redit_card.entity;

import com.europcar.create_redit_card.dto.CreditCardStatus;
import lombok.*;
import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class CreditCardEntity {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    @Column(unique = true)
    private String cardNumber;
    private String expiredDate;
    private String creditCardVerification;
    private int maxTentative;
    @Enumerated(value=EnumType.STRING)
    private CreditCardStatus creditCardStatus;
    private Date blockDate;

    @ManyToOne(fetch = FetchType.LAZY)
    private PersonEntity personEntity;

    public void incrementTentativeNumber(){
        this.maxTentative++;
    }

    public boolean isEqualCvc(String cvc){
        return this.getCreditCardVerification().equals(cvc);
    }
}
