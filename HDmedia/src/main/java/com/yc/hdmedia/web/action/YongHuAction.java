package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.YongHu;
import com.yc.hdmedia.entity.YongHuZC;
import com.yc.hdmedia.service.YongHuService;
import com.yc.hdmedia.utils.UploadUtil;

@Controller("yongHuAction")
public class YongHuAction implements ModelDriven<YongHu>{
	private YongHu yongHu=new YongHu();
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	private int page;
	private int rows;
	@Autowired
	private YongHuService yongHuService;
	
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
	public YongHu getModel() {
		return yongHu;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	public String getAllYongHu(){
		DataMap.clear();
		List<YongHu> yongHus=yongHuService.findAllYongHu(page,rows);
		if(yongHus!=null){
			DataMap.put("total",yongHuService.total());
			DataMap.put("rows", yongHus);
			return "success";
		}
		return "fail";
	}
	public String findByYongHuZCById(){
		YongHu yh=yongHuService.findYongHuZCId(yongHu.getYhzcid());
		DataMap.put("yh", yh);
		return "success";
	}
	public String addYongHuInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));
				File[] fs=new File(path).listFiles(); 
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));//开始上传
				List<String> files=new ArrayList<String>();
				for(File file:fs){
					files.add(file.getName());
				}
				ActionContext.getContext().getSession().put("image",files);//解耦合方法取出session
			} catch (IOException e) {
				System.out.println("上传失败...");
				e.printStackTrace();
			}
		}
		yongHu.setYhphoto(path+"/"+uploadFileName[0]);
		int result=yongHuService.addYungHuInfo(yongHu);
		return "addSuccess";
	}
	
	public String updateYongHuInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));//开始上传
				System.out.println("上传成功...");
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
		}
		yongHu.setYhphoto(path+"/"+uploadFileName[0]);
		int result=yongHuService.updateYungHuInfo(yongHu);
		return "updatesuccess";
	}
	
	public String findPageYongHuInfo(){
		DataMap.clear();
		List<YongHu> yhs=yongHuService.findAllYongHuInfo(page,rows);
		if(yhs!=null){
			DataMap=new HashMap<String,Object>();
			DataMap.put("rows", yhs);
			DataMap.put("total",yongHuService.total());
			return "success";
		}
		return "fail";
	}
}
