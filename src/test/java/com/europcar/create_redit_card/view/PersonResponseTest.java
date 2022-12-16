package com.europcar.create_redit_card.view;

import com.europcar.create_redit_card.view.PersonResponse;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class PersonResponseTest {

        private PersonResponse personResponse;


        @BeforeEach
        public void setUp() {
            personResponse = PersonResponse.builder()
                    .build();
        }

        @Test
        void testEmail() {
                personResponse.setEmail("test@gmail.com");
                Assertions.assertSame("test@gmail.com" , personResponse.getEmail());
        }

        @Test
        void testFirstname() {
                personResponse.setFirstName("firstname");
                Assertions.assertSame("firstname" , personResponse.getFirstName());
        }

        @Test
        void testLestname() {
                personResponse.setLastName("lastname");
                Assertions.assertSame("lastname" , personResponse.getLastName());
        }

}
