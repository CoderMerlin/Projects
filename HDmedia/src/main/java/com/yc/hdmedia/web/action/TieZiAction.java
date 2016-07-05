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
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}

	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private int page;
	private int rows;
	private String tids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
	private TieZi tieZi=new TieZi();
	
	
	/**
	 * 获取帖子页面信息
	 * @author HM修改
	 * @return
	 */
	public String getPageTieZiInfo(){
		List<TieZi> tiezis=tiZiService.find(page, rows);
		int total=tiZiService.total();
		if(tiezis!=null){
			DataMap.put("total", total);
			DataMap.put("rows", tiezis);
			return "success";
		}
		return "fail";
	}

	
	/**
	 * 添加帖子信息
	 * @author HM修改
	 * @return
	 */
	public String addTieZiInfo(){
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
		tieZi.setTzphoto(fileName+picture);
		int result = tiZiService.addTieZi(tieZi);
		if(result>0){
			DataMap.put("rows", result);
			return "success";
		}
		return "fail";
	}

	/**
	 * 删除帖子
	 * @author HM
下午9:36:12修改 
	 * @return
	 */
	public String delTieZiInfo(){
		DataMap.clear();
		int result=tiZiService.delTieZi(tids);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}

	
	/**
	 * 通过帖子id查看帖子
	 * @author HM
下午9:36:48修改
	 * @return
	 */
	public String findTieZiByTid(){
		DataMap.clear();
		List<TieZi> tz=tiZiService.find(tieZi.getTid());
		if(tz!=null){
			DataMap.put("rows", tz);
			return "success";
		}
		return "fail";
	}


	/**
	 * 更新帖子信息
	 * @author HM
下午9:38:15修改
	 * @return
	 */
	public String updateTieZiInfo(){
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
		tieZi.setTzphoto(fileName+picture);
		int result = tiZiService.addTieZi(tieZi);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";

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
