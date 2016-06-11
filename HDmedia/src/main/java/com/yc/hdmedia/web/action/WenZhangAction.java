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
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.Wenzhang;
import com.yc.hdmedia.service.IWenzhangService;

@Controller("wenZhangAction")
public class WenZhangAction implements ModelDriven<Wenzhang>{
	@Autowired
	private IWenzhangService iWenzhangService;
	private Wenzhang wenzhang=new Wenzhang();
	private JsonObject<Wenzhang> jsonObject;
	private int page;
	private int rows;
	private String wzids;
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}
	public JsonObject<Wenzhang> getJsonObject() {
		return jsonObject;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setWzids(String wzids) {
		this.wzids = wzids;
	}
	public String getPageWenzhangInfo(){
		List<Wenzhang> wz=iWenzhangService.find(page, rows);
		int total=iWenzhangService.total();
		jsonObject=new JsonObject<Wenzhang>();
		jsonObject.setTotal(total);
		jsonObject.setRows(wz);
		return "success";
	}
	
	public String addWenzhangInfo(){
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
		wenzhang.setWzpicture(uploadFileName[0]);
		int total=iWenzhangService.addWenzhang(wenzhang);
		jsonObject=new JsonObject<Wenzhang>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String findWenZhangById(){
		List<Wenzhang> wz=iWenzhangService.findById(wenzhang.getWzid());
		jsonObject=new JsonObject<Wenzhang>();
		jsonObject.setRows(wz);
		return "success";
	}
	
	public String delWenzhangInfo(){
		System.out.println("删除文章======>"+wzids);
		int total=iWenzhangService.del(wzids);
		jsonObject=new JsonObject<Wenzhang>();
		jsonObject.setTotal(total);
		return "success";
	}

	public String updateWenzhangInfo(){
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
		wenzhang.setWzpicture(uploadFileName[0]);
		int total=iWenzhangService.updateWenzhang(wenzhang);
		jsonObject=new JsonObject<Wenzhang>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String getAllWenzhangTitle(){
		List<Wenzhang> wz=iWenzhangService.finds();
		jsonObject=new JsonObject<Wenzhang>();
		jsonObject.setRows(wz);
		return "success";
	}
	
	@Override
	public Wenzhang getModel() {
		return wenzhang;
	}

}
