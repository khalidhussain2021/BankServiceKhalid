package com.hcl.khalid.mapper;


import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.hcl.khalid.dto.AccountDto;
import com.hcl.khalid.entities.Account;

@Mapper(componentModel = "spring")
public interface AccountMapper {
	AccountMapper INSTANCE = Mappers.getMapper(AccountMapper.class);

    Account toEntity(AccountDto dto);

    AccountDto toDTO(Account entity);
}
