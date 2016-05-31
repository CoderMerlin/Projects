package com.yc.hdmedia.entity;

/**
 * 后台人物表
 * @author HM
 *
 */
public class PropersonBack {
	
	private int prpid;  //人物的id;
	private int prid;   //省份的Id
	private String ppname;  //人物的名称
	private String ppcontent; //人物的介绍
	private String ppimg;  //人物的图片
	private int ppsatus;  //人物的状态
	
	
	public int getPrpid() {
		return prpid;
	}
	public void setPrpid(int prpid) {
		this.prpid = prpid;
	}
	public int getPrid() {
		return prid;
	}
	public void setPrid(int prid) {
		this.prid = prid;
	}
	public String getPpname() {
		return ppname;
	}
	public void setPpname(String ppname) {
		this.ppname = ppname;
	}
	public String getPpcontent() {
		return ppcontent;
	}
	public void setPpcontent(String ppcontent) {
		this.ppcontent = ppcontent;
	}
	public String getPpimg() {
		return ppimg;
	}
	public void setPpimg(String ppimg) {
		this.ppimg = ppimg;
	}
	public int getPpsatus() {
		return ppsatus;
	}
	public void setPpsatus(int ppsatus) {
		this.ppsatus = ppsatus;
	}
	
	
	@Override
	public String toString() {
		return "PropersonBack [prpid=" + prpid + ", prid=" + prid + ", ppname="
				+ ppname + ", ppcontent=" + ppcontent + ", ppimg=" + ppimg
				+ ", ppsatus=" + ppsatus + "]";
	}
	
	
}
