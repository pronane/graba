package com.graba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graba.entity.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Customer findByEmail(String email);

	void setActive(Long id);

	Customer findByVerificationCode(String verificationCode);

	
}
