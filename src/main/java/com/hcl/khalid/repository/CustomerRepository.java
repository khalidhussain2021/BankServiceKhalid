package com.hcl.khalid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.khalid.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
	boolean existsByEmail(String email);
}
