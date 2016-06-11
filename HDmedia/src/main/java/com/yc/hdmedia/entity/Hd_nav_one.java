package com.yc.hdmedia.entity;

import java.io.Serializable;

public class Hd_nav_one implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2043845217346186287L;
	private int nav1id; //一级导航栏id
	private String nav1name; //一级导航栏名称
	private int nav1status; //一级导航栏状态
	@Override
	public String toString() {
		return "hd_nav_one [nav1id=" + nav1id + ", nav1name=" + nav1name
				+ ", nav1status=" + nav1status + "]";
	}
	public int getNav1id() {
		return nav1id;
	}
	public int getNav1ids() {
		return nav1id;
	}
	public void setNav1id(int nav1id) {
		this.nav1id = nav1id;
	}
	public String getNav1name() {
		return nav1name;
	}
	public void setNav1name(String nav1name) {
		this.nav1name = nav1name;
	}
	public int getNav1status() {
		return nav1status;
	}
	public void setNav1status(int nav1status) {
		this.nav1status = nav1status;
	}
	public Hd_nav_one(int nav1id, String nav1name, int nav1status) {
		super();
		this.nav1id = nav1id;
		this.nav1name = nav1name;
		this.nav1status = nav1status;
	}
	public Hd_nav_one() {
		super();
	}
}
