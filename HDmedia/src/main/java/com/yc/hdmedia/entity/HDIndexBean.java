package com.yc.hdmedia.entity;

/**
 * 首页实体类bean
 * @author HM
 *
 */
public class HDIndexBean {
	private int wzid;  //文章id;
	private String wzyl1;  //文章作者
	private String wztitle;  //文章标题
	private String wztext;  //文章内容
	private String wzpicture;  //文章图片
	
	//图片类型表中
	private String pspics;  //主要的图片
	private String pstitile;   //图片标题
	
	
	public int getWzid() {
		return wzid;
	}
	public void setWzid(int wzid) {
		this.wzid = wzid;
	}
	public String getWzyl1() {
		return wzyl1;
	}
	public void setWzyl1(String wzyl1) {
		this.wzyl1 = wzyl1;
	}
	public String getWztitle() {
		return wztitle;
	}
	public void setWztitle(String wztitle) {
		this.wztitle = wztitle;
	}
	public String getWztext() {
		return wztext;
	}
	public void setWztext(String wztext) {
		this.wztext = wztext;
	}
	public String getWzpicture() {
		return wzpicture;
	}
	public void setWzpicture(String wzpicture) {
		this.wzpicture = wzpicture;
	}

	public HDIndexBean() {
		
		
	}
	@Override
	public String toString() {
		return "HDIndexBean [wzid=" + wzid + ", wzyl1=" + wzyl1 + ", wztitle="
				+ wztitle + ", wztext=" + wztext + ", wzpicture=" + wzpicture
				+ ", pspics=" + pspics + ", pstitile=" + pstitile + "]";
	}
	
	
	
	
}
