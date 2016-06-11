package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.LunTan;
import com.yc.hdmedia.service.ILunTanService;

/**
 * 二
 * @author Administrator
 *
 */
@Controller("lunTanAction")
public class LunTanAction implements ModelDriven<LunTan> {
	@Autowired
	private ILunTanService iLunTanService;
	private LunTan lunTan=new LunTan();
	private JsonObject<LunTan> jsonObject;
	private int page;
	private int rows;
	private String ltids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;		//上传的文件名
	private String[] uploadContentType;		//上传的文件类型

	

	public String showAll(){
		List<LunTan> luntans=iLunTanService.find(page, rows);
		int total=iLunTanService.total();
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setRows(luntans);
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String addLunTanInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		System.out.println("获取路径"+path);
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
				File[] fs=new File(path).listFiles();		//取出所有上传的文件
				List<String> files=new ArrayList<String>();
				
				for(File file:fs){
					files.add(file.getName());
				}
				ActionContext.getContext().getSession().put("image", files);
			} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		lunTan.setLtyl2(uploadFileName[0]);
		int result = iLunTanService.addLunTan(lunTan);
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String findLunTanById(){
		List<LunTan> ll = iLunTanService.findById(String.valueOf(lunTan.getLtid()));
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setRows(ll);
		return "success";
	}
	
	public String updateLunTanInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
				File[] fs=new File(path).listFiles();		//取出所有上传的文件
				List<String> files=new ArrayList<String>();
				
				for(File file:fs){
					files.add(file.getName());
				}
				ActionContext.getContext().getSession().put("image", files);
			} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		lunTan.setLtyl2(uploadFileName[0]);
		System.out.println("进入到论坛更新===========>"+lunTan);
		int result = iLunTanService.updateLunTan(lunTan);
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	public String delLunTanInfo(){
		System.out.println("进入修改论坛=====>"+ltids);
		int result = iLunTanService.delLunTan(ltids);
		System.out.println("进入修改论坛结果=====>"+result);
		jsonObject=new JsonObject<LunTan>();
		jsonObject.setTotal(result);
		return "success";
	}
	
	
	
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public JsonObject<LunTan> getJsonObject() {
		return jsonObject;
	}
	public void setLtids(String ltids) {
		this.ltids = ltids;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	@Override
	public LunTan getModel() {
		return lunTan;
	}
	
	}
