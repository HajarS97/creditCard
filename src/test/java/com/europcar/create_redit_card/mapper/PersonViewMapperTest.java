package com.europcar.create_redit_card.mapper;

import com.europcar.create_redit_card.dto.Person;
import com.europcar.create_redit_card.view.PersonRequest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class PersonViewMapperTest {

    @Test
    void requestToDto() {
        //given
        PersonRequest personRequest = PersonRequest.builder()
                .email("test@gmail.com")
                .firstName("firstname")
                .lastName("lastname")
                .build();

        //when
        Person person = PersonViewMapper.INSTANCE.requestToDto(personRequest);

        //then
        Assertions.assertThat(person).isNotNull();
        Assertions.assertThat(person.getEmail()).isEqualTo("test@gmail.com");
        Assertions.assertThat(person.getFirstName()).isEqualTo("firstname");
        Assertions.assertThat(person.getLastName()).isEqualTo("lastname");

    }
}
