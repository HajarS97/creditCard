package com.europcar.create_redit_card.messaging;

import com.europcar.create_redit_card.dto.NotificationValue;
import com.europcar.create_redit_card.messaging.producer.NotificationProducerEvent;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.kafka.common.TopicPartition;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.doAnswer;
import static org.mockito.Mockito.mock;

@ExtendWith(MockitoExtension.class)
class ProducerTest {

    NotificationValue notificationValue;
    NotificationValue notificationValueEvent;
    private final static String topicName = "topictest";
    @Mock
    private KafkaTemplate<String, NotificationValue> kafkaTemplate;
    @InjectMocks
    private NotificationProducerEvent kafkaProducerService;
    @BeforeEach
    void setUp(){
        notificationValue=NotificationValue.builder()
                .firstName("firstname")
                .lastName("lastname")
                .expiryDate("12/12/2032")
                .cardNumber("12341213")
                .email("test@gmail.com")
                .build();
        notificationValueEvent=NotificationValue.builder()
                .firstName("firstnameevent")
                .lastName("lastnameEvent")
                .expiryDate("12/12/2032")
                .cardNumber("12341213")
                .email("test@gmail.com")
                .build();
    }

    @Test
    void producerOnSuccessTest(){
        // Given
        ReflectionTestUtils.setField(kafkaProducerService, "topicName", "topictest");
        ListenableFuture<SendResult<String, NotificationValue>> responseFuture = mock(ListenableFuture.class);
        SendResult<String, Object> sendResult = mock(SendResult.class);
        RecordMetadata recordMetadata = new RecordMetadata(new TopicPartition("topictest", 1), 1, 0L, 0L, 0L, 0, 0);
        given(sendResult.getRecordMetadata()).willReturn(recordMetadata);

        Mockito.when(kafkaTemplate.send("topictest", notificationValue)).thenReturn(responseFuture);
        doAnswer(invocationOnMock -> {
            ListenableFutureCallback listenableFutureCallback = invocationOnMock.getArgument(0);
            listenableFutureCallback.onSuccess(sendResult);
            Assertions.assertEquals(1, sendResult.getRecordMetadata().offset());
            Assertions.assertEquals(1, sendResult.getRecordMetadata().partition());
            return null;
        }).when(responseFuture).addCallback(any(ListenableFutureCallback.class));

        kafkaProducerService.sendMessage(notificationValue);

    }

    @Test
    void producerOnFailerTest(){
        // Given
        Throwable throwable = mock(Throwable.class);
        ReflectionTestUtils.setField(kafkaProducerService, "topicName", "topictest");
        ListenableFuture<SendResult<String, NotificationValue>> responseFuture = mock(ListenableFuture.class);

        Mockito.when(kafkaTemplate.send("topictest", notificationValue)).thenReturn(responseFuture);
        doAnswer(invocationOnMock -> {
            ListenableFutureCallback listenableFutureCallback = invocationOnMock.getArgument(0);
            listenableFutureCallback.onFailure(throwable);
            return null;
        }).when(responseFuture).addCallback(any(ListenableFutureCallback.class));

        kafkaProducerService.sendMessage(notificationValue);
        org.assertj.core.api.Assertions.assertThat(throwable).isNotNull();

    }

}
