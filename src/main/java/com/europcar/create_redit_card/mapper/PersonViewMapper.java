package com.europcar.create_redit_card.mapper;
import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.view.PersonRequest;
import com.europcar.create_redit_card.view.PersonResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface PersonViewMapper {

    PersonViewMapper INSTANCE = Mappers.getMapper(PersonViewMapper.class);

    Person requestToDto(PersonRequest personRequest);
    PersonResponse dtoToResponse(Person person);
}
