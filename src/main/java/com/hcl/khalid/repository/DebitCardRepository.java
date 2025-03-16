package com.hcl.khalid.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hcl.khalid.entities.DebitCard;

@Repository
public interface DebitCardRepository extends JpaRepository<DebitCard,Long>{
	
}
