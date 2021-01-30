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

	/*
	 * @Query(
	 * value="Select p from Product p where p.enabled=true And p.categoryId=?1",
	 * countQuery = "SELECT count(*) FROM Product where categoryId =?1",
	 * nativeQuery=true) public Page<Product> listByCategory(Long categoryId, String
	 * categoryIdMatch, Pageable pageable);
	 */
	
	@Query(value="Select p.* from Product p where p.enabled=true And p.category_id=?1",
			 countQuery = "SELECT count(*) FROM Product where category_id =?1",
			 nativeQuery=true)
	public Page<Product> listByCategory(Long categoryId, Pageable pageable);
	
	public Product findByAlias(String alias);
	
	/*
	 * @Query(value="Select * from product " +
	 * " where MATCH(name, alias, short_description, full_description) " +
	 * " AGAINST (?1)", nativeQuery = true)
	 */
	//@Query(value="SELECT p FROM FT_SEARCH(?1, 0, 0)")
	@Query(value="SELECT p FROM Product p where p.name = ?1 OR p.shortDescription = ?1 OR p.shortName= ?1")
	public List<Product> search(String keyword);
}
