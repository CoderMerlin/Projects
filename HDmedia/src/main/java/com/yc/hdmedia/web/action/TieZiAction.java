package com.yc.hdmedia.web.action;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.TieZi;
import com.yc.hdmedia.service.TieZiService;
import com.yc.hdmedia.utils.UploadUtil;

/**
 * 突然
 * @author Administrator
 *
 */
@Controller("tieZiAction")
public class TieZiAction implements ModelDriven<TieZi>{

	@Autowired
	private TieZiService tiZiService;
	private TieZi tieZi;
	private JsonObject<TieZi> jsonObject;
	private UploadUtil uploadUtil;
	private int page;
	private int rows;
	private String tids;
	public JsonObject<TieZi> getJsonObject() {
		return jsonObject;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setTids(String tids) {
		this.tids = tids;
	}
	
	public UploadUtil getUploadUtil() {
		return uploadUtil;
	}
	
	public void setUploadUtil(UploadUtil uploadUtil) {
		this.uploadUtil = uploadUtil;
	}
	public String getPageTieZiInfo(){
		List<TieZi> tiezis=tiZiService.find(page, rows);
		int total=tiZiService.total();
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setRows(tiezis);
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String addTieZiInfo(){
		
		tieZi.setTzphoto(uploadUtil.upload());
		System.out.println("添加帖子========>"+tieZi);
		int result = tiZiService.addTieZi(tieZi);
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String delTieZiInfo(){
		int result=tiZiService.delTieZi(tids);
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String findTieZiByTid(){
		List<TieZi> tz=tiZiService.find(tieZi.getTid());
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setRows(tz);
		return "success";
	}
	
	
	public String updateTieZiInfo(){
		tieZi.setTzphoto(uploadUtil.upload());
		int result=tiZiService.updateTieZi(tieZi);
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	@Override
	public TieZi getModel() {
		this.tieZi=new TieZi();
		return tieZi;
	}

}
