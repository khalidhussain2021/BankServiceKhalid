package com.hcl.khalid.service;

import java.util.List;

import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.entities.Customer;
import com.hcl.khalid.exception.CustomerAlreadyExistException;
import com.hcl.khalid.exception.CustomerNotFoundException;

public interface CustomerService {
	Customer createCustomerWithAccountAndDebitCard(CustomerDto customerDTO) throws CustomerAlreadyExistException;
	Customer createCustomerWithAccountAndCreditCard(CustomerDto customerDto) throws CustomerAlreadyExistException;
	CustomerDto getCustomerById(Long id) throws CustomerNotFoundException;
    List<CustomerDto> getAllCustomers();
    CustomerDto updateCustomer(Long id, CustomerDto customerDTO) throws CustomerNotFoundException;
    void deleteCustomer(Long id) throws CustomerNotFoundException;
}
