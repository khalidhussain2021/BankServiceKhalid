package com.hcl.khalid.mapper;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.entities.Account;
import com.hcl.khalid.entities.AccountType;
import com.hcl.khalid.entities.Customer;

@Component
public class CustomerMapper {
	// Convert DTO to Entity
	public Customer toEntity(CustomerDto dto) {
        Customer customer = new Customer();
        customer.setEmail(dto.getEmail());
        customer.setName(dto.getName());
        customer.setPhoneNumber(dto.getPhoneNumber());

        List<Account> accounts = dto.getAccounts().stream()
            .map(accountDTO -> {
                Account account = new Account();
                account.setAccountNumber(accountDTO.getAccountNumber());
                account.setAccountType(Enum.valueOf(AccountType.class, accountDTO.getAccountType()));
                account.setCustomer(customer);
                return account;
            }).collect(Collectors.toList());
        customer.setAccounts(accounts);
        return customer;
    }

    // Convert Entity to DTO
    public CustomerDto toDto(Customer entity) {
        if (entity == null) {
            return null;
        }
        CustomerDto dto = new CustomerDto();
        dto.setEmail(entity.getEmail());
        dto.setName(entity.getName());
        dto.setPhoneNumber(entity.getPhoneNumber());

        // Convert List of Account entities to List of AccountDto
        if (entity.getAccounts() != null) {
            dto.setAccounts(entity.getAccounts().stream()
                    .map(AccountMapper::toDto)
                    .collect(Collectors.toList()));
        }
        return dto;
    }
}
