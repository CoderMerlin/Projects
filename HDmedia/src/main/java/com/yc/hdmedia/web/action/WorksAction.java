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
import com.yc.hdmedia.entity.Works;
import com.yc.hdmedia.entity.WorksType;
import com.yc.hdmedia.service.WorksService;

@Controller("worksAction")
public class WorksAction implements ModelDriven<Works>{
	private Works works=new Works();
	@Autowired
	private WorksService worksService;
	private JsonObject<Works> jsonObject;
	private int page;
	private int rows;
	private String works_ids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
		
		
	public String getPageWorksInfo(){
		List<Works> dtp=worksService.findAllWorks(page, rows);
		int total=worksService.total();
		jsonObject=new JsonObject<Works>();
		jsonObject.setRows(dtp);
		jsonObject.setTotal(total);
		return "success";
	}
		
	
	public String addWorksInfo(){
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
		works.setWorks_img(photo);
		int total=worksService.addWorks(works);
		jsonObject=new JsonObject<Works>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	
	public String updateWorksInfo(){
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
		works.setWorks_img(photo);
		int total=worksService.updateWorks(works);
		jsonObject=new JsonObject<Works>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String delWorksInfo(){
		System.out.println(works_ids);
		int total=worksService.delWorks(works_ids);
		jsonObject=new JsonObject<Works>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String findWorksInfoById(){
		List<Works> dtp=worksService.findById(works.getWorks_id());
		jsonObject=new JsonObject<Works>();
		jsonObject.setRows(dtp);
		return "success";
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
	public JsonObject<Works> getJsonObject() {
		return jsonObject;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setWorks_ids(String works_ids) {
		this.works_ids = works_ids;
	}

	@Override
	public Works getModel() {
		return works;
	}

}
