package com.yc.hdmedia.entity;

public class YongHuBean {
	private int yhzcid;
	private int yhid;
	private String yhname;
	private String yhemail;
	private String yhzctime;
	private String yhphone;
	
	public int getYhzcid() {
		return yhzcid;
	}
	public void setYhzcid(int yhzcid) {
		this.yhzcid = yhzcid;
	}
	public int getYhid() {
		return yhid;
	}
	public void setYhid(int yhid) {
		this.yhid = yhid;
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
	public String getYhzctime() {
		return yhzctime;
	}
	public void setYhzctime(String yhzctime) {
		this.yhzctime = yhzctime;
	}
	public String getYhphone() {
		return yhphone;
	}
	public void setYhphone(String yhphone) {
		this.yhphone = yhphone;
	}
	
	public String toString() {
		return "YongHuBean [yhzcid=" + yhzcid + ", yhid=" + yhid + ", yhname=" + yhname + ", yhemail=" + yhemail + ", yhzctime=" + yhzctime + ", yhphone=" + yhphone + "]";
	}
}
