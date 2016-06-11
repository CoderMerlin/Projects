package com.yc.hdmedia.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.NavBean;
import com.yc.hdmedia.service.NavService;

@Controller("navAction")
public class NavAction implements SessionAware{
	@Autowired
	public NavService navService;
	
	private Map<String, Object> session;

	public String list(){
		//查询出导航栏
		List<NavBean> nav=navService.findNavBean();
		session.put("navname", nav);
		System.out.println(nav);
		return "listSuccess";
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;		
	}	
}
