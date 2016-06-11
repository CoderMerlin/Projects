package com.yc.hdmedia.entity;


public class Works {  //作品实体类
	private int works_id;
	private int author_id;
	private String works_name;
	private String works_time;
	private String works_img;
	private int works_click;
	private int works_weight;
	private String works_details;
	private String wtid;
	private String wtname;
	private int status;
	private String author_name;
	private int rownum;
	public int getWorks_id() {
		return works_id;
	}
	public void setWorks_id(int works_id) {
		this.works_id = works_id;
	}
	public int getAuthor_id() {
		return author_id;
	}
	public void setAuthor_id(int author_id) {
		this.author_id = author_id;
	}
	public String getWorks_name() {
		return works_name;
	}
	public void setWorks_name(String works_name) {
		this.works_name = works_name;
	}
	public String getWorks_time() {
		return works_time;
	}
	public void setWorks_time(String works_time) {
		this.works_time = works_time;
	}
	public String getWorks_img() {
		return works_img;
	}
	public void setWorks_img(String works_img) {
		this.works_img = works_img;
	}
	public int getWorks_click() {
		return works_click;
	}
	public void setWorks_click(int works_click) {
		this.works_click = works_click;
	}
	public int getWorks_weight() {
		return works_weight;
	}
	public void setWorks_weight(int works_weight) {
		this.works_weight = works_weight;
	}
	public String getWorks_details() {
		return works_details;
	}
	public void setWorks_details(String works_details) {
		this.works_details = works_details;
	}
	public String getWtid() {
		return wtid;
	}
	public void setWtid(String wtid) {
		this.wtid = wtid;
	}
	public String getWtname() {
		return wtname;
	}
	public void setWtname(String wtname) {
		this.wtname = wtname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getAuthor_name() {
		return author_name;
	}
	public void setAuthor_name(String author_name) {
		this.author_name = author_name;
	}
	public int getRownum() {
		return rownum;
	}
	public void setRownum(int rownum) {
		this.rownum = rownum;
	}
	@Override
	public String toString() {
		return "Works [works_id=" + works_id + ", author_id=" + author_id
				+ ", works_name=" + works_name + ", works_time=" + works_time
				+ ", works_img=" + works_img + ", works_click=" + works_click
				+ ", works_weight=" + works_weight + ", works_details="
				+ works_details + ", wtid=" + wtid + ", wtname=" + wtname
				+ ", status=" + status + ", author_name=" + author_name
				+ ", rownum=" + rownum + "]";
	}
	public Works(int works_id, int author_id, String works_name,
			String works_time, String works_img, int works_click,
			int works_weight, String works_details, String wtid, String wtname,
			int status, String author_name, int rownum) {
		super();
		this.works_id = works_id;
		this.author_id = author_id;
		this.works_name = works_name;
		this.works_time = works_time;
		this.works_img = works_img;
		this.works_click = works_click;
		this.works_weight = works_weight;
		this.works_details = works_details;
		this.wtid = wtid;
		this.wtname = wtname;
		this.status = status;
		this.author_name = author_name;
		this.rownum = rownum;
	}
	public Works() {
		super();
	}
	
	
	
}
