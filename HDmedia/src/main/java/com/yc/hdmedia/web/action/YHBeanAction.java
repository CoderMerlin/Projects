package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.YongHuBean;
import com.yc.hdmedia.service.YongHuBeanService;

@Controller("yHBeanAction")
public class YHBeanAction implements ModelDriven<YongHuBean>{
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	private YongHuBean yongHuBean;
	
	@Autowired
	private YongHuBeanService yongHuBeanService;
	
	@Override
	public YongHuBean getModel() {
		yongHuBean=new YongHuBean();
		return yongHuBean;
	}
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}

	
	public String findYongHuByInfo(){
		
		System.out.println(yongHuBean);
		List<YongHuBean> yhb=yongHuBeanService.findYongHuBeanByInfo(yongHuBean);
		System.out.println(yhb);
		if(yhb!=null){
			DataMap.put("rows", yhb);
			return "success";
		}
		return "fail";
	}	
}
