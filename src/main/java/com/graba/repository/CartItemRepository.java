package com.graba.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graba.entity.CartItem;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
	
	//@Query("SELECT FROM CartItem c WHERE c.customer.id=?1")
	public List<CartItem> findByCustomerId(Long customerId);
	
	public CartItem findByCustomerIdAndProductId(Long userId, Long productId);
	
	//@Query("UPDATE CartItem c SET c.quantity = ?1 where c.product_id = ?2 and c.customer_id = ?3")
	//@Modifying
	//public void updateQuantity(Integer quantity, Long productId, Long customerId);
	
	//@Query("DELETE FROM CartItem c WHERE c.customer.id=?1 AND c.product.id=?2");
	//@Query("DELETE FROM CartItem c WHERE c.customer_id=?1 AND c.product_id=?2")
	//@Modifying
	//public void deleteByCustomerAndProduct(Long customerId, Long productId);
}
