package com.yc.hdmedia.web.action;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.opensymphony.xwork2.ModelDriven;
import com.yc.hdmedia.entity.GongGaoBean;
import com.yc.hdmedia.entity.TieZiBean;
import com.yc.hdmedia.service.GongGaoService;
import com.yc.hdmedia.service.TieZiService;

@Controller("tieZisAction")
public class TieZisAction implements ModelDriven<TieZiBean>, SessionAware{
	@Autowired
	private TieZiService tieZiService;
	@Autowired
	private GongGaoService gongGaoService;
	private Map<String, Object> session;
	private TieZiBean tieZiBean;
	private File[] upload;//上传的文件
	private String[] uploadFileName;//文件名
	private String[] uploadContentType;//文件类型

	public File[] getUpload() {
		return upload;
	}


	public void setUpload(File[] upload) {
		this.upload = upload;
	}


	public String[] getUploadFileName() {
		return uploadFileName;
	}


	public void setUploadFileName(String[] uploadFileName) {
		this.uploadFileName = uploadFileName;
	}


	public String[] getUploadContentType() {
		return uploadContentType;
	}


	public void setUploadContentType(String[] uploadContentType) {
		this.uploadContentType = uploadContentType;
	}


	public String findbyLtids(){
		String ltid=ServletActionContext.getRequest().getParameter("ltid"); 
		int id=Integer.parseInt(ltid);
		session.put("ltid", id);
		TieZiBean count=tieZiService.findByIdCount(id);
		session.put("countbyids", count);
		String html;//要写入的HTML
		List<String> list=new ArrayList<String>();
		int pageSize=1;//页数
		int pageNum=count.getCount();//总条数
		if(pageNum==0){//条数为0
			pageSize=0;
		}else if(pageNum<=12){//条数小于12
			pageSize=1;
			html="<input name='pageNo' value="+pageSize+" type='submit'/>";
			list.add(html);
		}else if(pageNum%12>0){ //条数超过12条
			pageSize=pageNum/12+1;
			for(int i=1;i<=pageSize;i++){
				html="<input name='pageNo' value="+i+" type='submit'/>";
				list.add(html);
			}
		}
		session.put("list", list);
		session.put("pageSize", pageSize);
		int pageNo=1;//当前页数
		if(tieZiBean.getPageNo()!=0){
			pageNo=tieZiBean.getPageNo();
			if(pageNo>pageSize){
				pageNo=pageSize;
			}
			if(pageNo==0){
				pageNo=1;
			}
		}
		session.put("pageNo", pageNo);
		List<TieZiBean> TieZiBean=tieZiService.findById(pageNo, 12, id);
		session.put("TieZiBean", TieZiBean);
		List<GongGaoBean> GongGaoBean=gongGaoService.findGongGao();
		session.put("GongGaoBean", GongGaoBean);
		return "tieziSuccess";

	}
	
	public String add(){
		String path=null;
		for(int i=0;i<upload.length;i++){
			//Ҫ�þ�Ե�ַ
			path=ServletActionContext.getServletContext().getRealPath("upload/"+uploadFileName[i]);
			
			try {
				FileUtils.copyFile(upload[i], new File(path));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		String s;
		StringBuffer files = new StringBuffer();
		files.delete(0,files.length());
		for(int i=0;i<uploadFileName.length;i++){
			files.append(uploadFileName[i]+",");
		}
		s=String.valueOf(files);
		System.out.println(s);
		String id=ServletActionContext.getRequest().getParameter("ltid"); 
		int ltid=Integer.parseInt(id);
		session.put("ltid", ltid);
		String tzname=ServletActionContext.getRequest().getParameter("tzname"); 
		String tzzy=ServletActionContext.getRequest().getParameter("tzzy"); 
		String tztext=ServletActionContext.getRequest().getParameter("tztext"); 
		int result=tieZiService.addTieZis(ltid, 1001, tzname, tzzy, tztext,s);
		session.put("addtieziltid", ltid);
		if(result==1){
			return "tieziAddSuccess";
		}
		return "fail";
	}

	
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}
	@Override
	public TieZiBean getModel() {
		tieZiBean=new TieZiBean();
		return tieZiBean;
	}
	
}
