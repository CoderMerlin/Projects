package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.websocket.Session;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.PropersonBack;
import com.yc.hdmedia.service.GeoraphyBackService;


//人物
@Controller("propersonBackAction")
public class PropersonBackAction implements SessionAware,ModelDriven<PropersonBack>{
	@Autowired
	private GeoraphyBackService georaphyBackService;
	
	private Map<String, Object> session;
	private PropersonBack propersonBack;
	

	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private File[] upload;//上传文件
	private String[] uploadFileName;//上传文件名
	private String[] uploadContentType;//上传文件类型
	
	
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	@Override
	public PropersonBack getModel() {
		return propersonBack=new PropersonBack();
	}

	public String addPersonInfo(){
		DataMap.clear();
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		System.out.println("上传的地址："+path);
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));//开始上传
				File[] fs=new File(path).listFiles(); //取出所有上传文件
				List<String> files=new ArrayList<String>();
				for(File file:fs){
					files.add(file.getName());
				}
				ActionContext.getContext().getSession().put("image",files);//解耦合方法取出session
			} catch (IOException e) {
				System.out.println("上传失败...");
				e.printStackTrace();
			}
			propersonBack.setPpimg(path+"/"+uploadFileName[0]);
			
		}
		LogManager.getLogger().debug("获取前台人物信息==:>"+propersonBack);
		int result=georaphyBackService.addPersonInfo(propersonBack);
		if(result>0){
			DataMap.put("rows", result);
			return "success";
		}
		DataMap.put("rows", result);
		return "fail";
	}
	
	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	
}
