package com.yc.hdmedia.web.action;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.entity.LunTan;
import com.yc.hdmedia.entity.LunTanBean;
import com.yc.hdmedia.entity.TieZiBean;
import com.yc.hdmedia.service.ILunTanService;
import com.yc.hdmedia.service.LunTanService;
import com.yc.hdmedia.service.TieZiService;

/**
 * @author Administrator
 *
 */
@Controller("lunTanAction")
public class LunTanAction implements ModelDriven<LunTan>,SessionAware {
	@Autowired
	private ILunTanService iLunTanService;
	@Autowired
	private LunTanService lunTanService;
	@Autowired
	private TieZiService tieZiService;
	private Map<String, Object> session;
	private LunTan lunTan=new LunTan();
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private int page;
	private int rows;
	private String ltids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;		//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
	
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

	public void setLtids(String ltids) {
		this.ltids = ltids;
	}
	public void setUpload(File[] upload) {
		this.upload = upload;
	}
	
	
	
	
	public String find(){
		List<LunTanBean> luntanjlq=lunTanService.findLunTan_jlq();
		List<LunTanBean> luntanwhjlq=lunTanService.findLunTan_whjlq();
		List<LunTanBean> luntanzyfx=lunTanService.findLunTan_zyfx();
		List<LunTanBean> luntanzzhz=lunTanService.findLunTan_zzhz();
		List<LunTanBean> luntanz=lunTanService.findLunTan();
		TieZiBean count=tieZiService.findCount();
		TieZiBean TerdayCount=tieZiService.findTerdayCount();
		TieZiBean YesterdayCount=tieZiService.findYesterdayCount();
		session.put("luntanjlq", luntanjlq);
		session.put("luntanwhjlq", luntanwhjlq);
		session.put("luntanzyfx", luntanzyfx);
		session.put("luntanzzhz", luntanzzhz);
		session.put("luntan", luntanz);
		session.put("count", count);
		session.put("TerdayCount", TerdayCount);
		session.put("YesterdayCount", YesterdayCount);
		return "lunTanSuccess";
	}

	/**
	 * 后台显示所有的帖子信息
	 * @return
	 * @author HM修改
	 */
	public String showAll(){
		DataMap.clear();
		List<LunTan> luntans=iLunTanService.find(page, rows);
		int total=iLunTanService.total();
		if(luntans!=null){
			DataMap.put("total",total);
			DataMap.put("rows", luntans);
			return "success";
		}
		return "fail";
	}
	
	public String addLunTanInfo(){
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
		lunTan.setLtyl2(fileName+picture);
		int result = iLunTanService.addLunTan(lunTan);
		if(result>0){
			DataMap.put("total",result);
			return "success";
		}
		return "fail";
	}
	
	public String findLunTanById(){
		DataMap.clear();
		List<LunTan> ll = iLunTanService.findById(String.valueOf(lunTan.getLtid()));
		if(ll!=null){
			DataMap.put("rows", ll);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 修改论坛信息
	 * @author HM修改
	 * @return
	 */
	public String updateLunTanInfo(){
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
		lunTan.setLtyl2(fileName+picture);
		int result = iLunTanService.updateLunTan(lunTan);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	
	
	
	/**
	 * 删除帖子信息
	 * @author HM修改
	 * @return
	 */
	public String delLunTanInfo(){
		DataMap.clear();
		int result = iLunTanService.delLunTan(ltids);
		if(result>0){
			DataMap.put("total", result);
			return "success";
		}
		return "fail";
	}
	
	
	
	

	@Override
	public LunTan getModel() {
		return lunTan;
	}
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	}
