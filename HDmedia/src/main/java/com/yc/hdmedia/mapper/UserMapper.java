package com.yc.hdmedia.mapper;

import com.yc.hdmedia.entity.User;

public interface UserMapper {
	public int addUser(User user);    //用户注册
	
	User login(User user);     //用户登录
}
