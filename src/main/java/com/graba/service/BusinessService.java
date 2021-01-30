package com.graba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graba.entity.Business;
import com.graba.repository.BusinessReposiory;

@Service
public class BusinessService {

	@Autowired
	private BusinessReposiory businessRepository;

	public List<Business> findAllLocalBuisness(long businessId) {
		// TODO Auto-generated method stub
		return businessRepository.findAllLocalBusiness(businessId);
	}

	public Business getBusiness(String name) {
		// TODO Auto-generated method stub
		return businessRepository.findByName(name);
	}
	
	
	
}
