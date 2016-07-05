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
import com.yc.hdmedia.entity.YongHu;
import com.yc.hdmedia.service.YongHuService;

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
			/*for (YongHu yongHu : yongHus) {
				String yhindentity=yongHu.getYhindentity();
				String yhindentitys=yhindentity.substring(0,3)+"*****"+yhindentity.substring(11,yhindentity.length());
				yongHu.setYhindentity(yhindentitys);
			}*/
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
		yongHu.setYhphoto(fileName+picture);
		int result=yongHuService.addYungHuInfo(yongHu);
		if(result>0){
			return "addSuccess";
		}
		return "fail";
	}
	
	public String updateYongHuInfo(){
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
		yongHu.setYhphoto(fileName+picture);
		int result=yongHuService.updateYungHuInfo(yongHu);
		if(result>0){
			return "updatesuccess";
		}
		return "fail";
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
