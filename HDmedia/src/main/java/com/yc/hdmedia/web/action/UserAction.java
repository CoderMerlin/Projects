package com.yc.hdmedia.web.action;

import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
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
	//private JavaMailSender javaMailSender; //邮件发送对象

	private User user;
	@Autowired
	private UserService userService;
	private Map<String, Object> session;
	
	//用户名注册
	public String register(){
		LogManager.getLogger().debug("注册...");
		int result=userService.addUser(user);
			System.out.println(user);
			return "register";
			}
	
	/*//邮箱注册
	public String register2(){
		SimpleMailMessage smm=new SimpleMailMessage();
		smm.setFrom("studymail_test@163.com");
		smm.setTo(user.getYhemail());
		smm.setSubject("验证码");
		String uuid=vc.code();
		ActionContext.getContext().getSession().put("user",user);
		ActionContext.getContext().getSession().put("code", uuid);
		smm.setText(uuid);
		javaMailSender.send(smm);
		int result=userService.addUser(user);
		if(result>0){
			return  "tiaozhuan";
		}
		return "false";
		
	}
	*/
	public String login(){
		LogManager.getLogger().debug("login登陆操作...");
		User users=userService.login(user);
		System.out.println(users);
		if(users==null){
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
