package com.yc.hdmedia.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.Admin;
import com.yc.hdmedia.mapper.AdminMapper;
import com.yc.hdmedia.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService{
	@Autowired
	private AdminMapper adminMapper;
	
	@Override
	public Admin login(Admin admin) {
		return adminMapper.login(admin);
	}

}
