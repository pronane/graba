package com.graba.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.graba.entity.Customer;
import com.graba.repository.CustomerRepository;
import com.graba.security.CustomerUserDetails;

public class GrabaUserDetailsService implements UserDetailsService{
	
	@Autowired 
	private CustomerRepository userRepository;
	
	public void setUserRepository(CustomerRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Customer user = userRepository.findByEmail(email);
		if(user == null) {
			throw new UsernameNotFoundException("Could not find user wit;at email");
		}
		return new CustomerUserDetails(user);
	}

}
