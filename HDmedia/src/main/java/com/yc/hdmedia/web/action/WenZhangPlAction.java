package com.yc.hdmedia.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.Wzyhpinglun;
import com.yc.hdmedia.service.IWzyhpinglunService;

@Controller("wenZhangPlAction")
public class WenZhangPlAction implements ModelDriven<Wzyhpinglun>{
	@Autowired
	private IWzyhpinglunService iWzyhpinglunService;
	private JsonObject<Wzyhpinglun> jsonObject;
	private Wzyhpinglun wzyhpinglun=new Wzyhpinglun();
	private int page ;
	private int rows;
	private String wzplids;
	public JsonObject<Wzyhpinglun> getJsonObject() {
		return jsonObject;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setWzplids(String wzplids) {
		this.wzplids = wzplids;
	}
	
	public String getPageWzyhpinglunInfo(){
		List<Wzyhpinglun> wzpl=iWzyhpinglunService.findAllPingLun(page, rows);
		int total=iWzyhpinglunService.total();
		jsonObject=new JsonObject<Wzyhpinglun>();
		jsonObject.setRows(wzpl);
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String addWzyhpinglunInfo(){
		System.out.println("添加评论====>"+wzyhpinglun);
		int total=iWzyhpinglunService.addWzyhpinglun(wzyhpinglun);
		jsonObject=new JsonObject<Wzyhpinglun>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String updateWzyhpinglunInfo(){
		int total=iWzyhpinglunService.updateWzyhpinglun(wzyhpinglun);
		jsonObject=new JsonObject<Wzyhpinglun>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String findPingLunById(){
		List<Wzyhpinglun> wzpl=iWzyhpinglunService.findById(wzyhpinglun.getWzplid());
		jsonObject=new JsonObject<Wzyhpinglun>();
		jsonObject.setRows(wzpl);
		return "success";
	}
	
	public String delWzyhpinglunInfo(){
		int total=iWzyhpinglunService.del(wzplids);
		jsonObject=new JsonObject<Wzyhpinglun>();
		jsonObject.setTotal(total);
		return "success";
	}

	@Override
	public Wzyhpinglun getModel() {
		return wzyhpinglun;
	}
}
