package com.graba.controllers.rest;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginRestController {

	@GetMapping("/loggedInCheck")
	public String removeProductFromCart(@AuthenticationPrincipal Authentication authentication) {
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken) {
			return "User not logged In";
		}
		return "User is logged in";
	}
		
}
