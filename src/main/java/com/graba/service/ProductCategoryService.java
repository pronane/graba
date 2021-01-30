package com.graba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graba.entity.Category;
import com.graba.entity.ProductCategory;
import com.graba.repository.ProductCategoryRepository;

@Service
public class ProductCategoryService {

	
	@Autowired
	private ProductCategoryRepository productCategoryRepository;
	
	public List<ProductCategory> listAll() {
		return productCategoryRepository.findAll();
	}

	public List<ProductCategory> listNoChildrenCategories() {
		// TODO Auto-generated method stub
		return productCategoryRepository.findAll();
	}

	public List<ProductCategory> getCategoryParents(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public ProductCategory getCategory(String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<ProductCategory> getCategoryParents(Category category) {
		// TODO Auto-generated method stub
		return null;
	};

}
