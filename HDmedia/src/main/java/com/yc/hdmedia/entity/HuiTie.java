package com.yc.hdmedia.entity;

import java.io.Serializable;

/**
 * 回帖实体类发给对方
 * @author Administrator
 *
 */
public class HuiTie  implements Serializable{
	private static final long serialVersionUID = 3518558283136350484L;
	private int htid;
	private int yhid;
	private int tid;
	private String httext;
	private String httime;
	private int htdianzan;
	private int htstatus;
	private String yhzsname;
	private String tzname;
	private String yhphoto;
	private int htcount;
	

	public int getHtid() {
		return htid;
	}

	public int getHtids() {
		return htid;
	}

	public void setHtid(int htid) {
		this.htid = htid;
	}


	public int getYhid() {
		return yhid;
	}


	public void setYhid(int yhid) {
		this.yhid = yhid;
	}


	public int getTid() {
		return tid;
	}


	public void setTid(int tid) {
		this.tid = tid;
	}


	public String getHttext() {
		return httext;
	}


	public void setHttext(String httext) {
		this.httext = httext;
	}


	public String getHttime() {
		return httime;
	}


	public void setHttime(String httime) {
		this.httime = httime;
	}

	public int getHtdianzan() {
		return htdianzan;
	}

	public void setHtdianzan(int htdianzan) {
		this.htdianzan = htdianzan;
	}

	public int getHtstatus() {
		return htstatus;
	}


	public void setHtstatus(int htstatus) {
		this.htstatus = htstatus;
	}


	public String getYhzsname() {
		return yhzsname;
	}

	public void setYhzsname(String yhzsname) {
		this.yhzsname = yhzsname;
	}

	public String getTzname() {
		return tzname;
	}

	public void setTzname(String tzname) {
		this.tzname = tzname;
	}

	public String getYhphoto() {
		return yhphoto;
	}

	public void setYhphoto(String yhphoto) {
		this.yhphoto = yhphoto;
	}

	public int getHtcount() {
		return htcount;
	}


	public void setHtcount(int htcount) {
		this.htcount = htcount;
	}

	public String toString() {
		return "HuiTie [htid=" + htid + ", yhid=" + yhid + ", tid=" + tid
				+ ", httext=" + httext + ", httime=" + httime + ", htdianzan="
				+ htdianzan + ", htstatus=" + htstatus + ", yhzsname="
				+ yhzsname + ", tzname=" + tzname + ", yhphoto=" + yhphoto
				+ ", htcount=" + htcount + "]";
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public HuiTie(int htid, int yhid, int tid, String httext, String httime,
			int htdianzan, int htstatus, String yhzsname, String tzname,
			String yhphoto, int htcount) {
		super();
		this.htid = htid;
		this.yhid = yhid;
		this.tid = tid;
		this.httext = httext;
		this.httime = httime;
		this.htdianzan = htdianzan;
		this.htstatus = htstatus;
		this.yhzsname = yhzsname;
		this.tzname = tzname;
		this.yhphoto = yhphoto;
		this.htcount = htcount;
	}

	public HuiTie() {
		super();
	}
	
}
