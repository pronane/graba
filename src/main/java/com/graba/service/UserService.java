package com.graba.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.graba.entity.Customer;
import com.graba.entity.User;
import com.graba.repository.CustomerRepository;

@Service
public class UserService {
	
	public static final int USERS_PER_PAGE = 10;
	
	@Autowired
	private CustomerRepository userRepository;
	
	@Cacheable(value="users-cache", key="#userId", unless = "#result==null")
	public Optional<Customer> getUserById(Long userId) {
		return userRepository.findById(userId);
	}

	@CacheEvict(value="users-cache", key="#userId")
	public void deleteUser(Long userId) {
		userRepository.deleteById(userId);
	}
	
	@CachePut(value="users-cache")
	public User updateUser(User user) {
		User updatedUser = null;
		Long id = user.getId();
		Customer userFromDB = (Customer) userRepository.findById(id).orElse(null);
		if(userFromDB != null) {
			userFromDB.setFirstName("Go Jek");
			userFromDB.setContact("4444");
			updatedUser = (User) userRepository.save(userFromDB);
		}
		return updatedUser;
	}

	public Page<User> listAll(Integer pageNum, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}