package com.graba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graba.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{
	
}
