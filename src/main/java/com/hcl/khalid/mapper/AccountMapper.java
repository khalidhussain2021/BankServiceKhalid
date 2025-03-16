package com.hcl.khalid.mapper;

import org.springframework.stereotype.Component;

import com.hcl.khalid.dto.AccountDto;
import com.hcl.khalid.entities.Account;
import com.hcl.khalid.entities.AccountType;
import com.hcl.khalid.entities.Customer;

@Component
public class AccountMapper {
	
    // Convert Entity to DTO
    public static AccountDto toDto(Account entity) {
        if (entity == null) {
            return null;
        }
        AccountDto dto = new AccountDto();
        dto.setAccountType(entity.getAccountType().name());
        dto.setAccountNumber(entity.getAccountNumber());
        // Avoid circular dependency
        if (entity.getCustomer() != null) {
            dto.setCustomer(null);
        }
        return dto;
    }
}
