package com.yc.hdmedia.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.LunTan;
import com.yc.hdmedia.entity.LunTanBean;
import com.yc.hdmedia.entity.TieZiBean;
import com.yc.hdmedia.service.ILunTanService;
import com.yc.hdmedia.service.LunTanService;
import com.yc.hdmedia.service.TieZiService;

/**
 * 二
 * @author Administrator
 *
 */
@Controller("lunTanAction")
public class LunTanAction implements ModelDriven<LunTan>,SessionAware{
	@Autowired
	private ILunTanService iLunTanService;
	@Autowired
	private LunTanService lunTanService;
	@Autowired
	private TieZiService tieZiService;
	private Map<String, Object> session;
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
	
	public String find(){
		List<LunTanBean> luntanjlq=lunTanService.findLunTan_jlq();
		List<LunTanBean> luntanwhjlq=lunTanService.findLunTan_whjlq();
		List<LunTanBean> luntanzyfx=lunTanService.findLunTan_zyfx();
		List<LunTanBean> luntanzzhz=lunTanService.findLunTan_zzhz();
		List<LunTanBean> luntanz=lunTanService.findLunTan();
		TieZiBean count=tieZiService.findCount();
		TieZiBean TerdayCount=tieZiService.findTerdayCount();
		TieZiBean YesterdayCount=tieZiService.findYesterdayCount();
		session.put("luntanjlq", luntanjlq);
		System.out.println("====>"+luntanjlq);
		session.put("luntanwhjlq", luntanwhjlq);
		session.put("luntanzyfx", luntanzyfx);
		session.put("luntanzzhz", luntanzzhz);
		session.put("luntan", luntanz);
		session.put("count", count);
		session.put("TerdayCount", TerdayCount);
		session.put("YesterdayCount", YesterdayCount);
		return "lunTanSuccess";
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
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
}
