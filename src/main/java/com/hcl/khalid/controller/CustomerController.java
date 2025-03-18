package com.hcl.khalid.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.hcl.khalid.entities.Customer;
import com.hcl.khalid.exception.CustomerAlreadyExistException;
import com.hcl.khalid.exception.CustomerNotFoundException;
import com.hcl.khalid.service.CustomerService;
import com.hcl.khalid.util.GenericResponse;
import com.hcl.khalid.util.MessageConstants;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {
	
	@Autowired(required = true)
    private CustomerService customerService;
	
	Logger logger=LoggerFactory.getLogger(CustomerController.class);
	
	@PostMapping("/addCust")
    public ResponseEntity<GenericResponse> createCustomer(@RequestBody CustomerDto customerDTO) throws CustomerAlreadyExistException {
          Customer customerWithAccountAndDebitCard = customerService.createCustomerWithAccountAndDebitCard(customerDTO);
        logger.info("Inside create Customer method()");
        if(null !=customerWithAccountAndDebitCard.getAccounts()) {
        	return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.OK.value())
        			.message(MessageConstants.CUSTOMER_CREATED).status(MessageConstants.SUCCESS).data(customerWithAccountAndDebitCard.getEmail()).build(),HttpStatus.OK);
        }
        logger.info("end create customer method()");
        return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.FORBIDDEN.value())
        		.message(MessageConstants.PROBLEM_IN_CREATEDING_CUSTOMER).status(MessageConstants.ERROR).data("Not a Valid Customer Please check the Field before creating")
        		.build(),HttpStatus.FORBIDDEN);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GenericResponse> getCustomerById(@PathVariable Long id) throws CustomerNotFoundException {
        logger.info("Inside find by id customer get details method()");
    	CustomerDto customerDTO = customerService.getCustomerById(id);
        if (customerDTO != null) {
        	return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.OK.value())
                    .message(MessageConstants.CUSTOMER_CREATED).status(MessageConstants.SUCCESS).data(customerDTO).build(), HttpStatus.OK);
        }
        logger.info("method end customer serch");
        return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.FORBIDDEN.value())
                .message(MessageConstants.CUSTOMER_NOT_FOUND).status(MessageConstants.ERROR).data("customer is not exist in given id"+id).build(), HttpStatus.NOT_FOUND);
    }

    @GetMapping("/allCustomer")
    public ResponseEntity<GenericResponse> getAllCustomers() {
    	logger.info("Inside method find all customer method()");
        List<CustomerDto> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.OK.value())
                .message(MessageConstants.CUSTOMER_CREATED).status(MessageConstants.SUCCESS).data(customers).build(), HttpStatus.OK);
    }

    @PutMapping("modify/{id}")
    public ResponseEntity<GenericResponse> updateCustomer(@PathVariable Long id, @RequestBody CustomerDto customerDTO) throws CustomerNotFoundException {
        logger.info("Inside customer update method inside");
    	CustomerDto updatedCustomer = customerService.updateCustomer(id, customerDTO);
        if (updatedCustomer != null) {
        	return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.OK.value())
                    .message(MessageConstants.CUSTOMER_CREATED).status(MessageConstants.SUCCESS).data(updatedCustomer).build(), HttpStatus.OK);        
        	}
        return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.FORBIDDEN.value())
                .message(MessageConstants.PROBLEM_IN_UPDATING_CUSTOMER).status(MessageConstants.ERROR).data("Not a Valid Customer Id Please check the Field before Updating ").build(), HttpStatus.NOT_ACCEPTABLE);   
        }

    @DeleteMapping("del/{id}")
    public ResponseEntity<GenericResponse> deleteCustomer(@PathVariable Long id) throws CustomerNotFoundException {
    	logger.info("Inside in to delete method()");
    	if(id !=null) {
    		customerService.deleteCustomer(id);
    		return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.OK.value())
                    .message(MessageConstants.CUSTOMER_DELETED).status(MessageConstants.SUCCESS).data(id).build(), HttpStatus.OK);
    	}
		return new ResponseEntity<>(GenericResponse.builder().statusCode(HttpStatus.FORBIDDEN.value())
                .message(MessageConstants.PROBLEM_IN_CREATEDING_CUSTOMER).status(MessageConstants.ERROR).data("Customer is not Present with id"+id).build(), HttpStatus.BAD_REQUEST);
       
    }
}
