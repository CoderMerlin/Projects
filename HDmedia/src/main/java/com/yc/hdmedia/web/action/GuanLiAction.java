package com.yc.hdmedia.web.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.service.GuanLiService;
import com.yc.hdmedia.utils.PageUtil;

@Controller("guanLiAction")
public class GuanLiAction implements ModelDriven<GuanLi>,SessionAware {
	@Autowired
	private GuanLiService guanLiService;
	private Map<String, Object> session;
	private GuanLi guanLi;
	private int page;
	private int rows;
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	
	public void setGuanLi(GuanLi guanLi) {
		this.guanLi = guanLi;
	}

	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}

	//管理员登陆操作
		public String login(){
			LogManager.getLogger().debug("login登陆操作...");
			GuanLi guanlis=guanLiService.login(guanLi);
			System.out.println(guanlis);
			if(guanlis==null){
				return "fail";
			}
			session.put("loginGuanLi",guanLi);
			return "index";
		}
	
	
	public String getAllGuanLi(){
		DataMap.clear(); 
		List<GuanLi> guanLis=guanLiService.findAllGuanLis(page,rows);
		if(guanLis!=null){
			DataMap.put("total",guanLiService.total());
			DataMap.put("rows", guanLis);
			return "success";
		}
		return "fail";
	}
	
	
	
	public String addGuanLi(){
		System.out.println("进入了添加管理员！"+guanLi);
		int result=guanLiService.addGuanLi(guanLi);
		if(result>0){
			DataMap.put("result", result);
			return "success";
		}
		return "fail";
	}
	public String getGuanLiById(){
		int glid=guanLi.getGlid();
		LogManager.getLogger().debug("传过来的glid==》"+glid);
		GuanLi gl=guanLiService.getGuanLiById(glid);
		LogManager.getLogger().debug("查到的数据==》"+gl);
		if(gl!=null){
			DataMap.put("guanLi", gl);
			return "success";
		}
		return "fail";
	}
	public String updateGuanLi(){
		System.out.println("修改的值==》"+guanLi);
		int result=guanLiService.updateGuanLiInfo(guanLi);
		if(result>0){
			DataMap.put("result", result);
			return "success";
		}
		return "fail";
	}
	public String deleteGuanLiById(){
		String gls=ServletActionContext.getRequest().getParameter("glids");
		String[] glis=gls.split(",");
		int[] glids=new int[glis.length];     
        for (int i=0; i<glids.length; i++) {
        	glids[i] = Integer.parseInt(glis[i]);
        	System.out.println(glids[i]);
        }
        int result=guanLiService.del(glids);
		System.out.println(result);
		if(result>0){
			DataMap.put("delId", result);
			return "success";
		}
		return "fail";
	}
	
	
	@Override
	public GuanLi getModel() {
		return guanLi=new GuanLi();
	}


	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
