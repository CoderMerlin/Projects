package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.WorksType;
import com.yc.hdmedia.service.WorksService;

@Controller("worksTypeAction")
public class WorksTypeAction implements ModelDriven<WorksType>{
	
	
	private WorksType worksType=new WorksType();
	@Autowired
	private WorksService worksService;
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	
	/**
	 * 获取所有的
	 * @author HM
上午11:50:13修改
	 * @return
	 */
	public String getAllWorksType(){
		DataMap.clear();
		List<WorksType> wt=worksService.findAllType();
		if(wt!=null){
			DataMap.put("rows", wt);
			return "success";
		}
		return "fail";
	}
	
	
	@Override
	public WorksType getModel() {
		return worksType;
	}

}
