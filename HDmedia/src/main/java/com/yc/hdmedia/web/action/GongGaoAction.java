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
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.GongGao;
import com.yc.hdmedia.service.GongGaoService;

@Controller("gongGaoAction")
public class GongGaoAction implements ModelDriven<GongGao>, SessionAware {

	@Autowired
	private GongGaoService gongGaoService;
	private GongGao gonggao;
	private int page;
	private int rows;
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
	private Map<String, Object> session;

	public void setGonggao(GongGao gonggao) {
		this.gonggao = gonggao;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	private Map<String, Object> DataMap = new HashMap<String, Object>();

	public Map<String, Object> getDataMap() {
		return DataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}

	// 添加
	public String add() {
		LogManager.getLogger().debug("添加...");
		System.out.println("gonggao"+gonggao);
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
			gonggao.setGgyl1((path+"/"+uploadFileName[0]));
			
		}
		int result = gongGaoService.addGongGao(gonggao);
		System.out.println("result"+result);
		if (result > 0) {
			DataMap.put("result", result);
			return "success";
		}
		return "fail";
	}

	public String getAllGongGao() {
		DataMap.clear();
		List<GongGao> gongGaos = gongGaoService.getAllGongGao(page, rows);
		if (gongGaos != null) {
			DataMap.put("total", gongGaoService.total());
			DataMap.put("rows", gongGaos);
			return "success";
		}
		return "fail";
	}

	public String findGongGaoByGid(){
		GongGao gg=gongGaoService.findGongGaoByGid(gonggao.getGid());
		DataMap.put("gg", gg);
		return "success";
	}
	
	// 修改
	public String update() {
		DataMap.clear();
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
			gonggao.setGgyl1((path+"/"+uploadFileName[0]));
		}
		System.out.println("gog"+gonggao);
		int result=gongGaoService.updateGongGao(gonggao);
		if(result>0){
			DataMap.put("results", result);
			System.out.println("进来l"+result+"        "+DataMap);
			return "updatesuccess";
		}
		return "fail";
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	@Override
	public GongGao getModel() {
		return gonggao = new GongGao();
	}


}
