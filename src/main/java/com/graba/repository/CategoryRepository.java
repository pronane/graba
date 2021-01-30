package com.graba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.graba.entity.Category;

public interface CategoryRepository extends JpaRepository<Category, Long>{

	@Query(value = "select * from Category c where alias=?1", nativeQuery = true)
	public Category findByAlias(String alias);


}
