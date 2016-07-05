package com.yc.hdmedia.entity;

public class YongHu {
	private int yhid;
	private int yhzcid;
	private String yhzsname;
	private String yhsex;
	private int yhage;
	private String yhphone;
	private String yhindentity;
	private String yhqq;
	private String yhbirthday;
	private String yhaddr;
	private String yhphoto;
	private String yhftnum;
	private String yhdownload;
	private String yhupload;
	private String yhjf;
	private int yhqd;
	private int yhjy;
	private String yhzhtime;
	private String yhqianming;
	private int yhstatus;
	private String yhyl1;
	private String yhyl2;
	
	private String yhname;
	private String yhemail;
	private String yhzctime;
	
	public String getYhname() {
		return yhname;
	}
	public void setYhname(String yhname) {
		this.yhname = yhname;
	}
	public String getYhemail() {
		return yhemail;
	}
	public void setYhemail(String yhemail) {
		this.yhemail = yhemail;
	}
	public String getYhzctime() {
		return yhzctime;
	}
	public void setYhzctime(String yhzctime) {
		this.yhzctime = yhzctime;
	}
	public int getYhid() {
		return yhid;
	}
	public void setYhid(int yhid) {
		this.yhid = yhid;
	}
	public int getYhzcid() {
		return yhzcid;
	}
	public void setYhzcid(int yhzcid) {
		this.yhzcid = yhzcid;
	}
	public String getYhzsname() {
		return yhzsname;
	}
	public void setYhzsname(String yhzsname) {
		this.yhzsname = yhzsname;
	}
	public String getYhsex() {
		return yhsex;
	}
	public void setYhsex(String yhsex) {
		this.yhsex = yhsex;
	}
	public int getYhage() {
		return yhage;
	}
	public void setYhage(int yhage) {
		this.yhage = yhage;
	}
	public String getYhphone() {
		return yhphone;
	}
	public void setYhphone(String yhphone) {
		this.yhphone = yhphone;
	}
	public String getYhindentity() {
		return yhindentity;
	}
	public void setYhindentity(String yhindentity) {
		/*if(yhindentity!=null){
			String yhindentitys=yhindentity.substring(0,3)+"*****"+yhindentity.substring(11,yhindentity.length());
			this.yhindentity = yhindentitys;
		}*/
		this.yhindentity = yhindentity;
		
	}
	public String getYhqq() {
		return yhqq;
	}
	public void setYhqq(String yhqq) {
		this.yhqq = yhqq;
	}
	public String getYhbirthday() {
		/*String [] arr=yhbirthday.split(" ");
		if(arr.length>0){
			yhbirthday=arr[0];
		}*/
		return yhbirthday;
	}
	public void setYhbirthday(String yhbirthday) {
		this.yhbirthday = yhbirthday;
	}
	public String getYhaddr() {
		return yhaddr;
	}
	public void setYhaddr(String yhaddr) {
		this.yhaddr = yhaddr;
	}
	public String getYhphoto() {
		return yhphoto;
	}
	public void setYhphoto(String yhphoto) {
		this.yhphoto = yhphoto;
	}
	public String getYhftnum() {
		return yhftnum;
	}
	public void setYhftnum(String yhftnum) {
		this.yhftnum = yhftnum;
	}
	public String getYhdownload() {
		return yhdownload;
	}
	public void setYhdownload(String yhdownload) {
		this.yhdownload = yhdownload;
	}
	public String getYhupload() {
		return yhupload;
	}
	public void setYhupload(String yhupload) {
		this.yhupload = yhupload;
	}
	public String getYhjf() {
		return yhjf;
	}
	public void setYhjf(String yhjf) {
		this.yhjf = yhjf;
	}
	public int getYhqd() {
		return yhqd;
	}
	public void setYhqd(int yhqd) {
		this.yhqd = yhqd;
	}
	public int getYhjy() {
		return yhjy;
	}
	public void setYhjy(int yhjy) {
		this.yhjy = yhjy;
	}
	public String getYhzhtime() {
		return yhzhtime;
	}
	public void setYhzhtime(String yhzhtime) {
		this.yhzhtime = yhzhtime;
	}
	public String getYhqianming() {
		return yhqianming;
	}
	public void setYhqianming(String yhqianming) {
		this.yhqianming = yhqianming;
	}
	public int getYhstatus() {
		return yhstatus;
	}
	public void setYhstatus(int yhstatus) {
		this.yhstatus = yhstatus;
	}
	public String getYhyl1() {
		return yhyl1;
	}
	public void setYhyl1(String yhyl1) {
		this.yhyl1 = yhyl1;
	}
	public String getYhyl2() {
		return yhyl2;
	}
	public void setYhyl2(String yhyl2) {
		this.yhyl2 = yhyl2;
	}
	@Override
	public String toString() {
		return "YongHu [yhid=" + yhid + ", yhzcid=" + yhzcid + ", yhzsname=" + yhzsname + ", yhsex=" + yhsex + ", yhage=" + yhage + ", yhphone=" + yhphone + ", yhindentity=" + yhindentity + ", yhqq="
				+ yhqq + ", yhbirthday=" + yhbirthday + ", yhaddr=" + yhaddr + ", yhphoto=" + yhphoto + ", yhftnum=" + yhftnum + ", yhdownload=" + yhdownload + ", yhupload=" + yhupload + ", yhjf="
				+ yhjf + ", yhqd=" + yhqd + ", yhjy=" + yhjy + ", yhzhtime=" + yhzhtime + ", yhqianming=" + yhqianming + ", yhstatus=" + yhstatus + ", yhyl1=" + yhyl1 + ", yhyl2=" + yhyl2
				+ ", yhname=" + yhname + ", yhemail=" + yhemail + ", yhzctime=" + yhzctime + "]";
	}
	
}
