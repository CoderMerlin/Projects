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
	
	
	
	
	
	//弘道首页获取所有的页面信息
	public String getIndexAllInfo(){
		List<HDIndexBean>  hdIndexInfo=hDIndexService.getIndexAllInfo();
		if(hdIndexInfo!=null){
			System.out.println("获取的首页信息:"+hdIndexInfo);
			return "success";
		}
		return "fail";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
