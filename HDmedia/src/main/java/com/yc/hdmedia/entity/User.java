package com.yc.hdmedia.entity;

import java.sql.Date;

public class User {
	private int yhzcid; //id
	private String yhname; //�û�ע���˺���
	private String yhpwd;//����
	private String yhemail;//�û�ע������
	private Date yhzctime;//�û�ע��ʱ��
	private int yhzcstatus=1;// ע���û���״̬  0��ʾ������  1��ʾ����
	private String yhzcyl1;//Ԥ���ֶ�1
	private String yhzcyl2;//Ԥ���ֶ�2
	public int getYhzcid() {
		return yhzcid;
	}
	public void setYhzcid(int yhzcid) {
		this.yhzcid = yhzcid;
	}
	public String getYhname() {
		return yhname;
	}
	public void setYhname(String yhname) {
		this.yhname = yhname;
	}
	public String getYhpwd() {
		return yhpwd;
	}
	public void setYhpwd(String yhpwd) {
		this.yhpwd = yhpwd;
	}
	public String getYhemail() {
		return yhemail;
	}
	public void setYhemail(String yhemail) {
		this.yhemail = yhemail;
	}
	public Date getYhzctime() {
		return yhzctime;
	}
	public void setYhzctime(Date yhzctime) {
		this.yhzctime = yhzctime;
	}
	public int getYhzcstatus() {
		return yhzcstatus;
	}
	public void setYhzcstatus(int yhzcstatus) {
		this.yhzcstatus = yhzcstatus;
	}
	public String getYhzcyl1() {
		return yhzcyl1;
	}
	public void setYhzcyl1(String yhzcyl1) {
		this.yhzcyl1 = yhzcyl1;
	}
	public String getYhzcyl2() {
		return yhzcyl2;
	}
	public void setYhzcyl2(String yhzcyl2) {
		this.yhzcyl2 = yhzcyl2;
	}
	@Override
	public String toString() {
		return "User [yhzcid=" + yhzcid + ", yhname=" + yhname + ", yhpwd="
				+ yhpwd + ", yhemail=" + yhemail + ", yhzctime=" + yhzctime
				+ ", yhzcstatus=" + yhzcstatus + ", yhzcyl1=" + yhzcyl1
				+ ", yhzcyl2=" + yhzcyl2 + "]";
	}
	
	
	
}
