package com.europcar.create_redit_card.client;

import com.europcar.create_redit_card.client.config.NotificationErrorDecoder;
import com.europcar.create_redit_card.dto.NotificationValue;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

import static com.europcar.create_redit_card.constants.UrlConstant.CLIENT_URL;

@FeignClient(name="svtNotification", url = CLIENT_URL, configuration = {ClientConfiguration.class, NotificationErrorDecoder.class})
public interface NotificationClient {

    @PostMapping("/notification/sendEmail")
    NotificationValue sendEmail(NotificationValue notificationValue);

}
