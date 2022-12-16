package com.europcar.create_redit_card.controller;

import com.europcar.create_redit_card.controllers.PersonController;
import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.mapper.PersonViewMapper;
import com.europcar.create_redit_card.service.PersonServiceImpl;
import com.europcar.create_redit_card.view.PersonRequest;
import com.europcar.create_redit_card.view.PersonResponse;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonControllerTest {

    @Mock
    PersonServiceImpl personService;
    @InjectMocks
    PersonController personController;
    @Mock
    PersonViewMapper personViewMapper;
    PersonRequest personRequest;
    PersonResponse personResponse;
    Person person;

    @BeforeEach
    public void setUp() {
        personRequest = PersonRequest.builder()
                .email("test@gmail.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();
        person = Person.builder()
                .email("test@gmail.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();
        personResponse = PersonResponse.builder()
                .email("test@gmail.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();
    }

    @Test
    void creatPersonControllerTest(){

        //when
        Mockito.when(personViewMapper.requestToDto(personRequest)).thenReturn(person);
        Mockito.when(personService.addPerson(person)).thenReturn(person);
        Mockito.when(personViewMapper.dtoToResponse(person)).thenReturn(personResponse);

        //then
        Assertions.assertThat(personController.createPerson(personRequest)).isNotNull();
        Assertions.assertThat(personController.createPerson(personRequest)).isEqualTo(personResponse);
    }
}


