package com.hcl.khalid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.khalid.entities.Transaction;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction,Long>{

}
