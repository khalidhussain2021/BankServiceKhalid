package com.hcl.khalid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreditCardDTO {
	
    private String cardNumber;
    private String expiryDate;
    private double creditLimit;
    private AccountDto account;
}
