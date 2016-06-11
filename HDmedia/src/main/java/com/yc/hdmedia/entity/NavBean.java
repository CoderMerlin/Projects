package com.yc.hdmedia.entity;

import java.util.List;

public class NavBean {
	private int nav1id; //一级导航栏id
	private String nav1name; //一级导航栏名称
	private int nav1status; //一级导航栏状态
	private List<Hd_nav_two> navTow;
	public int getNav1id() {
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
	public List<Hd_nav_two> getNavTow() {
		return navTow;
	}
	public void setNavTow(List<Hd_nav_two> navTow) {
		this.navTow = navTow;
	}
	@Override
	public String toString() {
		return "\nNavBean [nav1id=" + nav1id + ", nav1name=" + nav1name
				+ ", nav1status=" + nav1status + ", navTow=" + navTow + "]";
	}
	
	
}
