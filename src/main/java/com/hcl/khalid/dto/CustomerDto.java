package com.hcl.khalid.dto;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerDto {
    private String email;   
    private String name;  
    private String phoneNumber;   
    private List<AccountDto> accounts = new ArrayList<>();

}
