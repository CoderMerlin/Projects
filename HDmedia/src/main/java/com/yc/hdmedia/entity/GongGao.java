package com.yc.hdmedia.entity;

public class GongGao {
	private int gid;  //公告id
	private String gtitle;//公告标题
	private String gtext;//公告内容
	private String gtime;//公告创建时间
	private String gstatus;//状态       -- 0 表示删除   1表示存在
	private String ggyl1;//预留字段1
	private String ggyl2;//预留字段2
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getGtext() {
		return gtext;
	}
	public void setGtext(String gtext) {
		this.gtext = gtext;
	}
	public String getGtime() {
		return gtime;
	}
	public void setGtime(String gtime) {
		this.gtime = gtime;
	}
	public String getGstatus() {
		return gstatus;
	}
	public void setGstatus(String gstatus) {
		this.gstatus = gstatus;
	}
	public String getGgyl1() {
		return ggyl1;
	}
	public void setGgyl1(String ggyl1) {
		this.ggyl1 = ggyl1;
	}
	public String getGgyl2() {
		return ggyl2;
	}
	public void setGgyl2(String ggyl2) {
		this.ggyl2 = ggyl2;
	}
	@Override
	public String toString() {
		return "GongGao [gid=" + gid + ", gtitle=" + gtitle + ", gtext="
				+ gtext + ", gtime=" + gtime + ", gstatus=" + gstatus
				+ ", ggyl1=" + ggyl1 + ", ggyl2=" + ggyl2 + "]";
	}
	
	
}
