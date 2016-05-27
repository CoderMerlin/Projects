package com.yc.hdmedia.entity;

import java.sql.Date;

public class Admin {
	private int glid; //管理员id
	private String glname; //管理员名
	private String glpwd; //管理员密码
	private String glsex; //管理性别
	private String glemail; //管理员邮箱
	private String glphone; //管理员电话
	private String glzsname; //管理员真实名字
	private String glindentity; //管理员身份证
	private Date glzhtime; //管理员最后登录时间
	private int glstatus; //管理员状态   0不存在  1表示存在
	private String glyl1; //预留字段1
	private String glyl2; //预留字段2
	
	public int getGlid() {
		return glid;
	}
	public void setGlid(int glid) {
		this.glid = glid;
	}
	public String getGlname() {
		return glname;
	}
	public void setGlname(String glname) {
		this.glname = glname;
	}
	public String getGlpwd() {
		return glpwd;
	}
	public void setGlpwd(String glpwd) {
		this.glpwd = glpwd;
	}
	public String getGlsex() {
		return glsex;
	}
	public void setGlsex(String glsex) {
		this.glsex = glsex;
	}
	public String getGlemail() {
		return glemail;
	}
	public void setGlemail(String glemail) {
		this.glemail = glemail;
	}
	public String getGlphone() {
		return glphone;
	}
	public void setGlphone(String glphone) {
		this.glphone = glphone;
	}
	public String getGlzsname() {
		return glzsname;
	}
	public void setGlzsname(String glzsname) {
		this.glzsname = glzsname;
	}
	public String getGlindentity() {
		return glindentity;
	}
	public void setGlindentity(String glindentity) {
		this.glindentity = glindentity;
	}
	public Date getGlzhtime() {
		return glzhtime;
	}
	public void setGlzhtime(Date glzhtime) {
		this.glzhtime = glzhtime;
	}
	public int getGlstatus() {
		return glstatus;
	}
	public void setGlstatus(int glstatus) {
		this.glstatus = glstatus;
	}
	public String getGlyl1() {
		return glyl1;
	}
	public void setGlyl1(String glyl1) {
		this.glyl1 = glyl1;
	}
	public String getGlyl2() {
		return glyl2;
	}
	public void setGlyl2(String glyl2) {
		this.glyl2 = glyl2;
	}
	@Override
	public String toString() {
		return "Admin [glid=" + glid + ", glname=" + glname + ", glpwd="
				+ glpwd + ", glsex=" + glsex + ", glemail=" + glemail
				+ ", glphone=" + glphone + ", glzsname=" + glzsname
				+ ", glindentity=" + glindentity + ", glzhtime=" + glzhtime
				+ ", glstatus=" + glstatus + ", glyl1=" + glyl1 + ", glyl2="
				+ glyl2 + "]";
	}
	
	

}
