package com.graba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.graba.entity.Customer;
import com.graba.service.CustomerService;
import com.graba.service.ShoppingCartService;

@Controller
public class OrderController {

	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private CustomerService customerService;
	
	@RequestMapping(value="/orders", method = RequestMethod.GET)
	public String showRegistrationPage(Model model, Customer user){
		model.addAttribute("user", user);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
		} else {
			Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
			Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
			model.addAttribute("cartItemsSize", cartItemsSize);
		}	
		
		return "/orders";
	}
	
}
