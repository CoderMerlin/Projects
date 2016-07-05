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
import com.yc.hdmedia.entity.Author;
import com.yc.hdmedia.service.AuthorService;

@Controller("authorAction")
public class AuthorAction implements ModelDriven<Author>{
	@Autowired
	private AuthorService authorService;
	private Author	author=new Author();
	private Map<String,Object> DataMap=new HashMap<String,Object>();
	public Map<String, Object> getDataMap() {
		return DataMap;
	}
	public void setDataMap(Map<String, Object> dataMap) {
		DataMap = dataMap;
	}
	private int page;
	private int rows;
	private String author_ids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
	
	
	public String getPageAuthorInfo(){
		DataMap.clear();
		List<Author> dtp=authorService.findAllAuthor(page, rows);
		if(dtp!=null){
			DataMap.put("rows", dtp);
			DataMap.put("total",authorService.total());
			return "success";
		}
		return "fail";
	}
	
	public String addAuthorInfo(){
		DataMap.clear();
		String path=ServletActionContext.getServletContext().getRealPath("../upload");
		String fileName = String.valueOf(System.currentTimeMillis()+new Random().nextInt(10000));
		System.out.println("图片"+upload);
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
		author.setAuthor_photo(fileName+picture);
		int total=authorService.addAuthor(author);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	public String findAuthorById(){
		DataMap.clear();
		List<Author> dtp=authorService.findById(author.getAuthor_id());
		if(dtp!=null){
			DataMap.put("rows", dtp);
			return "success";
		}
		return "fail";
	}
	
	/**
	 * 肯定有问题
	 * @return
	 */
	public String delAuthorInfo(){
		int total=authorService.delAuthor(author_ids);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	public String updateAuthorInfo(){
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
		System.out.println(path+"       "+fileName+picture);
		
		System.out.println("修改的作者信息"+author);
		author.setAuthor_photo(fileName+picture);
		int total=authorService.updateAuthor(author);
		if(total>0){
			DataMap.put("total", total);
			return "success";
		}
		return "fail";
	}
	
	public void setPage(int page) {
		this.page = page;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public void setAuthor_ids(String author_ids) {
		this.author_ids = author_ids;
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

	@Override
	public Author getModel() {
		return author;
	}

}
