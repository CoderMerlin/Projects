package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.HuiTie;
import com.yc.hdmedia.service.HuiTieService;

/*
 * 天天人员
 */
@Controller("huiTieAction")
public class HuiTieAction{
	@Autowired
	private HuiTieService huiTieService;
	private HuiTie huiTie=new HuiTie();
	
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	
	
	private String htids;
	private int page;
	private int rows;
	
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	public void setHtids(String htids) {
		this.htids = htids;
	}


	public void setPage(int page) {
		System.out.println(page);
		this.page = page;
	}
	public void setRows(int rows) {
		System.out.println(rows);
		this.rows = rows;
	}
	
	/**
	 * 获取所有的回帖信息
	 * @author HM修改
	 * @return
	 */
	public String getPageHuiTieInfo(){
		DataMap.clear();
		List<HuiTie> huiTies=huiTieService.findAllHuiTie(page, rows);
		
		int total=huiTieService.total();
		if(huiTies!=null){
			DataMap.put("rows", huiTies);
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}

	public String addHuiTieInfo(){
		DataMap.clear();
		int result=huiTieService.addHuiTie(huiTie);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	public String updateHuiTieInfo(){
		DataMap.clear();
		int result=huiTieService.updateHuiTie(huiTie);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	
	public String findHuiTieByHtid(){
		DataMap.clear();
		List<HuiTie> ht=huiTieService.find(huiTie.getHtid());
		if(ht!=null){
			DataMap.put("ht", ht);
			return "success";
		}
		return "fail";
	}
	
	public String delHuiTieInfo(){
		DataMap.clear();
		int result=huiTieService.delHuiTie(htids);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	
}
