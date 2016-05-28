package com.yc.hdmedia.entity;

/**
 * 后台页面显示的省份信息
 * @author HM
 *
 */
public class ProvinceBack {
	private int prid;  //省份的id
	private String prname;  //省份的名称
	private String prcontent; //省份的介绍内容
	private int prstatus;  //省份的状态
	
	
	public int getPrid() {
		return prid;
	}
	public void setPrid(int prid) {
		this.prid = prid;
	}
	public String getPrname() {
		return prname;
	}
	public void setPrname(String prname) {
		this.prname = prname;
	}
	public String getPrcontent() {
		return prcontent;
	}
	public void setPrcontent(String prcontent) {
		this.prcontent = prcontent;
	}
	public int getPrstatus() {
		return prstatus;
	}
	public void setPrstatus(int prstatus) {
		this.prstatus = prstatus;
	}
	
	
	@Override
	public String toString() {
		return "ProvinceBack [prid=" + prid + ", prname=" + prname
				+ ", prcontent=" + prcontent + ", prstatus=" + prstatus + "]";
	}
	
	
	
	
}
