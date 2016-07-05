package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.Wenzhang;
import com.yc.hdmedia.service.IWenzhangService;

@Controller("wenZhangAction")
public class WenZhangAction implements ModelDriven<Wenzhang>{
	@Autowired
	private IWenzhangService iWenzhangService;
	private Wenzhang wenzhang=new Wenzhang();
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
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
		DataMap.clear();
		List<Wenzhang> wz=iWenzhangService.find(page, rows);
		if(wz!=null){
			DataMap.put("rows", wz);
			DataMap.put("total", iWenzhangService.total());
			return "success";
		}
		return "fail";
	}
	
	public String addWenzhangInfo(){
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
		wenzhang.setWzpicture(fileName+picture);
		int total=iWenzhangService.addWenzhang(wenzhang);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	public String findWenZhangById(){
		DataMap.clear();
		List<Wenzhang> wz=iWenzhangService.findById(wenzhang.getWzid());
		if(wz!=null){
			DataMap.put("rows", wz);
			return "success";
		}
		return "fail";
	}
	
	public String delWenzhangInfo(){
		DataMap.clear();
		int total=iWenzhangService.del(wzids);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}

	public String updateWenzhangInfo(){
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
		System.out.println("修改的文章信息");
		wenzhang.setWzpicture(fileName+picture);
		int total=iWenzhangService.updateWenzhang(wenzhang);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	public String getAllWenzhangTitle(){
		DataMap.clear();
		List<Wenzhang> wz=iWenzhangService.finds();
		if(wz!=null){
			DataMap.put("rows", wz);
			return "success";
		}
		return "fail";
	}
	
	@Override
	public Wenzhang getModel() {
		return wenzhang;
	}

}
