package com.graba.controllers.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.graba.entity.Product;
import com.graba.service.ProductService;

@RestController
public class ManageProductRestController {

	
	@Autowired
	private ProductService productService;
	
	
	@DeleteMapping("/manage/products/remove/{productId}")
	public String removeProductFromCart(@PathVariable("productId") Long productId,
										@AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must login to remove this product from your store";
		}
		

		productService.deleteProductById(productId);


		
		return "The product has been removed from your store.";
	}
	
	/*
	 * @PostMapping("/products/update/") public String
	 * updateProduct(@RequestParam("productId") Long productId,
	 * 
	 * @AuthenticationPrincipal Authentication authentication) {
	 * 
	 * if(authentication == null || authentication instanceof
	 * AnonymousAuthenticationToken) { return
	 * "You must login to update this product from your store"; }
	 * 
	 * productService.deleteProductById(productId);
	 * 
	 * return "The product has been removed from your store."; }
	 */
	
	@ResponseBody
	@PostMapping("/manage/products/update/")
	public String updateProductObject(@RequestBody Product product,
										@AuthenticationPrincipal Authentication authentication) {
		
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "You must login to update this product from your store";
		}
		if(product.getId() == null ) {
			productService.save(product);
		}
		else {
			productService.updateProduct(product);
		}
		
		return "The product has been updated in your store.";
	}
}
