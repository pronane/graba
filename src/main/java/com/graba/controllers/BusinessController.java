package com.graba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.graba.entity.Business;
import com.graba.entity.Customer;
import com.graba.entity.Product;
import com.graba.service.BusinessService;
import com.graba.service.CustomerService;
import com.graba.service.ProductService;
import com.graba.service.ShoppingCartService;

@Controller
public class BusinessController {

	@Autowired
	private BusinessService businessService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	
	@RequestMapping(value="/registerCompany", method = RequestMethod.GET)
	public String showRegistrationPage(Model model){
		return "/registerCompany";
	}
	
	@GetMapping("/b/{business_name}")
	public String viewBusiness(@PathVariable (name="business_name") String alias, Model model)
	{
		return viewBusinessByPage(alias, 1, model);
	}
	
	@GetMapping("/b/{business_name}/page/{pageNum}")
	public String viewBusinessByPage (@PathVariable (name="business_name") String name, @PathVariable(name="pageNum") Integer pageNum,
										Model model){
		Business business = businessService.getBusiness(name);
		
		Page<Product> pageProducts = productService.listByBusiness(pageNum, business.getId());
		List<Product> productList = pageProducts.getContent();
		
		model.addAttribute("totalPages", pageProducts.getTotalPages());
		model.addAttribute("totalItems", pageProducts.getTotalElements());
		model.addAttribute("currentPage", pageNum);
		
		long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
		model.addAttribute("startCount", startCount);
		
		long endCount = startCount + ProductService.PRODUCTS_PER_PAGE - 1;
		if(endCount > pageProducts.getTotalElements()) {
			endCount = pageProducts.getTotalElements();
		}
		model.addAttribute("endCount", endCount);
		
		model.addAttribute("business", business);
		model.addAttribute("businessName", name);
		model.addAttribute("products", productList);
		model.addAttribute("pageTitle", business.getName());
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
		} else {
			Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
			Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
			model.addAttribute("cartItemsSize", cartItemsSize);
		}	
		
		return "business.html";
	}
	
}
