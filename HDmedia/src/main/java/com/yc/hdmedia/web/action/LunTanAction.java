package com.yc.hdmedia.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.LunTan;
import com.yc.hdmedia.service.ILunTanService;

/**
 * 二
 * @author Administrator
 *
 */
@Controller("lunTanAction")
public class LunTanAction implements ModelDriven<LunTan>{
	@Autowired
	private ILunTanService iLunTanService;
	
	private LunTan lunTan;
	private JsonObject<LunTan> jsonObject;
	private int page;
	private int rows;
	private String ltids;
	
	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public JsonObject<LunTan> getJsonObject() {
		return jsonObject;
	}

	public void setLtids(String ltids) {
		this.ltids = ltids;
	}

	public String showAll(){
		List<LunTan> luntans=iLunTanService.find(page, rows);
		int total=iLunTanService.total();
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setRows(luntans);
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String addLunTanInfo(){
		int result = iLunTanService.addLunTan(lunTan);
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	
	public String updateLunTanInfo(){
		System.out.println("进入修改论坛=====>"+lunTan);
		int result = iLunTanService.updateLunTan(lunTan);
		System.out.println("进入修改论坛结果=====>"+result);
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String delLunTanInfo(){
		System.out.println("进入修改论坛=====>"+ltids);
		int result = iLunTanService.delLunTan(ltids);
		System.out.println("进入修改论坛结果=====>"+result);
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setTotal(result);
		return "success";
	}
	

	@Override
	public LunTan getModel() {
		this.lunTan=new LunTan();
		return lunTan;
	}

}
