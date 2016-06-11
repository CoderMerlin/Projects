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
import com.yc.hdmedia.entity.Author;
import com.yc.hdmedia.entity.JsonObject;
import com.yc.hdmedia.service.AuthorService;

@Controller("authorAction")
public class AuthorAction implements ModelDriven<Author>{
	@Autowired
	private AuthorService authorService;
	private Author author=new Author();
	private JsonObject< Author> jsonObject;
	private int page;
	private int rows;
	private String author_ids;
	//文件上传
	private File[] upload;		//上传文件
	private String[] uploadFileName;	//上传的文件名
	private String[] uploadContentType;		//上传的文件类型
	
	
	public String getPageAuthorInfo(){
		List<Author> dtp=authorService.findAllAuthor(page, rows);
		int total=authorService.total();
		jsonObject=new JsonObject<Author>();
		jsonObject.setRows(dtp);
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String addAuthorInfo(){
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		String photo="";
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
				if(i<upload.length-1){
					photo+=uploadFileName[i]+",";
				}
		} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		photo+=uploadFileName[upload.length-1];
		author.setAuthor_photo(photo);
		int total=authorService.addAuthor(author);
		jsonObject=new JsonObject<Author>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public String findAuthorById(){
		List<Author> dtp=authorService.findById(author.getAuthor_id());
		jsonObject=new JsonObject<Author>();
		jsonObject.setRows(dtp);
		return "success";
	}
	
	public String delAuthorInfo(){
		int total=authorService.delAuthor(author_ids);
		jsonObject=new JsonObject<Author>();
		jsonObject.setTotal(total);;
		return "success";
	}
	
	public String updateAuthorInfo(){
		LogManager.getLogger().debug("修改信息======>"+author);
		String path=ServletActionContext.getServletContext().getRealPath("upload/");
		String photo="";
		for(int i=0;i<upload.length;i++){
			try {
				FileUtils.copyFile(upload[i], new File(path+"/"+uploadFileName[i]));	//开始上传
				System.out.println("上传成功!!!!");
				if(i<upload.length-1){
					photo+=uploadFileName[i]+",";
					System.out.println(photo);
				}
		} catch (IOException e) {
				System.out.println("图片上传失败!!!!!!!!!!");
				e.printStackTrace();
			}
		}
		photo+=uploadFileName[upload.length-1];
		author.setAuthor_photo(photo);
		LogManager.getLogger().debug("修改信息======>"+author);
		int total=authorService.updateAuthor(author);
		jsonObject=new JsonObject<Author>();
		jsonObject.setTotal(total);
		return "success";
	}
	
	public JsonObject<Author> getJsonObject() {
		return jsonObject;
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
