package com.yc.hdmedia.entity;

public class WorksType {
	private int wtid;
	private String wtname;
	public int getWtid() {
		return wtid;
	}
	public void setWtid(int wtid) {
		this.wtid = wtid;
	}
	public String getWtname() {
		return wtname;
	}
	public void setWtname(String wtname) {
		this.wtname = wtname;
	}
	@Override
	public String toString() {
		return "WorksType [wtid=" + wtid + ", wtname=" + wtname + "]";
	}
	
	
}
