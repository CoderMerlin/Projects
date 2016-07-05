package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.Dietary;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.service.DietaryService;

@Controller("dietaryAction")
public class DietaryAction implements ModelDriven<Dietary>{
	
	@Autowired
	private DietaryService dietaryService;
	private Dietary dietary=new Dietary();
	private JsonObject< Dietary> jsonObject;
	private int page;
	private int rows;
	private String dtids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
	
	public String getPageDietaryInfo(){
		List<Dietary> dtp=dietaryService.findAll(page, rows);
		int total=dietaryService.total();
		jsonObject=new JsonObject<Dietary>();
		jsonObject.setRows(dtp);
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String addDietary(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		String photo="";
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
				if(i<upload.length-1){
					photo+=uploadFileName[i]+",";
				}
		} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		photo+=uploadFileName[upload.length-1];
		dietary.setPhoto(photo);
		LogManager.getLogger().debug(dietary);
		int total=dietaryService.addDietary(dietary);
		System.out.println("添加======>"+total);
		jsonObject=new JsonObject<Dietary>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String findDietaryById(){
		List<Dietary> dtp=dietaryService.findById(dietary.getDtid());
		jsonObject=new JsonObject<Dietary>();
		jsonObject.setRows(dtp);
		return "success";
	}
	
	public String delDietary(){
		int total=dietaryService.delDietary(dtids);
		jsonObject=new JsonObject<Dietary>();
		jsonObject.setTotal(total);;
		return "success";
	}
	
	public String updateDietary(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		String photo="";
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
				if(i<upload.length-1){
					photo+=uploadFileName[i]+",";
					System.out.println(photo);
				}
		} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		photo+=uploadFileName[upload.length-1];
		dietary.setPhoto(photo);
		int total=dietaryService.updateDietary(dietary);
		jsonObject=new JsonObject<Dietary>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public JsonObject<Dietary> getJsonObject() {
		return jsonObject;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setDtids(String dtids) {
		this.dtids = dtids;
	}
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
	public Dietary getModel() {
		return dietary;
	}

}
