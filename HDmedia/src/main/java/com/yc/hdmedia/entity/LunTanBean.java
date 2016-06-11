package com.yc.hdmedia.entity;

import java.io.Serializable;

public class LunTanBean implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2268806209029297760L;
	private int ltid;  //论坛id
	private String ltname; //论坛名称
	private String lttime; //论坛创建时间
	private int ltstatus; //状态
	private String ltyl1; //类型
	private String ltyl2; //图片
	private String rownum;
	private int count; //帖子数
	private int dtcount; //当天帖子数
	private String tztime; //最近的时间
	public int getLtid() {
		return ltid;
	}
	public void setLtid(int ltid) {
		this.ltid = ltid;
	}
	public String getLtname() {
		return ltname;
	}
	public void setLtname(String ltname) {
		this.ltname = ltname;
	}
	public String getLttime() {
		return lttime;
	}
	public void setLttime(String lttime) {
		this.lttime = lttime;
	}
	public int getLtstatus() {
		return ltstatus;
	}
	public void setLtstatus(int ltstatus) {
		this.ltstatus = ltstatus;
	}
	public String getLtyl1() {
		return ltyl1;
	}
	public void setLtyl1(String ltyl1) {
		this.ltyl1 = ltyl1;
	}
	public String getLtyl2() {
		return ltyl2;
	}
	public void setLtyl2(String ltyl2) {
		this.ltyl2 = ltyl2;
	}
	
	
	public String getRownum() {
		return rownum;
	}
	public void setRownum(String rownum) {
		this.rownum = rownum;
	}
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	
	
	public int getDtcount() {
		return dtcount;
	}
	public void setDtcount(int dtcount) {
		this.dtcount = dtcount;
	}
	public String getTztime() {
		return tztime;
	}
	public void setTztime(String tztime) {
		this.tztime = tztime;
	}
	@Override
	public String toString() {
		return "\nLunTanBean [ltid=" + ltid + ", ltname=" + ltname + ", lttime="
				+ lttime + ", ltstatus=" + ltstatus + ", ltyl1=" + ltyl1
				+ ", ltyl2=" + ltyl2 + ", rownum=" + rownum + ", count="
				+ count + ", dtcount=" + dtcount + ", tztime=" + tztime + "]";
	}


	
	
	
	
	
	
}
