package com.yc.hdmedia.mapper;

import com.yc.hdmedia.entity.User;

public interface UserMapper {
	public int addUser(User user);    //用户注册通过帐号
	
	public int addUserByMail(User user);  //用户注册通过邮箱
	
	User login(User user);     //用户登录通过账号
	
	User loginByMail(User user);     //用户登录通过邮箱
	
	int update(String yhemail);
}
