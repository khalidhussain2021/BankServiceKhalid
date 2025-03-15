package com.hcl.khalid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hcl.khalid.dto.DebitCardDTO;
import com.hcl.khalid.entities.DebitCard;

@Mapper(componentModel = "spring")
public interface DebitCardMapper {
	DebitCardMapper INSTANCE = Mappers.getMapper(DebitCardMapper.class);

    DebitCard toEntity(DebitCardDTO dto);

    DebitCardDTO toDTO(DebitCard entity);
}
