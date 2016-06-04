package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.YongHu;
import com.yc.hdmedia.service.YongHuService;

/**
 * 前台用户个人资料
 * @author HM
 *
 */
@Controller("personalDataAction")
public class PersonalDataAction implements SessionAware,ModelDriven<YongHu> {
	private YongHu yongHu;
	private Map<String, Object> session;
	
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	@Autowired
	private YongHuService yongHuService;



	
	//前台获取用户信息
	public String getYongHuInfo(){
		DataMap.clear();
		YongHu yonghu=yongHuService.getYongHuInfo(yongHu.getYhzcid());
		LogManager.getLogger().debug("前台用户信息==》"+yonghu);
		if(yonghu!=null){
			session.put("yongHuInfo", yonghu);
			return "success";
		}
		return "fail";
	}
	
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	@Override
	public YongHu getModel() {
		return yongHu=new YongHu();
	}
}
