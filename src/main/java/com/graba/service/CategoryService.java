package com.graba.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graba.entity.Category;
import com.graba.repository.CategoryRepository;

@Service
public class CategoryService {

	
	@Autowired
	private CategoryRepository categoryRepository;
	
	public List<Category> listAll() {
		return categoryRepository.findAll();
	}

	public List<Category> listNoChildrenCategories() {
		// TODO Auto-generated method stub
		return categoryRepository.findAll();
	}

	public List<Category> getCategoryParents(Long categoryId) {
		// TODO Auto-generated method stub
		return null;
	}

	public Category getCategory(String alias) {
		// TODO Auto-generated method stub
		return categoryRepository.findByAlias(alias);
	}

	public List<Category> getCategoryParents(Category category) {
		// TODO Auto-generated method stub
		//return categoryRepository.findByParents(category.getId());
		return null;
	};

}
