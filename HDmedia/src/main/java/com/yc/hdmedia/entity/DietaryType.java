package com.yc.hdmedia.entity;

public class DietaryType {
	private int dtpid;
	private String dtpname;
	public int getDtpid() {
		return dtpid;
	}
	public void setDtpid(int dtpid) {
		this.dtpid = dtpid;
	}
	public String getDtpname() {
		return dtpname;
	}
	public void setDtpname(String dtpname) {
		this.dtpname = dtpname;
	}
	@Override
	public String toString() {
		return "DietaryType [dtpid=" + dtpid + ", dtpname=" + dtpname + "]";
	}
	
}
