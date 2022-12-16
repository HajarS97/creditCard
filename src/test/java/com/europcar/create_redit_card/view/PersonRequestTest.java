package com.europcar.create_redit_card.view;

import com.europcar.create_redit_card.view.PersonRequest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonRequestTest {

        private PersonRequest personRequest;

        @BeforeEach
        public void setUp() {
            personRequest = PersonRequest.builder()
                    .build();
        }


    @Test
    void testEmail() {
        personRequest.setEmail("test@gmail.com");
        Assertions.assertSame("test@gmail.com" , personRequest.getEmail());
    }

    @Test
    void testFirstname() {
        personRequest.setFirstName("firstname");
        Assertions.assertSame("firstname" , personRequest.getFirstName());
    }

    @Test
    void testLestname() {
        personRequest.setLastName("lastname");
        Assertions.assertSame("lastname" , personRequest.getLastName());
    }

}
