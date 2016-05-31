package com.yc.hdmedia.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import org.apache.struts2.ServletActionContext;

/**
 * 图片上传
 * @author zxl
 *
 */
public class UploadUtil {
	private List<File> pic; // 要上传的表单元素的name属性值
	private List<String> picContentType;// 上传文件的类型
	private List<String> picFileName;// 上传文件的名称
	private String savePath;// 用来接收以来注入的文件保存路径


	public List<File> getPic() {
		return pic;
	}

	public void setPic(List<File> pic) {
		this.pic = pic;
	}

	public List<String> getPicContentType() {
		return picContentType;
	}

	public void setPicContentType(List<String> picContentType) {
		this.picContentType = picContentType;
	}

	public List<String> getPicFileName() {
		return picFileName;
	}

	public void setPicFileName(List<String> picFileName) {
		this.picFileName = picFileName;
	}

	public String getSavePath() {
		return "uploadPath/";
				/*ServletActionContext.getServletContext().getRealPath(savePath);*/
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	
	@Override
	public String toString() {
		return "UploadUtil [pic=" + pic + ", picContentType=" + picContentType
				+ ", picFileName=" + picFileName + ", savePath=" + savePath
				+ "]";
	}

	/*
	 * 获取存储根路径
	 */
	private String getRootPath() {
		String filePath = Thread.currentThread().getContextClassLoader().getResource("").toString();

		if (filePath.toLowerCase().indexOf("file:") > -1) {
			filePath = filePath.substring(6, filePath.length());
		}

		if (filePath.toLowerCase().indexOf("classes") > -1) {
			filePath = filePath.replaceAll("/classes", "");
		}

		if (filePath.toLowerCase().indexOf("web-inf") > -1) {
			filePath = filePath.substring(0, filePath.length() - 9);
		}

		if (System.getProperty("os.name").toLowerCase().indexOf("window") < 0) {
			filePath = "/" + filePath;
		}

		if (filePath.endsWith("/"))
			filePath = filePath.substring(0, filePath.length() - 1);

		return filePath;
	}

	public String upload() {
		FileOutputStream fos = null;
		FileInputStream fis = null;

		String saveFileName = ""; // 要保存的文件名称

		for (int i = 0; i < pic.size(); i++) {
			try {
				String path = getRootPath().substring(0, getRootPath().lastIndexOf("/"));
				String savePath = path + "/uploadPath"; // 设置文件保存目录
				File saveFile = new File(savePath);
				if (!saveFile.exists()) {
					saveFile.mkdirs();
				}

				String timeStr =""+System.currentTimeMillis(); // 时间戳

				String uploadFileName = timeStr + this.picFileName.get(i);

				saveFileName += "../uploadPath/"+uploadFileName + ",";
				System.out.println(getSavePath()+"/"+pic.get(i));
				fos = new FileOutputStream(getSavePath() + "/" + uploadFileName);
				fis = new FileInputStream(pic.get(i));

				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.flush();
			} catch (FileNotFoundException e) {
				e.printStackTrace();
				return "error";
			} catch (IOException e) {
				e.printStackTrace();
				return "error";
			} finally {
				if (fos != null) {
					try {
						fos.close();
					} catch (IOException e) {
						e.printStackTrace();
						return "error";
					}
				}

				if (fis != null) {
					try {
						fis.close();
					} catch (IOException e) {
						e.printStackTrace();
						return "error";
					}
				}
			}
		}

		// 获取上传的文件名称
		String fileName = saveFileName.substring(0, saveFileName.lastIndexOf(","));
		System.out.println("进入到uploadUtil.upload方法中====>"+fileName);
		return fileName;
	}

}
