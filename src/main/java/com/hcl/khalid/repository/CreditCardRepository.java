package com.hcl.khalid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.khalid.entities.CreditCard;
@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

}
