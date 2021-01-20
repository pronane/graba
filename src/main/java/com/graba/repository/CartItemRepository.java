package com.graba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.graba.entity.CartItem;
import com.graba.entity.Customer;
import com.graba.entity.Product;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	public List<CartItem> findByCustomer(Customer customer);
	
	public CartItem findByCustomerAndProduct(Customer customer, Product product);
	
	@Query("UPDATE CartItem c SET c.quantity = ?1 where c.product.id = ?2 and c.customer.id = ?3")
	@Modifying
	public void updateQuantity(Integer quantity, Long productId, Long customerId);
	
	//@Query("DELETE FROM CartItem c WHERE c.customer.id=?1 AND c.product.id=?2");
	@Query("DELETE FROM CartItem c WHERE c.customer.id=?1 AND c.product.id=?2")
	@Modifying
	public void deleteByCustomerAndProduct(Long customerId, Long productId);
}
