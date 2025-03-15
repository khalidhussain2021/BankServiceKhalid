package com.hcl.khalid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DebitCardDTO {
	private Long cardId;
    private String cardNumber;
    private String expiryDate;
    private double balance;
    private AccountDto account;

}
