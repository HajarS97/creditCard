package com.europcar.create_redit_card.dto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class NotificationValueTest {

    private NotificationValue notificationValue;
    private NotificationValue notificationValueTest;

    @BeforeEach
    public void setUp() {
        notificationValue=NotificationValue.builder()
                .cardNumber("12345668787")
                .email("test@gmail.com")
                .expiryDate("12/11/2090")
                .firstName("firstname")
                .lastName("lastname")
                .build();
        notificationValueTest = NotificationValue.builder().
                cardNumber("12345668787")
                .email("test@gmail.com")
                .expiryDate("12/11/2090")
                .firstName("firstname")
                .lastName("lastname")
                .build();
    }

    @Test

    void testEmail() {
        notificationValue.setEmail("test@gmail.com");
        Assertions.assertSame("test@gmail.com" , notificationValue.getEmail());
    }

    @Test
    void testFirstname() {
        notificationValue.setFirstName("firstname");
        Assertions.assertSame("firstname" , notificationValue.getFirstName());
    }

    @Test
    void testLestname() {
        notificationValue.setLastName("lastname");
        Assertions.assertSame("lastname" , notificationValue.getLastName());
    }

    @Test
    void testCardNumber() {
        notificationValue.setCardNumber("12345668787");
        Assertions.assertSame("12345668787" , notificationValue.getCardNumber());
    }

    @Test
    void testExpiryDate() {
        notificationValue.setExpiryDate("12/11/2090");
        Assertions.assertSame("12/11/2090" , notificationValue.getExpiryDate());
    }

    @Test
    void testEqual() {
        boolean equal = notificationValue.equals(notificationValueTest);
        Assertions.assertTrue(equal);
    }

    @Test
    void testHashCodeForEqualObjects() {

        int hashCode1 = notificationValue.hashCode();
        int hashCode2 = notificationValueTest.hashCode();

        org.assertj.core.api.Assertions.assertThat(hashCode1).isEqualTo(hashCode2);
    }
}
