package com.yc.hdmedia.web.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.User;
import com.yc.hdmedia.service.UserService;

@Controller("userAction")
public class UserAction implements ModelDriven<User>,SessionAware{
	private User user;
	@Autowired
	private UserService userService;
	private Map<String, Object> session;
	
	
	public String register(){
		LogManager.getLogger().debug("注册...");
		int result=userService.addUser(user);
			System.out.println(user);
			return "register";
			}
	
	public String login(){
		LogManager.getLogger().debug("login登陆操作...");
		User users=userService.login(user);
		System.out.println(users);
		if(user==null){
			return "fail";
		}
		return "login";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

	@Override
	public User getModel() {
		return user=new User();
	}

	

}
