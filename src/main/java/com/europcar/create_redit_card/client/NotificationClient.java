package com.europcar.create_redit_card.client;

import com.europcar.create_redit_card.client.config.NotificationErrorDecoder;
import com.europcar.create_redit_card.dto.NotificationValue;
import com.europcar.create_redit_card.dto.SendEmailResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(name="svtNotification", url = "${client.url}", configuration =NotificationErrorDecoder.class)
public interface NotificationClient {

    @PostMapping("/notification/sendEmail")
    SendEmailResponse sendEmail(NotificationValue notificationValue);

}
