package com.yc.hdmedia.web.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.Admin;
import com.yc.hdmedia.service.AdminService;

@Controller("adminAction")
public class AdminAction implements ModelDriven<Admin>,SessionAware{
	private Admin admin;
	@Autowired
	private AdminService adminService;
	private Map<String, Object> session;

	
	public String login(){
		LogManager.getLogger().debug("login登陆操作...");
		Admin admins=adminService.login(admin);
		System.out.println(admins);
		if(admin==null){
			return "fail";
		}
		return "login";
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public Admin getModel() {
		return admin=new Admin();
	}

	

}
