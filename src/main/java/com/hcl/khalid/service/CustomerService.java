package com.hcl.khalid.service;

import java.util.List;

import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.exception.CustomerNotFoundException;

public interface CustomerService {
	CustomerDto createCustomer(CustomerDto customerDTO);
    CustomerDto getCustomerById(Long id) throws CustomerNotFoundException;
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(Long id, CustomerDto customerDTO) throws CustomerNotFoundException;
    void deleteCustomer(Long id) throws CustomerNotFoundException;
}
