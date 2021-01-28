package com.graba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.graba.entity.Customer;

@Controller
public class OrderController {

	@RequestMapping(value="/orders", method = RequestMethod.GET)
	public String showRegistrationPage(Model model, Customer user){
		model.addAttribute("user", user);
		return "/orders";
	}
	
}
