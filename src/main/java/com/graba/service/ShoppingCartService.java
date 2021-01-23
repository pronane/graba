package com.graba.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.graba.entity.CartItem;
import com.graba.entity.Customer;
import com.graba.entity.Product;
import com.graba.repository.CartItemRepository;
import com.graba.repository.ProductRepository;

@Service
@Transactional
public class ShoppingCartService {

	@Autowired
	private CartItemRepository cartRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	public List<CartItem> listCartItems(Customer customer) {
		return cartRepository.findByCustomerId(customer.getId());
	}
	
	public Integer addProduct(Long productId, Integer quantity, Customer customer) {
		Integer addedQuantity = quantity;
		
		Optional<Product> product = productRepository.findById(productId);
		
		CartItem cartItem = cartRepository.findByCustomerIdAndProductId(customer.getId(), product.get().getId());
		
		if(cartItem != null) {
			addedQuantity = cartItem.getQuantity() + quantity;
			cartItem.setQuantity(addedQuantity);
		} else {
			cartItem = new CartItem();
			cartItem.setQuantity(quantity);
			cartItem.setCustomer(customer.getId());
			cartItem.setProduct(productId);
		}
		
		cartRepository.save(cartItem);
		
		return addedQuantity;
	}
	
	public Float updateQuantity(Long productId, Integer quantity, Customer customer) {
		
	//	cartRepository.updateQuantity(quantity, productId, customer.getId());
		Product product = productRepository.findById(productId).get();
		
		Float subtotal = product.getPrice()* quantity;
		
		return subtotal;
	}
	
	public void removeProduct(Long productId, Customer customer) {
		//cartRepository.deleteByCustomerAndProduct(productId, productId);
	}
}
