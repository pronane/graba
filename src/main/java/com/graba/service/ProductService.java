package com.graba.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.graba.common.exception.ProductNotFoundException;
import com.graba.entity.Product;
import com.graba.repository.ProductRepository;

@Service
@Transactional
public class ProductService {

	public static final int PRODUCTS_PER_PAGE = 5;
	public static final int SEARCH_RESULT_PER_PAGE = 10;
	
	@Autowired
	private ProductRepository productRepository;

	
	
	public void save(Product product) {
		if(product.getId() == null) {
			product.setCreatedDate(new Date());
		}
		
		if(product.getAlias() == null || product.getAlias().isEmpty()) {
			String defaultAlias = product.getName().replaceAll(" ", "-");
			product.setAlias(defaultAlias);
		} else {
			
		}
	}

	public Page<Product> listAll(Integer pageNum, String sortField, String sortDir, String keyword, Long category) {
		// TODO Auto-generated method stub
		List<Product> product = productRepository.findAll();
		return productRepository.findAll(PageRequest.of(0, 3, Sort.by("name").descending()));
	}

	public Product get(Long id) throws ProductNotFoundException{
		// TODO Auto-generated method stub
		Optional<Product> product = productRepository.findById(id);
		if(product == null) {
			throw new ProductNotFoundException("Product not Found =  " + id);
		}
		return product.get();
	}
	
	
	public Page<Product> listByCategory(int pageNum, Long categoryId) {
		String categoryMatchId = "-" + String.valueOf(categoryId + "-");
		
		Pageable pageable = PageRequest.of(pageNum - 1, PRODUCTS_PER_PAGE);
		
		//return repo.listByCategory(categoryId, categoryIdMatch, pageable);
		return null;
	}
	
	public Product getProduct(String alias) throws ProductNotFoundException {
		Product product = productRepository.findByAlias(alias);
		if(product == null) {
			throw new ProductNotFoundException("Product does not exist");
		}
		return product;
	}
	
	public List<Product> search(String keyword) {
		return productRepository.search(keyword);
	}
}
