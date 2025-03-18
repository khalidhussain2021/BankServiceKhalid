package com.hcl.khalid.mapper;

import org.springframework.stereotype.Component;

import com.hcl.khalid.dto.AccountDto;
import com.hcl.khalid.entities.Account;

@Component
public class AccountMapper {
	
//    // Convert Entity to DTO
    public static AccountDto toDto(Account entity) {
        if (entity == null) {
            return null;
        }
        AccountDto dto = new AccountDto();
        dto.setAccountType(entity.getAccountType().name());
        dto.setAccountNumber(entity.getAccountNumber());
        return dto;
    }
}
