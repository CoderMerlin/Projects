package com.yc.hdmedia.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.HDIndexBean;
import com.yc.hdmedia.service.HDIndexService;


@Controller("hDIndexAction")
public class HDIndexAction implements SessionAware{

	@Autowired
	private HDIndexService hDIndexService;
	private Map<String, Object> session;
	
	
	
	
	
	//弘道首页获取所有的页面信息
	public String getIndexAllInfo(){
		List<HDIndexBean>  hdIndexInfo=hDIndexService.getIndexAllInfo();
		if(hdIndexInfo!=null){
			for (HDIndexBean hdIndexBean : hdIndexInfo) {
				String wztext=hdIndexBean.getWztext();
				wztext=wztext.substring(0,75)+"....";
				hdIndexBean.setWztext(wztext);
			}
			System.out.println("信息"+hdIndexInfo);
			session.put("hdIndexInfos", hdIndexInfo);
			return "hdIndexInfoSuccess";
		}
		return "fail";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
