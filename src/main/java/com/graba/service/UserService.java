package com.graba.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.graba.entity.User;
import com.graba.repository.UserDao;

@Service
public class UserService {
	
	public static final int USERS_PER_PAGE = 10;
	
	@Autowired
	private UserDao userDAO;
	
	@Cacheable(value="users-cache", key="#userId", unless = "#result==null")
	public Optional<User> getUserById(Long userId) {
		return userDAO.findById(userId);
	}

	@CacheEvict(value="users-cache", key="#userId")
	public void deleteUser(Long userId) {
		userDAO.deleteById(userId);
	}
	
	@CachePut(value="users-cache")
	public User updateUser(User user) {
		User updatedUser = null;
		Long id = user.getId();
		User userFromDB = userDAO.findById(id).orElse(null);
		if(userFromDB != null) {
			userFromDB.setFirstName("Go Jek");
			userFromDB.setContact("4444");
			updatedUser = userDAO.save(userFromDB);
		}
		return updatedUser;
	}

	public Page<User> listAll(Integer pageNum, String sortField, String sortDir, String keyword) {
		// TODO Auto-generated method stub
		return null;
	}
}