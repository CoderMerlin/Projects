package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.TieZi;
import com.yc.hdmedia.service.TieZiService;

/**
 * 突然
 * @author Administrator
 *
 */
@Controller("tieZiAction")
public class TieZiAction implements ModelDriven<TieZi>{

	@Autowired
	private TieZiService tiZiService;
	private JsonObject<TieZi> jsonObject;
	private int page;
	private int rows;
	private String tids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
	private TieZi tieZi=new TieZi();
	
	public String getPageTieZiInfo(){
		List<TieZi> tiezis=tiZiService.find(page, rows);
		int total=tiZiService.total();
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setRows(tiezis);
		jsonObject.setTotal(total);
		return "success";
	}

	public String addTieZiInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
		} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		tieZi.setTzphoto(uploadFileName[0]);
		int result = tiZiService.addTieZi(tieZi);
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setTotal(result);
		return "success";

	}

	public String delTieZiInfo(){
		int result=tiZiService.delTieZi(tids);
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setTotal(result);
		return "success";
	}

	public String findTieZiByTid(){
		System.out.println("查找帖子===========>"+tieZi.getTid());
		List<TieZi> tz=tiZiService.find(tieZi.getTid());
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setRows(tz);
		return "success";
	}


	public String updateTieZiInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
		} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		tieZi.setTzphoto(uploadFileName[0]);
		System.out.println("添加帖子=====>"+tieZi);
		int result = tiZiService.addTieZi(tieZi);
		jsonObject=new JsonObject<TieZi>();
		jsonObject.setTotal(result);
		return "success";

	}
	
	

	@Override
	public TieZi getModel() {
		return tieZi;
	}

	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File[] upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public JsonObject<TieZi> getJsonObject() {
		return jsonObject;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setTids(String tids) {
		this.tids = tids;
	}


}
