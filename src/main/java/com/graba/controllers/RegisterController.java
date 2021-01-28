package com.graba.controllers;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.graba.entity.Customer;
import com.graba.entity.User;
import com.graba.service.CustomerService;
import com.graba.service.EmailService;


@Controller
public class RegisterController {
	

	
	/*
	 * @Autowired private PasswordEncoder bCryptPasswordEncoder;
	 */
	@Autowired
	private CustomerService userService;
	@Autowired
	private EmailService emailService;
	
	@Autowired 
	private PasswordEncoder bCryptPasswordEncoder;
	
 
    @Autowired
    public RegisterController(PasswordEncoder bCryptPasswordEncoder, CustomerService userService, EmailService emailService) {
      
      this.bCryptPasswordEncoder = bCryptPasswordEncoder;
      this.userService = userService;
      this.emailService = emailService;
    }

	// Return registration form template
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String showRegistrationPage(Model model, Customer user){
		model.addAttribute("user", user);
		return "/register";
	}
	
	// Process form input data
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String processRegistrationForm(Model model, @Valid Customer user, BindingResult bindingResult, HttpServletRequest request) {
				
		// Lookup user in database by e-mail
		Customer userExists = userService.findByEmail(user.getEmail());
		
		System.out.println(userExists);
		
		
		
		if (userExists != null) {
			model.addAttribute("alreadyRegisteredMessage", "Oops!  There is already a user registered with the email provided.");
			model.addAttribute("register");
			model.addAttribute("user", user);
			bindingResult.reject("email");
			return "register";
		}
			
		if (bindingResult.hasErrors()) { 
			model.addAttribute("register");	
			model.addAttribute("user", user);
			return "register";
		} else { // new user so we create user and send confirmation e-mail
				  
		    userService.registerCustomer(user);
				
			String appUrl = request.getScheme() + "://" + request.getServerName();
			
			SimpleMailMessage registrationEmail = new SimpleMailMessage();
			registrationEmail.setTo(user.getEmail());
			registrationEmail.setSubject("Registration Confirmation");
			registrationEmail.setText("To confirm your e-mail address, please click the link below:\n"
					+ appUrl + "/confirm?token=" + user.getConfirmationToken());
			registrationEmail.setFrom("noreply@domain.com");
			
			emailService.sendEmail(registrationEmail);
			
			model.addAttribute("confirmationMessage", "A confirmation e-mail has been sent to " + user.getEmail());
			model.addAttribute("register");
			model.addAttribute("user", user);
			return "register";
		}
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.GET)
	public String showConfirmationPage(ModelAndView modelAndView, @RequestParam("token") String token) {
			
		Customer user = userService.findByConfirmationToken(token);
			
		if (user == null) { // No token found in DB
			modelAndView.addObject("invalidToken", "Oops!  This is an invalid confirmation link.");
		} else { // Token found
			modelAndView.addObject("confirmationToken", user.getConfirmationToken());
		}
			
		modelAndView.setViewName("confirm");
		return "confirm";		
	}
	
	// Process confirmation link
	@RequestMapping(value="/confirm", method = RequestMethod.POST)
	public ModelAndView processConfirmationForm(ModelAndView modelAndView, BindingResult bindingResult, @RequestParam Map requestParams, RedirectAttributes redir) {
				
		modelAndView.setViewName("confirm");
		
		//Zxcvbn passwordCheck = new Zxcvbn();
		
		//Strength strength = passwordCheck.measure(requestParams.get("password"));
		
		/*
		 * if (strength.getScore() < 3) { bindingResult.reject("password");
		 * 
		 * redir.addFlashAttribute("errorMessage",
		 * "Your password is too weak.  Choose a stronger one.");
		 * 
		 * modelAndView.setViewName("redirect:confirm?token=" +
		 * requestParams.get("token")); System.out.println(requestParams.get("token"));
		 * return modelAndView; }
		 */
	
		// Find the user associated with the reset token
		Customer user = userService.findByConfirmationToken((String)requestParams.get("token"));

		// Set new password
		user.setPassword(bCryptPasswordEncoder.encode((CharSequence) requestParams.get("password")));
		user.setPassword((String)requestParams.get("password"));
		// Set user to enabled
		//user.setEnabled(true);
		
		// Save user
		userService.save(user);
		
		modelAndView.addObject("successMessage", "Your password has been set!");
		return modelAndView;		
	}
	
}