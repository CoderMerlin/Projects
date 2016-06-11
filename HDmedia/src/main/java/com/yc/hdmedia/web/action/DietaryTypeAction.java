package com.yc.hdmedia.web.action;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.DietaryType;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.service.DietaryService;

@Controller("dietaryTypeAction")
public class DietaryTypeAction implements ModelDriven<DietaryType>{

	@Autowired
	private DietaryService dietaryService;
	private DietaryType dietaryType=new DietaryType();
	private JsonObject< DietaryType> jsonObject;
	public JsonObject<DietaryType> getJsonObject() {
		return jsonObject;
	}
	
	
	public String getAllType(){
		List<DietaryType> dtp=dietaryService.findAllType();
		jsonObject=new JsonObject<DietaryType>();
		jsonObject.setRows(dtp);
		return "success";
	}
	
	
	@Override
	public DietaryType getModel() {
		return dietaryType;
	}

}
