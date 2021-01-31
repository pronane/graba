package com.graba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.graba.entity.Customer;
import com.graba.service.CustomerService;
import com.graba.service.ShoppingCartService;

@Controller
public class LoginController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private CustomerService customerService;
	
	@GetMapping("/login")
	public String login(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.addAttribute("pageTitle", "Customer Login");
			return "login";
		}
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		Integer numberOfItemsInCart = shoppingCartService.countByCustomerId(customer.getId());
	
		model.addAttribute("cartItemsSize", numberOfItemsInCart);
		
		return "redirect:/";
	}
}
