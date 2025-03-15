package com.hcl.khalid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {

	private Long accountId;
    private String accountType;
    private double balance;
    private CustomerDto customer;
}
