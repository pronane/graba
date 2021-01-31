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
import org.springframework.web.bind.annotation.RequestParam;

import com.graba.common.exception.ProductNotFoundException;
import com.graba.entity.Business;
import com.graba.entity.Category;
import com.graba.entity.Customer;
import com.graba.entity.Product;
import com.graba.service.BusinessService;
import com.graba.service.CategoryService;
import com.graba.service.CustomerService;
import com.graba.service.ProductService;
import com.graba.service.ShoppingCartService;

@Controller
public class CommonController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BusinessService buinessService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;
	
	@Autowired
	private CustomerService customerService;
	/*
	 * @GetMapping("/") public String home(Model model){ return "index.html"; }
	 */
	 
	@GetMapping("/about")
	public String aboutUs(Model model) {		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
		} else {
			Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
			Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
			model.addAttribute("cartItemsSize", cartItemsSize);
		}	
		return "aboutUs";
	}
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Category> categoryList = categoryService.listNoChildrenCategories();
		/**
		 * @TODO hardcoded because we only have one location
		 */
		List<Business> buisnesses = buinessService.findAllLocalBuisness(1l);
		
		model.addAttribute("categories", categoryList);
		model.addAttribute("buisnesses", buisnesses);
		model.addAttribute("pageTitle", "Home");
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
		} else {
			Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
			Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
			model.addAttribute("cartItemsSize", cartItemsSize);
		}	
		
		return "index.html";
	}
	
	@GetMapping("/contact")
	public String viewContact(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.addAttribute("pageTitle", "Customer Login");
			return "login";
		}
		Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
		Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
		
		model.addAttribute("pageTitle", "Contact");
		model.addAttribute("cartItemsSize", cartItemsSize);
		
		return "contact.html";
	}
	
	@GetMapping("/c/{category_alias}")
	public String viewCategory(@PathVariable (name="category_alias") String alias, Model model)
	{
		return viewCategoryByPage(alias, 1, model);
	}
	
	@GetMapping("/c/{category_alias}/page/{pageNum}")
	public String viewCategoryByPage (@PathVariable (name="category_alias") String alias, @PathVariable(name="pageNum") Integer pageNum,
										Model model){
		Category category = categoryService.getCategory(alias);
		List<Category> parents = categoryService.getCategoryParents(category);
		
		Page<Product> pageProducts = productService.listByCategory(pageNum, category.getId());
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
		
		model.addAttribute("category", category);
		model.addAttribute("catAlias", alias);
		model.addAttribute("parents", parents);
		model.addAttribute("products", productList);
		model.addAttribute("pageTitle", category.getName());
		
		return "category.html";
	}
	
	
	@GetMapping("/search")
	public String search(@RequestParam (name = "keyword") String keyword, Model model) {
		
		List<Product> searchResult = productService.search(keyword);
		
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageTitle", "Search results for " + keyword);
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
		} else {
			Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
			Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
			model.addAttribute("cartItemsSize", cartItemsSize);
		}		
		
		return "searchResult";
	}
	
}
