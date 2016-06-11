package com.yc.hdmedia.entity;

import java.io.Serializable;

public class Hd_nav_two implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5150133651700646955L;
	private int nav2id; //二级导航栏id
	private int nav1id; //一级导航栏id
	private String nav2name; //二级导航栏名称 
	private int nav2status; //二级导航栏状态
	private String nav1name; //一级导航栏名称
	private int rownum; //一级导航栏名称
	
	
	

	@Override
	public String toString() {
		return "Hd_nav_two [nav2id=" + nav2id + ", nav1id=" + nav1id
				+ ", nav2name=" + nav2name + ", nav2status=" + nav2status
				+ ", nav1name=" + nav1name + ", rownum=" + rownum + "]";
	}



	public int getRownum() {
		return rownum;
	}



	public void setRownum(int rownum) {
		this.rownum = rownum;
	}



	public int getNav2id() {
		return nav2id;
	}
	public void setNav2id(int nav2id) {
		this.nav2id = nav2id;
	}
	public int getNav1id() {
		return nav1id;
	}
	public void setNav1id(int nav1id) {
		this.nav1id = nav1id;
	}
	public String getNav2name() {
		return nav2name;
	}
	public void setNav2name(String nav2name) {
		this.nav2name = nav2name;
	}
	public int getNav2status() {
		return nav2status;
	}
	public void setNav2status(int nav2status) {
		this.nav2status = nav2status;
	}
	public String getNav1name() {
		return nav1name;
	}
	public void setNav1name(String nav1name) {
		this.nav1name = nav1name;
	}
	
	public Hd_nav_two(int nav2id, int nav1id, String nav2name, int nav2status,
			String nav1name, int rownum) {
		super();
		this.nav2id = nav2id;
		this.nav1id = nav1id;
		this.nav2name = nav2name;
		this.nav2status = nav2status;
		this.nav1name = nav1name;
		this.rownum = rownum;
	}



	public Hd_nav_two() {
		super();
	}
}
