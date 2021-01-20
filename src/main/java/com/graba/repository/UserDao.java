package com.graba.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.graba.entity.User;

@Repository
public interface UserDao extends JpaRepository<User, Long>{
	
}
