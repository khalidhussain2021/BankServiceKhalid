package com.hcl.khalid.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hcl.khalid.dto.AccountDto;
import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.dto.DebitCardDTO;
import com.hcl.khalid.entities.Account;
import com.hcl.khalid.entities.AccountType;
import com.hcl.khalid.entities.Customer;
import com.hcl.khalid.entities.DebitCard;
import com.hcl.khalid.exception.CustomerAlreadyExistException;
import com.hcl.khalid.exception.CustomerNotFoundException;
import com.hcl.khalid.mapper.CustomerMapper;
import com.hcl.khalid.repository.AccountRepository;
import com.hcl.khalid.repository.CustomerRepository;
import com.hcl.khalid.repository.DebitCardRepository;
import com.hcl.khalid.service.CustomerService;

import jakarta.transaction.Transactional;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	Logger logger=LoggerFactory.getLogger(CustomerServiceImpl.class);
	
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CustomerMapper customerMapper;
	
	@Autowired
	private AccountRepository accountRepository;
	
	@Autowired
	private DebitCardRepository debitCardRepository;
	
//	@Autowired
//	private TransactionRepository transactionRepository;
//	
	

	@Transactional
	@Override
	public Customer createCustomerWithAccountAndDebitCard(CustomerDto customerDTO) throws CustomerAlreadyExistException {
		if(customerRepository.existsByEmail(customerDTO.getEmail())) {
			throw new CustomerAlreadyExistException("Customer alredady exist with "+customerDTO.getEmail());
		}
		Customer customer = new Customer();
        customer.setEmail(customerDTO.getEmail());
        customer.setName(customerDTO.getName());
        customer.setPhoneNumber(customerDTO.getPhoneNumber());
        
        // Process Accounts
        List<Account> accounts = new ArrayList<>();
        for (AccountDto accountDTO : customerDTO.getAccounts()) {
            // Create Account
            Account account = new Account();
            account.setAccountNumber(accountDTO.getAccountNumber());
            account.setAccountType(Enum.valueOf(AccountType.class, accountDTO.getAccountType()));
            account.setCustomer(customer);

            // Process Debit Card
            DebitCardDTO debitCardDTO = accountDTO.getDebitCardDTO();
            if (debitCardDTO != null) {
                DebitCard debitCard = new DebitCard();
                debitCard.setCardNumber(debitCardDTO.getCardNumber());
                debitCard.setExpiryDate(debitCardDTO.getExpiryDate());
                debitCard.setAccount(account);
                account.setDebitCard(debitCard);
                // Save Debit Card
                debitCard = debitCardRepository.save(debitCard);
            }
            // Save Account
            customer.getAccounts().add(account);
            account = accountRepository.save(account);
        }
        customer.setAccounts(accounts); 
        customerRepository.save(customer);
        return customer;
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

	@Override
	public Customer createCustomerWithAccountAndCreditCard(CustomerDto customerDto) throws CustomerAlreadyExistException {
		if(customerRepository.existsByEmail(customerDto.getEmail())) {
			throw new CustomerAlreadyExistException("Customer alredady exist with "+customerDto.getEmail());
			
		}
		return null;
	}
	
	
}
