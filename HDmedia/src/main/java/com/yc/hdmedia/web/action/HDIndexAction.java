package com.yc.hdmedia.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.HDIndexBean;
import com.yc.hdmedia.service.HDIndexService;



public class HDIndexAction implements SessionAware{

	private HDIndexService hDIndexService;
	private Map<String, Object> session;
	
	
	
	
	public String getIndexAllInfo(){
		List<HDIndexBean>  hdIndexInfo=hDIndexService.getIndexAllInfo();
		
		return "fail";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
