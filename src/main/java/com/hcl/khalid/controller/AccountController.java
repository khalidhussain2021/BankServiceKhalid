package com.hcl.khalid.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {
	
	@PostMapping("/add")
	public ResponseEntity<String> saveDebirtCard() {
		
		return new ResponseEntity<>("str",HttpStatus.CREATED);
	}
}
