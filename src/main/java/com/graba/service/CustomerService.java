package com.graba.service;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.graba.entity.Country;
import com.graba.entity.Customer;
import com.graba.repository.CountryRepository;
import com.graba.repository.CustomerRepository;
import com.graba.security.CustomerUserDetails;

import net.bytebuddy.utility.RandomString;

@Service
@Transactional
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	@Autowired
	private CountryRepository countryRepository;
	
	@Autowired
	private JavaMailSender mailSender;
	
	public List<Country> listAllCountries() {
		//return countryRepository.findAllByOrByNameAsc();
		return countryRepository.findAll();
	}
	
	public void registerCustomer(Customer customer) {
		encodePassword(customer);
		customer.setCreatedTime(new Date());
		customer.setActive(false);
		customer.setUserName(customer.getEmail());
		//customer.setAuthProvider(AuthenticationProvider.LOCAL);
		
		String randomCode = RandomString.make(64);
		customer.setVerificationCode(randomCode);
		customerRepository.save(customer);
	}
	
	public void createNewCustomerAfterOAuthLoginSuccess(String email,
			String name, AuthenticationProvider provider) {
		Customer customer = new Customer();
		customer.setEmail(email);
		//customer.setEnabled(true);
		customer.setCreatedTime(new Date());
		customer.setFirstName(name);
		//customer.setAuthProvider(provider);
	
		customerRepository.save(customer);
	}
	
	public void updateCustomerAfterOAuthLoginSuccess(Customer customer, String name, AuthenticationProvider provider) {
		customer.setFirstName(name);
		//customer.setAuthProvider(provider);
		customerRepository.save(customer);
	}
	
	public Customer findByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	public void updateCustomer(Customer customer) {
		Customer existCustomer = customerRepository.findById(customer.getId()).get();
		
		//if(existCustomer.getAuthProvider().equals(AuthenticationProvider.LOCAL)) {
			if(customer.getPassword().isEmpty()) {
				customer.setPassword(existCustomer.getPassword());
			}
			else {
				encodePassword(customer);
			}
		//}
		
		customer.setAuthProvider(existCustomer.getAuthProvider());
		customer.setActive(true);
		
		customerRepository.save(customer);
	}

	private void encodePassword(Customer customer) {
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		String encodedPassword = passwordEncoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
	}

	public boolean isEmailUnique(String email) {
		Customer existingCustomer = customerRepository.findByEmail(email);
		return existingCustomer == null;
	}
	
	public Customer getCustomerByEmail(String email) {
		return customerRepository.findByEmail(email);
	}
	
	public boolean verify(String verificationCode) {
		Customer customer = customerRepository.findByVerificationCode(verificationCode);
		
		if(customer == null || customer.getActive()) {
			return false;
		} else {
			customerRepository.setActive(customer.getId());
			return true;
		}
	}
	
	public Customer getCurrentlyLoggedInCustomer(Authentication authentication) {
		if(authentication == null) 
			return null;
		
		Customer customer = null;
		Object principal = authentication.getPrincipal();
		
		if(principal instanceof CustomerUserDetails) {
			customer = ((CustomerUserDetails)principal).getCustomer();
		} /*
			 * else if(principal instanceof CustomOAuth2User) { String email =
			 * ((CustomOAuth2User)principal).getEmail(); customer =
			 * getCustomerByEmail(email); }
			 */
		else {
			
			User user = (User) principal;
			customer = new Customer();
			customer.setUserName(user.getUsername());
			customer.setId(1l);
		}
		
		return customer;
	}

	public Customer findByConfirmationToken(String token) {
		// TODO Auto-generated method stub
		return customerRepository.findByVerificationCode(token);
	}
	
	public Customer save(Customer user) {
		// TODO Auto-generated method stub
		return customerRepository.save(user);
	}


}
