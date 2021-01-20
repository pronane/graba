package com.graba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graba.entity.ProductCategory;
import com.graba.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	public List<ProductCategory> listAll() {
		return productCategoryRepository.findAll();
	};

}
