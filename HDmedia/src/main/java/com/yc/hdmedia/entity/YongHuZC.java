package com.yc.hdmedia.entity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import com.yc.hdmedia.utils.MD5Encryption;

public class YongHuZC {
	private int yhzcid;
	private String yhname;
	private String yhemail;
	private String yhpwd;
	private String yhzctime;
	private int yhzcstatus;
	private String yhzcyl1;
	private String yhzcyl2;
	
	public YongHuZC() {
		Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		yhzctime= sdf.format(currDate);
	}
	public int getYhzcid() {
		return yhzcid;
	}
	public int getYhzcids() {
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
	public String getYhpwd() {
		return yhpwd;
	}
	//给用户密码加密
	public void setYhpwd(String yhpwd) {
		yhpwd=MD5Encryption.createPassword(yhpwd);
		this.yhpwd = yhpwd;
	}
	public String getYhzctime() {
		String [] arr=yhzctime.split(" ");
		yhzctime=arr[0];
		return yhzctime;
	}
	public void setYhzctime(String yhzctime) {
		this.yhzctime=yhzctime;
	}
	public int getYhzcstatus() {
		return yhzcstatus;
	}
	public void setYhzcstatus(int yhzcstatus) {
		this.yhzcstatus = yhzcstatus;
	}
	public String getYhzcyl1() {
		return yhzcyl1;
	}
	public void setYhzcyl1(String yhzcyl1) {
		this.yhzcyl1 = yhzcyl1;
	}
	public String getYhzcyl2() {
		return yhzcyl2;
	}
	public void setYhzcyl2(String yhzcyl2) {
		this.yhzcyl2 = yhzcyl2;
	}
	@Override
	public String toString() {
		return "YongHuZC [yhzcid=" + yhzcid + ", yhname=" + yhname + ", yhemail=" + yhemail + ", yhpwd=" + yhpwd + ", yhzctime=" + yhzctime + ", yhzcstatus=" + yhzcstatus + ", yhzcyl1=" + yhzcyl1
				+ ", yhzcyl2=" + yhzcyl2 + "]";
	}
}
