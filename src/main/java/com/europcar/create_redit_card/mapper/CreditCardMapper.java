package com.europcar.create_redit_card.mapper;
import com.europcar.create_redit_card.dto.CreditCardDto;
import com.europcar.create_redit_card.entity.CreditCardEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface CreditCardMapper {

    CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCardDto creditCardToDto(CreditCardEntity card);
    CreditCardEntity dtoToCreditCard(CreditCardDto creditCardDto);


}
