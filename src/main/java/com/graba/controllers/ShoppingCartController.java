package com.graba.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.graba.entity.CartItem;
import com.graba.entity.Customer;
import com.graba.entity.Product;
import com.graba.repository.ProductRepository;
import com.graba.service.CustomerService;
import com.graba.service.ShoppingCartService;

@Controller
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ProductRepository productRepository;
	
	@GetMapping("/cart")
	public String showShoppingCart(Model model, @AuthenticationPrincipal Authentication authentication) {
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		List<CartItem> cartItems = cartService.listCartItems(customer);
		
		List<Product> products = productRepository.findAllById(cartItems.stream().map
																(cartItem -> cartItem.getProductId()).collect(Collectors.toSet()));
		
		
		List<CartItem> filteredList = cartItems.stream()
				 		.filter(cartItem -> products.stream()
				 		.anyMatch(product -> product.getId().equals(cartItem.getProductId())))
				 		.map(cartItem-> {
				 			for(Product product : products) {
				 				if(product.getId().equals(cartItem.getProductId())) {
				 					cartItem.setSubTotal(product.getPrice() * cartItem.getQuantity());
				 				}
				 			}		
							return cartItem;
						})
				 		.collect(Collectors.toList());
		
		
		model.addAttribute("cartItems", filteredList);
		model.addAttribute("products", products);
		model.addAttribute("pageTitel", "Shopping Cart");
		
		
		return "shoppingCart";
	}
	
	
	private boolean isAuthenticated() {
	    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	    if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
	        return false;
	    }
	    return authentication.isAuthenticated();
	}
	
}
