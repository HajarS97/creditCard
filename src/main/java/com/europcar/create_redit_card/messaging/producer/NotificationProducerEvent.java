package com.europcar.create_redit_card.messaging.producer;

import com.europcar.create_redit_card.dto.NotificationValue;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Service
@Slf4j
public class NotificationProducerEvent implements INotificationProducerEvent{

    @Autowired
    private KafkaTemplate<String, NotificationValue> kafkaTemplate;

    @Value("${spring.kafka.producer.topic.name}")
    private String topicName;

    @Override
    public void sendMessage(NotificationValue notificationValue) {

        ListenableFuture<SendResult<String, NotificationValue>> future =
                kafkaTemplate.send(topicName,notificationValue);

        future.addCallback(new ListenableFutureCallback<SendResult<String, NotificationValue>>() {

            @Override
            public void onSuccess(SendResult<String, NotificationValue> result) {
                log.info("Sent message=[" + notificationValue +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            }
            @Override
            public void onFailure(Throwable ex) {
                log.info("Unable to send message=["
                        + notificationValue + "] due to : " + ex.getMessage());
            }
        });
    }
}
