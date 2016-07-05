package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.Works;
import com.yc.hdmedia.service.WorksService;

@Controller("worksAction")
public class WorksAction implements ModelDriven<Works>,SessionAware{
	private Works works=new Works();
	@Autowired
	private WorksService worksService;
	private Map<String,Object> session;
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}

	private int page;
	private int rows;
	private String works_ids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
		
		
	public String getPageWorksInfo(){
		DataMap.clear();
		List<Works> dtp=worksService.findAllWorks(page, rows);
		if(dtp!=null){
			DataMap.put("rows", dtp);
			DataMap.put("total", worksService.total());
			return "success";
		}
		return "fail";
	}
		
	public String getAllWorks(){
		Works xw=worksService.findXWork();
		Works cw=worksService.findCWork();
		Works gw=worksService.findGWork();
		Works gjw=worksService.findGJWork();
		String xwtext=xw.getWorks_details();
		xwtext=xwtext.substring(0,50);
		xw.setWorks_details(xwtext);
		
		String cwtext=cw.getWorks_details();
		cwtext=cwtext.substring(0,50);
		cw.setWorks_details(cwtext);
		
		
		String gwtext=gw.getWorks_details();
		gwtext=gwtext.substring(0,50);
		gw.setWorks_details(gwtext);
		
		String gjwtext=gjw.getWorks_details();
		gjwtext=gjwtext.substring(0,50);
		gjw.setWorks_details(gjwtext);
		
		session.put("xwork", xw);
		session.put("cwork", cw);
		session.put("gwork", gw);
		session.put("gjwork", gjw);
		
		List<Works> Xworks=worksService.findAllXWorks();
		List<Works> Cworks=worksService.findAllCWorks();
		List<Works> Gworks=worksService.findAllGWorks();
		List<Works> GJworks=worksService.findAllGJWorks();
		
		
		session.put("Xworks", Xworks);
		session.put("Cworks", Cworks);
		session.put("Gworks", Gworks);
		session.put("GJworks", GJworks);
		return "getsuccess";
	}
	
	public String getworkDetailInfo(){
		int  works_id=Integer.parseInt(ServletActionContext.getRequest().getParameter("works_id"));
		Works w=worksService.getWorkInfoById(works_id);
		session.put("work", w);
		return "detail";
	}
	
	public String addWorksInfo(){
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
		works.setWorks_img(fileName+picture);
		int total=worksService.addWorks(works);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	
	public String updateWorksInfo(){
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
		works.setWorks_img(fileName+picture);
		int total=worksService.updateWorks(works);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	public String delWorksInfo(){
		DataMap.clear();
		int total=worksService.delWorks(works_ids);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	public String findWorksInfoById(){
		DataMap.clear();
		List<Works> dtp=worksService.findById(works.getWorks_id());
		if(dtp!=null){
			System.out.println("数据"+dtp);
			DataMap.put("rows", dtp);
			return "success";
		}
		return "fail";
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
	
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
