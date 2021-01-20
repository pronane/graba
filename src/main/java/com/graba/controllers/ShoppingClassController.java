package com.graba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.graba.entity.CartItem;
import com.graba.entity.Customer;
import com.graba.service.CustomerService;
import com.graba.service.ShoppingCartService;

@Controller
public class ShoppingClassController {

	@Autowired
	private ShoppingCartService cartService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/cart")
	public String showShoppingCart(Model model, @AuthenticationPrincipal Authentication authentication) {
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		List<CartItem> cartItems = cartService.listCartItems(customer);
		
		model.addAttribute("cartItems", cartItems);
		model.addAttribute("pageTitel", "Shopping Cart");
		
		
		return "shoppingCart";
	}
	
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