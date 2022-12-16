
package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.entity.PersonEntity;
import com.europcar.create_redit_card.mapper.PersonMapper;
import com.europcar.create_redit_card.repository.IPersonRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class PersonServiceTest {

    @Mock
    private  IPersonRepository personRepository;
    @InjectMocks
    private PersonServiceImpl personService;
    @Mock
    private  PersonMapper personMapper;
    private PersonEntity personEntity;


    @BeforeEach
    public void setUp() {
        personEntity = PersonEntity.builder()
                .build();
    }


    @Test
    void testEmail() {
        personEntity.setEmail("test@gmail.com");
        Assertions.assertSame("test@gmail.com" , personEntity.getEmail());
    }

    @Test
    void testFirstname() {
        personEntity.setFirstName("firstname");
        Assertions.assertSame("firstname" , personEntity.getFirstName());
    }

    @Test
    void testLestname() {
        personEntity.setLastName("lastname");
        Assertions.assertSame("lastname" , personEntity.getLastName());
    }


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
        org.assertj.core.api.Assertions.assertThat(personTest).isNotNull();

    }








}
