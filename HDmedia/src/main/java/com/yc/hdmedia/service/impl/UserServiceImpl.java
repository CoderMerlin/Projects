package com.yc.hdmedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.User;
import com.yc.hdmedia.mapper.UserMapper;
import com.yc.hdmedia.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserMapper userMapper;
	
	@Override
	public int addUser(User user) {
		return userMapper.addUser(user);
	}

	@Override
	public User login(User user) {
	
		return userMapper.login(user);
	}

}
