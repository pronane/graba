package com.graba.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class BusinessController {

	@RequestMapping(value="/registerCompany", method = RequestMethod.GET)
	public String showRegistrationPage(Model model){
		return "/registerCompany";
	}
	
}
