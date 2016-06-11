package com.yc.hdmedia.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.HuiTie;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.service.HuiTieService;

/*
 * 天天人员
 */
@Controller("huiTieAction")
public class HuiTieAction{
	@Autowired
	private HuiTieService huiTieService;
	private HuiTie huiTie=new HuiTie();
	private JsonObject<HuiTie> jsonObject;
	private String htids;
	private int page;
	private int rows;
	
	
	public String getPageHuiTieInfo(){
		List<HuiTie> huiTies=huiTieService.findAllHuiTie(page, rows);
		int result=huiTieService.total();
		jsonObject=new JsonObject<HuiTie>();
		jsonObject.setRows(huiTies);
		jsonObject.setTotal(result);
		return "success";
	}

	public String addHuiTieInfo(){
		int result=huiTieService.addHuiTie(huiTie);
		jsonObject=new JsonObject<HuiTie>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String updateHuiTieInfo(){
		int result=huiTieService.updateHuiTie(huiTie);
		jsonObject=new JsonObject<HuiTie>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	
	public String findHuiTieByHtid(){
		List<HuiTie> ht=huiTieService.find(huiTie.getHtid());
		jsonObject=new JsonObject<HuiTie>();
		jsonObject.setRows(ht);
		return "success";
	}
	
	public String delHuiTieInfo(){
		int result=huiTieService.delHuiTie(htids);
		jsonObject=new JsonObject<HuiTie>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public void setHtids(String htids) {
		this.htids = htids;
	}

	public JsonObject<HuiTie> getJsonObject() {
		return jsonObject;
	}

	public void setPage(int page) {
		System.out.println(page);
		this.page = page;
	}
	public void setRows(int rows) {
		System.out.println(rows);
		this.rows = rows;
	}
}
