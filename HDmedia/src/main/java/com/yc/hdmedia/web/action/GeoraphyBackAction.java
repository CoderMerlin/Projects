package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.ProvinceBack;
import com.yc.hdmedia.service.GeoraphyBackService;

@Controller("georaphyBackAction")
public class GeoraphyBackAction implements SessionAware{
	
	@Autowired
	private GeoraphyBackService georaphyBackService;
	private Map<String, Object> session;
	
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	/**
	 * 后台查找所有的省份
	 * @return
	 */
	public String getAllProvinces(){
		DataMap.clear();
		List<ProvinceBack> provinces=georaphyBackService.getAllProvinces(page,rows);
		System.out.println("所有的省份"+provinces);
		if(provinces!=null){
			DataMap.put("total",georaphyBackService.total());
			DataMap.put("rows", provinces);
			return "success";
		}
		return "fail";
	}
	
	

	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	
}
