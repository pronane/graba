package com.graba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.graba.common.exception.ProductNotFoundException;
import com.graba.entity.Category;
import com.graba.entity.Product;
import com.graba.service.ProductCategoryService;
import com.graba.service.ProductService;

public class CommonController {
	
	@Autowired
	private ProductCategoryService categoryService;
	
	@Autowired
	private ProductService productService;
	
	@GetMapping("/")
	public String viewHomePage(Model model) {
		List<Category> categoryList = categoryService.listNoChildrenCategories();
		model.addAttribute("categories", categoryList);
		model.addAttribute("pageTitle", "Home");
		
		return "index";
	}
	
	@GetMapping("/c/{category_alias}")
	public String viewCategory(@PathVariable (name="categoryAlias") String alias, Model model)
	{
		return viewCategoryByPage(alias, 1, model);
	}
	
	@GetMapping("/c/{category_alias}/page/{pageNum}")
	public String viewCategoryByPage (@PathVariable (name="categoryAlias") String alias, @PathVariable(name="pageNum") Integer pageNum,
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
		
		return "category";
	}
	
	@GetMapping("/p/{product_alias}")
	public String viewProductDetail(@PathVariable (name = "productAlias") String alias, Model model) {
		try {
			Product product = productService.getProduct(alias);
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
	public String search(@PathVariable (name = "keyword") String keyword, Model model) {
		
		List<Product> searchResult = productService.search(keyword);
		
		model.addAttribute("searchResult", searchResult);
		model.addAttribute("keyword", keyword);
		model.addAttribute("pageTitle", "Search results for " + keyword);
		
		return "";
	}
	
}
