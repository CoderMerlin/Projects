package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.YongHuZC;
import com.yc.hdmedia.service.YongHuZCService;
@Controller("yongHuZCAction")
public class YongHuZCAction implements ModelDriven<YongHuZC>{
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	private YongHuZC yongHuZC;
	private int page;
	private int rows;
	@Autowired
	private YongHuZCService yongHuZCService;
	
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	
	public String getPageYongHuZCInfo(){
		DataMap.clear();
		List<YongHuZC> yongHuZCs=yongHuZCService.findAllYongHuZC(page,rows);
		if(yongHuZCs!=null){
			DataMap.put("total",yongHuZCService.total());
			DataMap.put("rows", yongHuZCs);
			return "success";
		}
		return "fail";
	}
	
	public String addYongHuZCInfo(){
		DataMap.clear();
		int result=yongHuZCService.addYongHuZC(yongHuZC);
		if(result>0){
			DataMap.put("result", result);
			return "success";
		}
		return "fail";
	}
	public String delYongHuZCById(){
		DataMap.clear();
		String yhs=ServletActionContext.getRequest().getParameter("yhzcids");
		String[] yhzcs=yhs.split(",");
		int[] yhzcids=new int[yhzcs.length];     
        for (int i=0; i<yhzcids.length; i++) {
        	yhzcids[i] = Integer.parseInt(yhzcs[i]);
        	System.out.println(yhzcids[i]);
        }
		int result=yongHuZCService.delYongHuZCByIds(yhzcids);
		if(result>0){
		DataMap.put("delId", result);
		return "success";
		}
		return "fail";
	}
	public String getYongHuZCById(){
		DataMap.clear();
		int yhzcid=yongHuZC.getYhzcid();
		YongHuZC yh=yongHuZCService.getYongHuZCById(yhzcid);
		LogManager.getLogger().debug("查到的数据==》"+yh);
		if(yh!=null){
			DataMap.put("yongHuZC", yh);
			return "success";
		}
		return "fail";
	}
	public String updateYongHuZCInfo(){
		int result=yongHuZCService.updateYongHuZCInfo(yongHuZC);
		if(result>0){
			DataMap.put("result", result);
			return "success";
		}
		return "fail";
	}
	
	
	
	@Override
	public YongHuZC getModel() {
		yongHuZC=new YongHuZC();
		return yongHuZC;
	}
}
