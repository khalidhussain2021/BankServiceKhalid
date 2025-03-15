package com.hcl.khalid.serviceImpl;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.entities.Customer;
import com.hcl.khalid.exception.CustomerNotFoundException;
import com.hcl.khalid.mapper.CustomerMapper;
import com.hcl.khalid.repository.CustomerRepository;
import com.hcl.khalid.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService{
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	@Qualifier(value = "customerMapper")
	private CustomerMapper customerMapper;
	
//	public CustomerServiceImpl( CustomerMapper customerMapper,CustomerRepository customerRepository) {
//		this.customerMapper=customerMapper;
//		this.customerRepository=customerRepository;
//	}
	
//	@Autowired
//	private AccountRepository accountRepository;
	
//	@Autowired
//	private TransactionRepository transactionRepository;
//	
	

	
	@Override
	public CustomerDto createCustomer(CustomerDto customerDTO) {
		Customer customer = customerMapper.toEntity(customerDTO);
		Customer saveCustomer = customerRepository.save(customer);
		return customerMapper.toDTO(saveCustomer);
	}
	@Override
	public CustomerDto getCustomerById(Long id) throws CustomerNotFoundException {
		Optional<Customer> customer = customerRepository.findById(id);
		if(customer.isEmpty()) {
			throw new CustomerNotFoundException("Customer is not present please Register..");
		}
		return customer.map(customerMapper::toDTO).orElse(null);
	}
	@Override
	public List<CustomerDto> getAllCustomers() {
		return customerRepository.findAll()
				.stream()
				.map(customerMapper::toDTO)
				.collect(Collectors.toList());
	}
	@Override
	public CustomerDto updateCustomer(Long id, CustomerDto customerDTO) throws CustomerNotFoundException {
		 Customer existingCustomer = customerRepository.findById(id)
	                .orElseThrow(() -> new CustomerNotFoundException("Customer with ID " + id + " not found"));
	        existingCustomer.setName(customerDTO.getName());
	        existingCustomer.setEmail(customerDTO.getEmail());
	        existingCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
	        Customer updatedCustomer = customerRepository.save(existingCustomer);
		return customerMapper.toDTO(updatedCustomer);
	}
	@Override
	public void deleteCustomer(Long id) throws CustomerNotFoundException {
		if (!customerRepository.existsById(id)) {
            throw new CustomerNotFoundException("Customer with ID " + id + " not found");
        }
        customerRepository.deleteById(id);
    }
	
	
}
