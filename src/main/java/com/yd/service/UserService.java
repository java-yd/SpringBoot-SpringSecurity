package com.yd.service;

import com.yd.entity.User;

public interface UserService {
	
	User  findUserById(Integer  id);

	User findUserByUserName(String username);
	
}
