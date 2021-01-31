package com.graba.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.query.Param;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.graba.common.exception.ProductNotFoundException;
import com.graba.entity.Brand;
import com.graba.entity.Category;
import com.graba.entity.Customer;
import com.graba.entity.Product;
import com.graba.entity.ProductCategory;
import com.graba.service.BrandService;
import com.graba.service.CategoryService;
import com.graba.service.CustomerService;
import com.graba.service.ProductCategoryService;
import com.graba.service.ProductService;
import com.graba.service.ShoppingCartService;

@Controller
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	@Autowired
	private BrandService brandService;
	
	@Autowired
	private ProductCategoryService productCategoryService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping("/products")
	public String listAll(Model model) {
		return listByPage(1, model, "name", "asc", null, 0l);
	}
	
	@GetMapping("/products/detail/{id}")
	public String viewProduct(@PathVariable("id") Long id,  Model model, RedirectAttributes redirectAttributes) {
		try {
			Product product = productService.get(id);
			model.addAttribute("product", product);
			model.addAttribute("pageTitle", "Product Details (ID: " + id + ")");
			
		//	return "productDetailModal";
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			} else {
				Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
				Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
				model.addAttribute("cartItemsSize", cartItemsSize);
			}	
			return "productDetail";
		} catch ( ProductNotFoundException ex) {
			redirectAttributes.addFlashAttribute("message" , "Could not find any productes with ID " + id);
			return "redirect:/products";
		}
	}
	
	@GetMapping("/products/new")
	public String newProduct(Model model) {
		List<Brand> listBrands = brandService.findAll();
		return "addProduct";
	}
		
	
	@GetMapping("/products/page/{pageNum}")
	public String listByPage(@PathVariable(name = "pageNum") Integer pageNum, Model model, 
			@Param("sortField") String sortField,
			@Param("sortDir") String sortDir,
			@Param("keyword") String keyword,
			@Param("category") Long category) {
		
		List<ProductCategory> listCategories = productCategoryService.listAll();
		
		Page<Product> page = productService.listAll(pageNum, sortField, sortDir, keyword, category);
		if(page != null ) {
			List<Product> listProducts = page.getContent();
			
			model.addAttribute("listCategories", listCategories);
			
			if(category != null) 
				model.addAttribute("category", category);
			
			model.addAttribute("totalPages", page.getTotalPages());
			model.addAttribute("totalItems", page.getTotalElements());
			model.addAttribute("currentPage", pageNum);
			model.addAttribute("sortField", sortField);
			model.addAttribute("sortDir", sortDir);
			model.addAttribute("keyword", keyword);
			model.addAttribute("reverseSortDir",sortDir.equals("asc") ? "desc" : "asc");
			
			model.addAttribute("listProducts", listProducts);
			
			long startCount = (pageNum - 1) * ProductService.PRODUCTS_PER_PAGE + 1;
			model.addAttribute("startCount", startCount);
			
			long endCount = startCount = ProductService.PRODUCTS_PER_PAGE - 1;
			if(endCount > page.getTotalElements()) {
				endCount = page.getTotalElements();
			}
			model.addAttribute("endCount", endCount);
			
			if(page.getTotalPages() > 1) {
				model.addAttribute("pageTitle", "Products (page " + pageNum + ")");
			} else {
				model.addAttribute("pageTitle", "Products");
			}
		}
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
		} else {
			Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
			Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
			model.addAttribute("cartItemsSize", cartItemsSize);
		}	
		
		return "products";
	}
	
	@GetMapping("/p/{productAlias}")
	public String viewProductDetail(@PathVariable  String productAlias, Model model) {
		try {
			Product product = productService.getProduct(productAlias);
			List<Category> parents = categoryService.getCategoryParents(product.getCategoryId());
			model.addAttribute("parents", parents);
			model.addAttribute("product", product);
			model.addAttribute("pageTitle",  product.getName());
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			} else {
				Customer customer = customerService.getCurrentlyLoggedInCustomer(authentication);
				Integer cartItemsSize = shoppingCartService.countByCustomerId(customer.getId());
				model.addAttribute("cartItemsSize", cartItemsSize);
			}	
			
			return "productDetail";
		} catch(ProductNotFoundException e) {
			return "error/404";
		}
	}

}
