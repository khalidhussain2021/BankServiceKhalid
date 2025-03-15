package com.hcl.khalid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hcl.khalid.dto.CreditCardDTO;
import com.hcl.khalid.entities.CreditCard;

@Mapper(componentModel = "spring")
public interface CreditCardMapper {
	CreditCardMapper INSTANCE = Mappers.getMapper(CreditCardMapper.class);

    CreditCard toEntity(CreditCardDTO dto);

    CreditCardDTO toDTO(CreditCard entity);
}
