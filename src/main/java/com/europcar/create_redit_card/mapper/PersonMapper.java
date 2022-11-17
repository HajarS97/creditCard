package com.europcar.create_redit_card.mapper;
import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.entity.PersonEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PersonMapper {

    Person personEntityToDto(PersonEntity personEntity);
    PersonEntity dtoToPersonEntity(Person person);


}
