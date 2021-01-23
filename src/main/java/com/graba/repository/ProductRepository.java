package com.graba.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.graba.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

	//@Query("Select p from Product p where p.enabled=true And (p.category.id="")
	//public Page<Product> listByCategory(Integer categoryId, String categoryIdMatch, Pageable pageable);
	
	public Product findByAlias(String alias);
	
	@Query(value="Select * from products "
			+ " where MATCH(name, alias, short_description, full_description) "
			+ " AGAINST (?1)",
			nativeQuery = true)
	public List<Product> search(String keyword);
}
