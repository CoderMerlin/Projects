package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.yc.hdmedia.entity.PropersonBack;
import com.yc.hdmedia.entity.ProvinceBack;
import com.yc.hdmedia.service.GeoraphyBackService;

@Controller("georaphyBackAction")
public class GeoraphyBackAction implements SessionAware{
	
	@Autowired
	private GeoraphyBackService georaphyBackService;
	private Map<String, Object> session;
	
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}

	private int prid;   //省份的Id
	public void setPrid(int prid) {
		this.prid = prid;
	}
	
	private int prpid;  //人物的id
	public void setPrpid(int prpid) {
		this.prpid = prpid;
	}
	/**
	 * 后台查找所有的省份
	 * @return
	 */
	public String getAllProvinces(){
		DataMap.clear();
		List<ProvinceBack> provinces=georaphyBackService.getAllProvinces(page,rows);
		if(provinces!=null){
			DataMap.put("total",georaphyBackService.total());
			DataMap.put("rows", provinces);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 后台通过省份id获得对应的所有人物信息
	 * @return
	 */
	public String getAllPersons(){
		DataMap.clear();
		List<PropersonBack> persons=georaphyBackService.getAllPersonsByPrid(prid);
		if(persons!=null){
			DataMap.put("persons", persons);
			return "success";
		}
		return "fail";
	}
	
	//通过人物id获取人物的信息
	public String getPersonByPrpid(){
		DataMap.clear();
		PropersonBack person=georaphyBackService.getPersonByPrpid(prpid);
		if(person!=null){
			DataMap.put("rows", person);
			return "success";
		}
		return "fail";
	}	
	
	//后台获取所有省份的名称
	public String getAllProvincePrnames(){
		DataMap.clear();
		List<ProvinceBack> provinceNames=georaphyBackService.getAllProvincePrnames();
		if(provinceNames!=null){
			DataMap.put("rows", provinceNames);
			return "success";
		}
		return "fail";
		
	}
	
	//后台通过省份id获得所有的人物名称
	public String getAllPersonNamesByPrid(){
		DataMap.clear();
		List<PropersonBack> personNames=georaphyBackService.getAllPersonNamesByPrid(prid);
		if(personNames!=null){
			DataMap.put("rows", personNames);
			return "success";
		}
		return "fail";
		
	}
	
	//添加人物信息
	public String addPersonInfo(){
		
		
		return "fail";
	}
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
	
}
