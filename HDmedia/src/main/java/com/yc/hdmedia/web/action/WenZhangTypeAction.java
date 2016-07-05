package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.WenzhangType;
import com.yc.hdmedia.service.IWenzhangTypeService;

@Controller("wenZhangTypeAction")
public class WenZhangTypeAction{
	
	@Autowired
	private IWenzhangTypeService iWenzhangTypeService;
	private WenzhangType wzType=new WenzhangType();
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private int page;
	private int rows;
	private String wztypeids;
	private int wztypeid; //文章类型ID
	private String wzname; //文章类型名
	private int status; //文章类型状态
	
	/**
	 * @author HM
上午11:40:38修改
	 * @return
	 */
	public String addTypeInfo(){
		DataMap.clear();
		wzType.setWzname(wzname);
		wzType.setStatus(status);
		int result=iWenzhangTypeService.addWenzhangtype(wzType);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * @author HM
上午11:41:17修改
	 * @return
	 */
	public String delTypeInfo(){
		DataMap.clear();
		int result=iWenzhangTypeService.del(wztypeids);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	public String updateTypeInfo(){
		DataMap.clear();
		wzType.setWztypeid(wztypeid);
		wzType.setWzname(wzname);
		wzType.setStatus(status);
		int result=iWenzhangTypeService.update(wzType);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * @author HM
上午11:44:21修改
	 * @return
	 */
	public String getPageTypeInfo(){
		DataMap.clear();
		List<WenzhangType> wenZhangType=iWenzhangTypeService.find(page, rows);
		if(wenZhangType!=null){
			DataMap.put("total", iWenzhangTypeService.total());
			DataMap.put("rows", wenZhangType);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * @author HM
上午11:42:54修改
	 * @return
	 */
	public String getAllType(){
		DataMap.clear();
		List<WenzhangType> wztype=iWenzhangTypeService.finds();
		if(wztype!=null){
			DataMap.put("rows", wztype);
			return "success";
		}
		return "fail";
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
