package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	private YongHu yongHu;
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	private int page;
	private int rows;
	@Autowired
	private YongHuService yongHuService;
	
	private File[] upload;//�ϴ��ļ�
	private String[] uploadFileName;//�ϴ��ļ���
	private String[] uploadContentType;//�ϴ��ļ�����
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
		yongHu=new YongHu();
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
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));//��ʼ�ϴ�
				File[] fs=new File(path).listFiles(); //ȡ�������ϴ��ļ�
				List<String> files=new ArrayList<String>();
				for(File file:fs){
					files.add(file.getName());
				}
				ActionContext.getContext().getSession().put("image",files);//����Ϸ���ȡ��session
			} catch (IOException e) {
				System.out.println("�ϴ�ʧ��...");
				e.printStackTrace();
			}
		}
		yongHu.setYhphoto(path+"/"+uploadFileName[0]);
		int result=yongHuService.addYungHuInfo(yongHu);
		DataMap.put("result",result);
		return "addSuccess";
	}
	
	public String updateYongHuInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));//��ʼ�ϴ�
				System.out.println("�ϴ��ɹ�...");
				File[] fs=new File(path).listFiles(); //ȡ�������ϴ��ļ�
				List<String> files=new ArrayList<String>();
				for(File file:fs){
					files.add(file.getName());
				}
				ActionContext.getContext().getSession().put("image",files);//����Ϸ���ȡ��session
			} catch (IOException e) {
				System.out.println("�ϴ�ʧ��...");
				e.printStackTrace();
			}
		}
		int result=yongHuService.updateYungHuInfo(yongHu);
		if(result>0){
			return "updatesuccess";
		}
		return "fail";
	}
}