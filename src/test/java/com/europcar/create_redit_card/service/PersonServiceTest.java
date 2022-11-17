/*
package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.entity.PersonEntity;
import com.europcar.create_redit_card.mapper.PersonMapper;
import com.europcar.create_redit_card.repository.IPersonRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {

    @Mock
    private  IPersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private  PersonMapper personMapper;

    @Test
    void addPersonTest(){
        //given
        PersonEntity personEntity = PersonEntity.builder()
                                    .email("test@gmail.com")
                                    .firstName("firstname")
                                    .lastName("lastname")
                                    .creditCardEntityList(null)
                                    .build();
        Person person = Person.builder()
                        .email("test@gmail.com")
                        .firstName("firstname")
                        .lastName("lastname")
                        .build();

        //when
        Mockito.when(personMapper.dtoToPersonEntity(person)).thenReturn(personEntity);
        Mockito.when(personRepository.save(personEntity)).thenReturn((personEntity));
        Mockito.when(personMapper.personEntityToDto(personEntity)).thenReturn(person);
        Person personTest = personService.addPerson(person);
        //then
        Assertions.assertThat(personTest).isNotNull();

    }





}*/
