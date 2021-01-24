package com.graba.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.graba.entity.Customer;
import com.graba.service.CustomerService;
import com.graba.service.ShoppingCartService;

@RestController
public class ShoppingCartRestController {

	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShoppingCartService cartService;
	
	@PostMapping("/cart/add/{pid}/qty}")
	public String addProductToCart(@PathVariable("pid") Long productId, @PathVariable("qty") Integer quantity,
									@AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must login to add this product to your shopping cart";
		}
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		
		if(customer == null) {
			return "You must login to add this product to your shopping cart";
		}
		
		Integer addedQuantity = cartService.addProduct(productId, quantity, customer);
		
		return addedQuantity + " item(s) of this product were added to your shopping cart";
	}
	
	@PostMapping("/cart/add/")
	public String addProductToCartUsing(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity,
									@AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must login to add this product to your shopping cart";
		}
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		
		if(customer == null) {
			return "You must login to add this product to your shopping cart";
		}
		
		Integer addedQuantity = cartService.addProduct(productId, quantity, customer);
		
		return addedQuantity + " item(s) of this product were added to your shopping cart";
	}
	
	@PostMapping("/cart/update/{pid}/qty}")
	public String updateQuantity(@PathVariable("pid") Long productId, @PathVariable("qty") Integer quantity,
									@AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must login to add this product to your shopping cart";
		}
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		
		if(customer == null) {
			return "You must login to add this product to your shopping cart";
		}
		
		Float subtotal = cartService.updateQuantity(productId, quantity, customer);
		
		return String.valueOf(subtotal);
	}
	
	@PostMapping("/cart/update/")
	public String updateQuantityUsing(@RequestParam("productId") Long productId, @RequestParam("quantity") Integer quantity,
									@AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must login to add this product to your shopping cart";
		}
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		
		if(customer == null) {
			return "You must login to add this product to your shopping cart";
		}
		
		Float subtotal = cartService.updateQuantity(productId, quantity, customer);
		
		return String.valueOf(subtotal);
	}
	
	@PostMapping("/cart/remove/{pid}")
	public String removeProductFromCart(@PathVariable("pid") Long productId,
										@AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must login to add this product to your shopping cart";
		}
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		
		if(customer == null) {
			return "You must login to remove product";
		}
		
		 cartService.removeProduct(productId, customer);
		
		return "The product has been removed from your shopping cart.";
	}
}
