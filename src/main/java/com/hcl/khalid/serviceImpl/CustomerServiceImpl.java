package com.hcl.khalid.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.khalid.dto.AccountDto;
import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.entities.Account;
import com.hcl.khalid.entities.AccountType;
import com.hcl.khalid.entities.Customer;
import com.hcl.khalid.exception.CustomerNotFoundException;
import com.hcl.khalid.mapper.CustomerMapper;
import com.hcl.khalid.repository.AccountRepository;
import com.hcl.khalid.repository.CustomerRepository;
import com.hcl.khalid.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private AccountRepository accountRepository;
	
//	@Autowired
//	private TransactionRepository transactionRepository;
//	
	

	
	@Override
	public CustomerDto createCustomer(CustomerDto customerDTO) {
		Customer customer = customerMapper.toEntity(customerDTO);
		
		Customer saveCustomer = customerRepository.save(customer);
		return customerMapper.toDto(saveCustomer);
	}
	@Override
	public CustomerDto getCustomerById(Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer is not present please Register..");
		}
		return customer.map(customerMapper::toDto).orElse(null);
	}
	@Override
	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(customerMapper::toDto)
				.collect(Collectors.toList());
	}
	@Override
	public CustomerDto updateCustomer(Long id, CustomerDto customerDTO) throws CustomerNotFoundException {
		 Optional<Customer> existingCustomer = customerRepository.findById(id);
	                
		      Customer customer;
	        if (existingCustomer.isPresent()) {
	            // Existing Customer Found, update only non-null values
	            customer = existingCustomer.get();

	            if (customerDTO.getEmail() != null) {
	                customer.setEmail(customerDTO.getEmail());
	            }
	            if (customerDTO.getName() != null) {
	                customer.setName(customerDTO.getName());
	            }
	            if (customerDTO.getPhoneNumber() != null) {
	                customer.setPhoneNumber(customerDTO.getPhoneNumber());
	            }

	            // If accounts list is provided, update accounts as well
	            if (customerDTO.getAccounts() != null && !customerDTO.getAccounts().isEmpty()) {
	                customer.getAccounts().clear();
	                customerDTO.getAccounts().forEach(accountDTO -> {
	                    Account account = new Account();
	                    account.setAccountNumber(accountDTO.getAccountNumber());
	                    account.setAccountType(Enum.valueOf(AccountType.class, accountDTO.getAccountType()));
	                    account.setCustomer(customer);
	                    customer.getAccounts().add(account);
	                });
	            }
	        }else {
	        	customer = new Customer();
	            customer.setEmail(customerDTO.getEmail());
	            customer.setName(customerDTO.getName());
	            customer.setPhoneNumber(customerDTO.getPhoneNumber());

	            customerDTO.getAccounts().forEach(accountDTO -> {
	                Account account = new Account();
	                account.setAccountNumber(accountDTO.getAccountNumber());
	                account.setAccountType(Enum.valueOf(AccountType.class, accountDTO.getAccountType()));
	                account.setCustomer(customer);
	                customer.getAccounts().add(account);
	            });
	        }
	        customerRepository.save(customer);
		return customerMapper.toDto(customer);
	      
	}
	@Override
	public void deleteCustomer(Long id) throws CustomerNotFoundException {
		Optional<Customer> customerId = customerRepository.findById(id);
		if(customerId.isPresent()) {
			customerRepository.deleteById(id);
		}else {
			throw new CustomerNotFoundException("customer is not found with "+id);
		}
    }
	
	
}
