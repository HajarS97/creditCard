package com.europcar.create_redit_card.mapper;

import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.view.CreditCardRequest;
import com.europcar.create_redit_card.view.CreditCardResponse;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ViewMapper {

    ViewMapper INSTANCE = Mappers.getMapper(ViewMapper.class);

    CreditCardDto requestToDto(CreditCardRequest creditCardRequest);
    CreditCardResponse dtoToResponse(CreditCardDto creditCardDto);
}
