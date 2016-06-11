package com.yc.hdmedia.entity;

public class WenzhangType {
	private int wztypeid; //文章类型ID
	private String wzname; //文章类型名
	private int status; //文章类型状态
	@Override
	public String toString() {
		return "WenzhangType [wztypeid=" + wztypeid + ", wzname=" + wzname
				+ ", status=" + status + "]";
	}
	public int getWztypeid() {
		return wztypeid;
	}
	public void setWztypeid(int wztypeid) {
		this.wztypeid = wztypeid;
	}
	public String getWzname() {
		return wzname;
	}
	public void setWzname(String wzname) {
		this.wzname = wzname;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public WenzhangType(int wztypeid, String wzname, int status) {
		super();
		this.wztypeid = wztypeid;
		this.wzname = wzname;
		this.status = status;
	}
	public WenzhangType() {
		super();
	}
	
	
	
}
