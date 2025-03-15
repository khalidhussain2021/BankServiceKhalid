package com.hcl.khalid.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hcl.khalid.dto.TransactionDto;
import com.hcl.khalid.entities.Transaction;

@Mapper(componentModel = "spring")
public interface TransactionMapper {
	TransactionMapper INSTANCE = Mappers.getMapper(TransactionMapper.class);

    Transaction toEntity(TransactionDto dto);

    TransactionDto toDTO(Transaction entity);
}
