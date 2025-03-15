package com.hcl.khalid.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDto {
	private Long transactionId;
    private Double amount;
    private LocalDateTime transactionDate;
    private String transactionType; // DEBIT or CREDIT
    private AccountDto accountdto;
}
