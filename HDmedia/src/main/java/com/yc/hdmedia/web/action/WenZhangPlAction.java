package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.Wzyhpinglun;
import com.yc.hdmedia.service.IWzyhpinglunService;

@Controller("wenZhangPlAction")
public class WenZhangPlAction implements ModelDriven<Wzyhpinglun>{
	@Autowired
	private IWzyhpinglunService iWzyhpinglunService;
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private Wzyhpinglun wzyhpinglun=new Wzyhpinglun();
	private int page ;
	private int rows;
	private String wzplids;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setWzplids(String wzplids) {
		this.wzplids = wzplids;
	}
	
	/**
	 * @author HM
上午11:53:41修改
	 * @return
	 */
	public String getPageWzyhpinglunInfo(){
		DataMap.clear();
		List<Wzyhpinglun> wzpl=iWzyhpinglunService.findAllPingLun(page, rows);
		if(wzpl!=null){
			DataMap.put("rows", wzpl);
			DataMap.put("total", iWzyhpinglunService.total());
			return "success";
		}
		return "fail";
	}
	
	/**
	 * @author HM
上午11:54:39修改
	 * @return
	 */
	public String addWzyhpinglunInfo(){
		DataMap.clear();
		int total=iWzyhpinglunService.addWzyhpinglun(wzyhpinglun);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * @author HM
上午11:55:27修改
	 * @return
	 */
	public String updateWzyhpinglunInfo(){
		DataMap.clear();
		int total=iWzyhpinglunService.updateWzyhpinglun(wzyhpinglun);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * @author HM
上午11:56:17修改
	 * @return
	 */
	public String findPingLunById(){
		DataMap.clear();
		List<Wzyhpinglun> wzpl=iWzyhpinglunService.findById(wzyhpinglun.getWzplid());
		if(wzpl!=null){
			DataMap.put("rows", wzpl);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 
	 *@author HM
上午11:57:06修改
	 * @return
	 */
	public String delWzyhpinglunInfo(){
		int total=iWzyhpinglunService.del(wzplids);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}

	@Override
	public Wzyhpinglun getModel() {
		return wzyhpinglun;
	}
}
