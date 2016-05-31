package com.yc.hdmedia.entity;

public class YHBean {
	private int yhzcid;
	private String yhname;
	private String yhemail;
	private String yhphone;
	private String yhzctime;
	private int page;
	private int rows;
	
	
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public int getYhzcid() {
		return yhzcid;
	}
	public void setYhzcid(int yhzcid) {
		this.yhzcid = yhzcid;
	}
	
	public String getYhname() {
		return yhname;
	}
	public void setYhname(String yhname) {
		this.yhname = yhname;
	}
	public String getYhemail() {
		return yhemail;
	}
	public void setYhemail(String yhemail) {
		this.yhemail = yhemail;
	}
	public String getYhphone() {
		return yhphone;
	}
	public void setYhphone(String yhphone) {
		this.yhphone = yhphone;
	}
	public String getYhzctime() {
		return yhzctime;
	}
	public void setYhzctime(String yhzctime) {
		this.yhzctime = yhzctime;
	}
	@Override
	public String toString() {
		return "YHBean [yhzcid=" + yhzcid + ", yhname=" + yhname + ", yhemail=" + yhemail + ", yhphone=" + yhphone + ", yhzctime=" + yhzctime + ", page=" + page + ", rows=" + rows + "]";
	}
}
