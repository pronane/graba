package com.graba.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.graba.entity.Customer;
import com.graba.service.CustomerService;

@Controller
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@RequestMapping("/customer")
	@GetMapping
	public String showCustomer(Model model, @AuthenticationPrincipal Authentication authentication) {
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "login";
		}
		
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		model.addAttribute("customer", customer);
		return "customer";
	}
}
