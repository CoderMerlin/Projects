package com.yc.hdmedia.service;

import com.yc.hdmedia.entity.User;

public interface UserService {
	public int addUser(User user);    //用户注册通过帐号
	
	public int addUserByMail(User user);  //用户注册通过邮箱
	
	public User login(User user);     //用户登录通过帐号
	
	public User loginByMail(User user);     //用户登录通过邮箱
	
	int update(String yhname);
}
