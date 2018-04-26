package com.yd.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yd.entity.User;
import com.yd.mapper.UserMapper;
import com.yd.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private  UserMapper  userMapper;
	
	@Override
	public User findUserById(Integer id) {
		return userMapper.selectByPrimaryKey(id);
	}

	@Override
	public User findUserByUserName(String username) {
		return userMapper.selectByUsernameOrPassword(username,null);
	}

}
