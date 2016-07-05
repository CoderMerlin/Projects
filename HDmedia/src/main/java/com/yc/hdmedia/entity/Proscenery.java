package com.yc.hdmedia.entity;

public class Proscenery {
	private int prsid; //风景的id
	private int prid; //省份的id
	private String prsname;//风景的名称
	private String prsimg;//风景图片
	private String prscontent;//风景的介绍
	private int prsstatus;//风景状态 0表示不存在  1表示存在
	public int getPrsid() {
		return prsid;
	}
	public void setPrsid(int prsid) {
		this.prsid = prsid;
	}
	public int getPrid() {
		return prid;
	}
	public void setPrid(int prid) {
		this.prid = prid;
	}
	public String getPrsname() {
		return prsname;
	}
	public void setPrsname(String prsname) {
		this.prsname = prsname;
	}
	public String getPrsimg() {
		return prsimg;
	}
	public void setPrsimg(String prsimg) {
		this.prsimg = prsimg;
	}
	public String getPrscontent() {
		return prscontent;
	}
	public void setPrscontent(String prscontent) {
		this.prscontent = prscontent;
	}
	public int getPrsstatus() {
		return prsstatus;
	}
	public void setPrsstatus(int prsstatus) {
		this.prsstatus = prsstatus;
	}
	@Override
	public String toString() {
		return "proscenery [prsid=" + prsid + ", prid=" + prid + ", prsname="
				+ prsname + ", prsimg=" + prsimg + ", prscontent=" + prscontent
				+ ", prsstatus=" + prsstatus + "]";
	}
	public Proscenery(int prsid, int prid, String prsname, String prsimg,
			String prscontent, int prsstatus) {
		super();
		this.prsid = prsid;
		this.prid = prid;
		this.prsname = prsname;
		this.prsimg = prsimg;
		this.prscontent = prscontent;
		this.prsstatus = prsstatus;
	}
	public Proscenery() {
		super();
	}
	
	
	
	
}
