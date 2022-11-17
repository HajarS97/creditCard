package com.europcar.create_redit_card.repository;
import com.europcar.create_redit_card.entity.PersonEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IPersonRepository extends CrudRepository<PersonEntity,Long> {

}
