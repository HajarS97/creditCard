package com.europcar.create_redit_card.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


@ExtendWith(MockitoExtension.class)
class PersonTest {

    private Person person;

    @BeforeEach
    public void setUp() {
        person = Person.builder().build();
    }

    @Test

    void testEmail() {
        person.setEmail("test@gmail.com");
        Assertions.assertSame("test@gmail.com" , person.getEmail());
    }

    @Test
    void testFirstname() {
        person.setFirstName("firstname");
        Assertions.assertSame("firstname" , person.getFirstName());
    }

    @Test
    void testLestname() {
        person.setLastName("lastname");
        Assertions.assertSame("lastname" , person.getLastName());
    }

}

