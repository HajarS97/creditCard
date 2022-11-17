package com.europcar.create_redit_card.service;

import com.europcar.create_redit_card.dto.CreditCardDto;


public interface INotificationService {

    void sendNotification(CreditCardDto creditCardDto);
}

