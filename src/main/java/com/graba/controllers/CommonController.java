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
import com.graba.entity.Category;
import com.graba.entity.Product;
import com.graba.service.CategoryService;
import com.graba.service.ProductService;

@Controller
public class CommonController {
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	
	/*
	 * @GetMapping("/") public String home(Model model){ return "index.html"; }
	 */
	 
	@GetMapping("/about")
	public String aboutUs(Model model) {		
		return "aboutUs";
	}
	
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Category> categoryList = categoryService.listNoChildrenCategories();
		model.addAttribute("categories", categoryList);
		model.addAttribute("pageTitle", "Home");
		
		return "index.html";
	}
	
	@GetMapping("/contact")
	public String viewContact(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.addAttribute("pageTitle", "Customer Login");
			return "login";
		}
		model.addAttribute("pageTitle", "Contact");
		
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
	
	@GetMapping("/p/{productAlias}")
	public String viewProductDetail(@PathVariable  String productAlias, Model model) {
		try {
			Product product = productService.getProduct(productAlias);
			List<Category> parents = categoryService.getCategoryParents(product.getCategoryId());
			model.addAttribute("parents", parents);
			model.addAttribute("product", product);
			model.addAttribute("pageTitle",  product.getName());
			
			return "productDetail";
		} catch(ProductNotFoundException e) {
			return "error/404";
		}
	}
	
	@GetMapping("/search")
	public String search(@RequestParam (name = "keyword") String keyword, Model model) {
		
		List<Product> searchResult = productService.search(keyword);
		
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageTitle", "Search results for " + keyword);
		
		return "searchResult";
	}
	
}
