package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.websocket.Session;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.PropersonBack;
import com.yc.hdmedia.service.GeoraphyBackService;


//人物
@Controller("propersonBackAction")
public class PropersonBackAction implements SessionAware,ModelDriven<PropersonBack>{
	@Autowired
	private GeoraphyBackService georaphyBackService;
	
	private Map<String, Object> session;
	private PropersonBack propersonBack;
	

	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}

	@Override
	public PropersonBack getModel() {
		return propersonBack=new PropersonBack();
	}

	public String addPersonInfo(){
		DataMap.clear();
		LogManager.getLogger().debug("获取前台人物信息==:>"+propersonBack);
		int result=georaphyBackService.addPersonInfo(propersonBack);
		System.out.println("result"+result);
		if(result>0){
			DataMap.put("rows", result);
			return "success";
		}
		DataMap.put("rows", result);
		return "fail";
	}
	
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
}
