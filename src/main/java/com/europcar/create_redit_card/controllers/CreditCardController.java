package com.europcar.create_redit_card.controllers;

import com.europcar.create_redit_card.mapper.ViewMapper;
import com.europcar.create_redit_card.service.ICreditCardService;
import com.europcar.create_redit_card.service.INotificationService;
import com.europcar.create_redit_card.view.CheckCardRequest;
import com.europcar.create_redit_card.view.CreditCardRequest;
import com.europcar.create_redit_card.view.CreditCardResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("creditCard")
public class CreditCardController {

    @Autowired
    ICreditCardService creditCardService;
    @Autowired
    ViewMapper viewMapper;
    @Autowired
    INotificationService notificationService;

    @PostMapping("/addCreditCard/{idPerson}")
    public CreditCardResponse createCreditCard(@RequestBody @Valid CreditCardRequest creditCardRequest,@PathVariable Long idPerson){
        return viewMapper.dtoToResponse(creditCardService.createCreditCard(viewMapper.requestToDto(creditCardRequest),idPerson));
    }

    @GetMapping("/checkCreditCardVerification")
    public String checkCreditCardVerification(@RequestBody CheckCardRequest checkCardRequest){
        return creditCardService.checkCreditCardVerification(checkCardRequest.getCardNumber(), checkCardRequest.getCreditCardVerification());
    }


}

