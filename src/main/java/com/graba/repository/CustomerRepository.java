package com.graba.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.graba.entity.Customer;

@Repository
@Transactional
public interface CustomerRepository extends UserRepository<Customer>{

	Customer findByEmail(String email);

	@Modifying
	@Query(value = "update Customer set active='y' where Id=?1", nativeQuery=true)
	void setActive(Long id);

	Customer findByVerificationCode(String verificationCode);

	
}
