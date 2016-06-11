package com.yc.hdmedia.web.action;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.WorksType;
import com.yc.hdmedia.service.WorksService;

@Controller("worksTypeAction")
public class WorksTypeAction implements ModelDriven<WorksType>{
	private WorksType worksType=new WorksType();
	@Autowired
	private WorksService worksService;
	private JsonObject<WorksType> jsonObject;
	
	public String getAllWorksType(){
		List<WorksType> wt=worksService.findAllType();
		jsonObject=new JsonObject<WorksType>();
		jsonObject.setRows(wt);
		return "success";
	}
	
	public JsonObject<WorksType> getJsonObject() {
		return jsonObject;
	}

	@Override
	public WorksType getModel() {
		return worksType;
	}

}
