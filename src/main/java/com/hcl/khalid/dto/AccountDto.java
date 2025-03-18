package com.hcl.khalid.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String accountType;
    private String accountNumber;
    private DebitCardDTO debitCardDTO;
}
