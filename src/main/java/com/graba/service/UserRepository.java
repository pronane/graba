package com.graba.service;

import com.graba.entity.User;

public interface UserRepository {

	User getUserByEmail(String email);

}
