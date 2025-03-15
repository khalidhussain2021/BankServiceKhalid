package com.hcl.khalid.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hcl.khalid.dto.CustomerDto;
import com.hcl.khalid.exception.CustomerNotFoundException;
import com.hcl.khalid.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired(required = true)
    private CustomerService customerService;
	
	@PostMapping("/addCust")
    public ResponseEntity<CustomerDto> createCustomer(@RequestBody CustomerDto customerDTO) {
        CustomerDto createdCustomer = customerService.createCustomer(customerDTO);
        return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CustomerDto> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        CustomerDto customerDTO = customerService.getCustomerById(id);
        if (customerDTO != null) {
            return new ResponseEntity<>(customerDTO,HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }

    @GetMapping("/allCustomer")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        List<CustomerDto> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers,HttpStatus.OK);
    }

    @PutMapping("modify/{id}")
    public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDTO) throws CustomerNotFoundException {
        CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDTO);
        if (updatedCustomer != null) {
            return new ResponseEntity<>(updatedCustomer,HttpStatus.ACCEPTED);
        }
        throw new CustomerNotFoundException("Customer is not present");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
        customerService.deleteCustomer(id);
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}
