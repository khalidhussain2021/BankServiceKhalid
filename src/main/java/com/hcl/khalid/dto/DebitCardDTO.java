package com.hcl.khalid.dto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class DebitCardDTO {
	
    private String cardNumber;
    private LocalDate expiryDate;
}
