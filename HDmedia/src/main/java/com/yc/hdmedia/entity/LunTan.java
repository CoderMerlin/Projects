package com.yc.hdmedia.entity;

import java.io.Serializable;

/**
 * 论坛实体类 下次的说法
 * @author Administrator
 *
 */
public class LunTan implements Serializable{
	private static final long serialVersionUID = 629594397013489863L;
	
	private int ltid;  //论坛id
	private String ltname; //论坛名称
	private String lttime; //论坛创建时间
	private int ltstatus; //状态
	private String ltyl1; //论坛类型
	private String ltyl2; //论坛图片
	
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
	
	public String toString() {
		return "LunTan [ltid=" + ltid + ", ltname=" + ltname + ", lttime="
				+ lttime + ", ltstatus=" + ltstatus + ", ltyl1=" + ltyl1
				+ ", ltyl2=" + ltyl2 + "]";
	}
	public LunTan(int ltid, String ltname, String lttime, int ltstatus,
			String ltyl1, String ltyl2) {
		super();
		this.ltid = ltid;
		this.ltname = ltname;
		this.lttime = lttime;
		this.ltstatus = ltstatus;
		this.ltyl1 = ltyl1;
		this.ltyl2 = ltyl2;
	}
	public LunTan() {
		super();
	}
	
	
}
