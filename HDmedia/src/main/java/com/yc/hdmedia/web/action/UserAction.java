package com.yc.hdmedia.web.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.User;
import com.yc.hdmedia.service.UserService;
import com.yc.hdmedia.utils.VerifyCode;

@Controller("userAction")
public class UserAction implements ModelDriven<User>,SessionAware{
	private VerifyCode vc=new VerifyCode();
	private String email;
	
	public void setEmail(String email) {
		this.email = email;
	}
	@Autowired
	private JavaMailSender javaMailSender; //邮件发送对象

	private User user;
	@Autowired
	private UserService userService;
	private Map<String, Object> session;
	
	//用户名注册
	public String register(){
		LogManager.getLogger().debug("注册...");
		int result=userService.addUser(user);
			System.out.println(user);
			if(result>0){
				return "registerSuccess";
			}
			return "fail";
			}
	
	//邮箱注册
	public String registerBymail(){
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom("studymail_test@163.com");
		smm.setTo(user.getYhemail());
		System.out.println(user.getYhemail());
		smm.setSubject("验证码");
		String uuid=vc.code();
		ActionContext.getContext().getSession().put("user",user);
		ActionContext.getContext().getSession().put("code",uuid);
		smm.setText(uuid);
		javaMailSender.send(smm);
		int result=userService.addUserByMail(user);
		if(result>0){
			return  "mail";
		}
		return "false";
	}
	
	//邮箱验证码验证
	public String registers(){
		String jiaoyan=(String) ActionContext.getContext().getSession().get("code");
		if(jiaoyan.equals(email)){
			User us=(User) ActionContext.getContext().getSession().get("user");
			String yhemail=us.getYhemail();
			int result=userService.update(yhemail);
			return "registerSuccess";
			
		}
		return  "false";
	}
	
	public String login(){
		LogManager.getLogger().debug("login登陆操作...");
		User users=userService.login(user);
		System.out.println(users);
		if(users==null){
			return "fail";
		}
		return "index";
	}
	
	public String loginByMail(){
		LogManager.getLogger().debug("login登陆操作...");
		User users=userService.loginByMail(user);
		System.out.println(users);
		if(users==null){
			return "fail";
		}
		return "index";
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
