package com.graba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.graba.common.exception.ProductNotFoundException;
import com.graba.entity.Brand;
import com.graba.entity.Customer;
import com.graba.entity.Product;
import com.graba.service.BrandService;
import com.graba.service.ProductCategoryService;
import com.graba.service.ProductService;

@Controller
public class ManageProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@GetMapping("/manage/login")
	public String login(Model model){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.addAttribute("pageTitle", "Customer Login");
			return "manage/login";
		}
		return "redirect:manage/products/showAll";
	}
	
	/** @TODO FIx tomorrow **/
	@PostMapping("/manage/login")
	public String details(Model model, @RequestParam String username, @RequestParam String password){
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			model.addAttribute("pageTitle", "Customer Login");
			return "manage/login";
		}
		return "redirect:manage/products/showAll";
	}
	
	@GetMapping("/manage/products/showAll")
	public String showAll(Model model, @AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "manage/login";
		}
		
		
		List<Brand> listBrands = brandService.findAll();
		Page<Product> pageProductList = productService.listAll(1, "name", "asc");
		
		if(pageProductList != null ) {
			List<Product> productList = pageProductList.getContent();
			model.addAttribute("productList", productList);
		}
		return "/manage/showAllProducts";
	}
	
	@GetMapping("/manage/products/detail/{id}")
	public String viewProduct(@PathVariable("id") Long id,  Model model,
					RedirectAttributes redirectAttributes, @AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "manage/login";
		}
		
		try {
			Product product = productService.get(id);
			model.addAttribute("product", product);
			model.addAttribute("pageTitle", "Product Details (ID: " + id + ")");
			
		//	return "productDetailModal";
			return "manage/manageProductDetail";
		} catch ( ProductNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message" , "Could not find any productes with ID " + id);
			return "redirect:manage/products";
		}
	}
	
}
