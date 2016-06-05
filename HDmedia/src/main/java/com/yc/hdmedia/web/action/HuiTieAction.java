package com.yc.hdmedia.web.action;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.HuiTie;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.service.HuiTieService;

/*
 * 天天人员
 */
@Controller("huiTieAction")
public class HuiTieAction implements ModelDriven<HuiTie>{
	@Autowired
	private HuiTieService huiTieService;
	private HuiTie huiTie;
	private JsonObject<HuiTie> jsonObject;
	private String htids;
	
	private int page;
	private int rows;
	
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

	public String getPageHuiTieInfo(){
		System.out.println("page=====>"+page);
		System.out.println("rows======>"+rows);
		List<HuiTie> huiTies=huiTieService.findAllHuiTie(page, rows);
		int result=huiTieService.total();
		jsonObject=new JsonObject<HuiTie>();
		jsonObject.setRows(huiTies);
		jsonObject.setTotal(result);
		return "success";
	}

	public String addHuiTieInfo(){
		System.out.println("添加回帖获取到的回帖=========》"+huiTie);
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
		List<HuiTie> ht=huiTieService.find(Integer.valueOf(huiTie.getHtid()));
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
	
	@Override
	public HuiTie getModel() {
		this.huiTie=new HuiTie();
		return huiTie;
	}
	
}
