package com.europcar.create_redit_card.repository;
import com.europcar.create_redit_card.entity.CreditCardEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ICreditCardRepository extends CrudRepository<CreditCardEntity,Long> {

   Optional<CreditCardEntity> findBycardNumber(String cardNumber);
}
