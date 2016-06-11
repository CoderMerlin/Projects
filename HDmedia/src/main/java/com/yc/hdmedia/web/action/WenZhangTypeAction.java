package com.yc.hdmedia.web.action;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.WenzhangType;
import com.yc.hdmedia.service.IWenzhangTypeService;

@Controller("wenZhangTypeAction")
public class WenZhangTypeAction{
	
	@Autowired
	private IWenzhangTypeService iWenzhangTypeService;
	private WenzhangType wzType=new WenzhangType();
	private JsonObject<WenzhangType> jsonObject;
	private int page;
	private int rows;
	private String wztypeids;
	private int wztypeid; //文章类型ID
	private String wzname; //文章类型名
	private int status; //文章类型状态
	
	public String addTypeInfo(){
		wzType.setWzname(wzname);
		wzType.setStatus(status);
		int result=iWenzhangTypeService.addWenzhangtype(wzType);
		jsonObject=new JsonObject<WenzhangType>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String delTypeInfo(){
		int result=iWenzhangTypeService.del(wztypeids);
		jsonObject=new JsonObject<WenzhangType>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String updateTypeInfo(){
		wzType.setWztypeid(wztypeid);
		wzType.setWzname(wzname);
		wzType.setStatus(status);
		int result=iWenzhangTypeService.update(wzType);
		jsonObject=new JsonObject<WenzhangType>();
		jsonObject.setTotal(result);
		return "success";
	}
	public String getPageTypeInfo(){
		List<WenzhangType> wenZhangType=iWenzhangTypeService.find(page, rows);
		jsonObject=new JsonObject<WenzhangType>();
		jsonObject.setRows(wenZhangType);
		jsonObject.setTotal(iWenzhangTypeService.total());
		return "success";
	}
	
	public String getAllType(){
		List<WenzhangType> wztype=iWenzhangTypeService.finds();
		LogManager.getLogger().debug("所有文章类型==========>"+wztype);
		jsonObject=new JsonObject<WenzhangType>();
		jsonObject.setRows(wztype);
		return "success";
	}
	
	public void setWztypeid(int wztypeid) {
		this.wztypeid = wztypeid;
	}
	public void setWzname(String wzname) {
		this.wzname = wzname;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public JsonObject<WenzhangType> getJsonObject() {
		return jsonObject;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setWztypeids(String wztypeids) {
		this.wztypeids = wztypeids;
	}
	
	
}
