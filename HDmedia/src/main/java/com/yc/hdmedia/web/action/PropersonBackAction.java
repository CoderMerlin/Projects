package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

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
	//路径
	
	
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
		String path=ServletActionContext.getServletContext().getRealPath("../upload");
		String fileName = String.valueOf(System.currentTimeMillis()+new Random().nextInt(10000));
		String picture="";
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+fileName+uploadFileName[i]));//开始上传
    			//为了方便多次测试，把上传到服务器的文件中，在工程中也传一份，开发完成后在删除
    			FileUtils.copyFile(upload[i], new File("D:\\pictrues"+"/"+fileName+uploadFileName[i]));//开始上传
    			System.out.println("上传成功！");
    			
			} catch (IOException e) {
				System.out.println("上传失败...");
				e.printStackTrace();
			}
		}
		if(uploadFileName.length==1){
			picture = uploadFileName[0];
	    }else if(uploadFileName.length>1){
		    for(String as:uploadFileName){
		    	picture += as+",";
		    }
		    picture = picture.substring(0, picture.length()-1);
	    }
		propersonBack.setPpimg(fileName+picture);
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
