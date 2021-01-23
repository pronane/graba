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

	public List<Category> listNoChildrenCategories() {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> getCategoryParents(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Category getCategory(String alias) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Category> getCategoryParents(Category category) {
		// TODO Auto-generated method stub
		return null;
	};

}
