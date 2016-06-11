package com.yc.hdmedia.entity;

import java.io.Serializable;

public class HuitieBean implements Serializable{
	private static final long serialVersionUID = 3518558283136350484L;
	private int htid; //回帖ID
	private int yhid; //回帖用户ID
	private int tid;  //帖子ID
	private String tzname; //帖子名
	private String tztext; //帖子内容
	private String tzphoto; //帖子图片
	private String httext;  //回帖内容
	private String httime; //回帖时间
	private int htdianzan; //回帖点赞
	private int htstatus;  //回帖状态
	private String yhzsname; //回帖的用户名	
	private String yhphoto; //用户头像
	private int htcount; 
	private int pageNo;
	public int getHtid() {
		return htid;
	}
	public void setHtid(int htid) {
		this.htid = htid;
	}
	public int getYhid() {
		return yhid;
	}
	public void setYhid(int yhid) {
		this.yhid = yhid;
	}
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public String getHttext() {
		return httext;
	}
	public void setHttext(String httext) {
		this.httext = httext;
	}
	public String getHttime() {
		return httime;
	}
	public void setHttime(String httime) {
		this.httime = httime;
	}
	public int getHtdianzan() {
		return htdianzan;
	}
	public void setHtdianzan(int htdianzan) {
		this.htdianzan = htdianzan;
	}
	public int getHtstatus() {
		return htstatus;
	}
	public void setHtstatus(int htstatus) {
		this.htstatus = htstatus;
	}
	public String getYhzsname() {
		return yhzsname;
	}
	public void setYhzsname(String yhzsname) {
		this.yhzsname = yhzsname;
	}
	public String getTzname() {
		return tzname;
	}
	public void setTzname(String tzname) {
		this.tzname = tzname;
	}
	public String getYhphoto() {
		return yhphoto;
	}
	public void setYhphoto(String yhphoto) {
		this.yhphoto = yhphoto;
	}
	public int getHtcount() {
		return htcount;
	}
	public void setHtcount(int htcount) {
		this.htcount = htcount;
	}
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	@Override
	public String toString() {
		return "\nHuitieBean [htid=" + htid + ", yhid=" + yhid + ", tid=" + tid
				+ ", tzname=" + tzname + ", tztext=" + tztext + ", tzphoto="
				+ tzphoto + ", httext=" + httext + ", httime=" + httime
				+ ", htdianzan=" + htdianzan + ", htstatus=" + htstatus
				+ ", yhzsname=" + yhzsname + ", yhphoto=" + yhphoto
				+ ", htcount=" + htcount + ", pageNo=" + pageNo + "]";
	}
	
}
