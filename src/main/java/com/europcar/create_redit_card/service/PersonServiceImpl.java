package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.entity.PersonEntity;
import com.europcar.create_redit_card.mapper.PersonMapper;
import com.europcar.create_redit_card.repository.IPersonRepository;
import org.springframework.stereotype.Service;


@Service
public class PersonServiceImpl implements IPersonService {

    private final IPersonRepository personRepository;
    private final PersonMapper personMapper;

    public PersonServiceImpl(IPersonRepository personRepository,PersonMapper personMapper) {
        this.personRepository=personRepository;
        this.personMapper=personMapper;
    }

    public Person addPerson(Person person){
        PersonEntity personEntity =personRepository.save(personMapper.dtoToPersonEntity(person));
        return personMapper.personEntityToDto(personEntity);
    }


}
