package com.yc.hdmedia.service;

import com.yc.hdmedia.entity.User;

public interface UserService {
	public int addUser(User user);    //用户注册
	
	public User login(User user);     //用户登录
}
