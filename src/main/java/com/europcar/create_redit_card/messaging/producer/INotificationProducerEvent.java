package com.europcar.create_redit_card.messaging.producer;

import com.europcar.create_redit_card.dto.NotificationValue;

public interface INotificationProducerEvent {

    public void sendMessage(NotificationValue notificationValue);
}
