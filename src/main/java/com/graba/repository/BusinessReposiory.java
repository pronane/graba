package com.graba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.graba.entity.Business;

public interface BusinessReposiory extends JpaRepository<Business, Long>{

	@Query(value ="select * from Business where location_Id = ?1",
			nativeQuery = true)
	public List<Business> findAllLocalBusiness(long businessId);

	@Query(value ="select * from Business where name = ?1",
			nativeQuery = true)
	public Business findByName(String name);

}
