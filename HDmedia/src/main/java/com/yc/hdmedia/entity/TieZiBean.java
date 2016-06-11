package com.yc.hdmedia.entity;

import java.io.Serializable;

public class TieZiBean implements Serializable{
	private static final long serialVersionUID = 3518558283136350484L;
	private int tid;
	private int ltid;
	private int yhid;
	private String tzname;
	private String tzzy;
	private String tztime;
	private int weight;
	private String tztext;
	private String tzphoto;
	private int tzclick;
	private int status;
	private String ltname;
	private String yhzsname;
	private String yhphoto;
	private int pageNo;
	private int pageSize;
	private int count;
	private int TerdayCount;
	private int YesterdayCount;
	
	public int getTid() {
		return tid;
	}
	public void setTid(int tid) {
		this.tid = tid;
	}
	public int getLtid() {
		return ltid;
	}
	public void setLtid(int ltid) {
		this.ltid = ltid;
	}
	public int getYhid() {
		return yhid;
	}
	public void setYhid(int yhid) {
		this.yhid = yhid;
	}
	public String getTzname() {
		return tzname;
	}
	public void setTzname(String tzname) {
		this.tzname = tzname;
	}
	public String getTzzy() {
		return tzzy;
	}
	public void setTzzy(String tzzy) {
		this.tzzy = tzzy;
	}
	public String getTztime() {
		return tztime;
	}
	public void setTztime(String tztime) {
		this.tztime = tztime;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getTztext() {
		return tztext;
	}
	public void setTztext(String tztext) {
		this.tztext = tztext;
	}
	public String getTzphoto() {
		return tzphoto;
	}
	public void setTzphoto(String tzphoto) {
		this.tzphoto = tzphoto;
	}
	public int getTzclick() {
		return tzclick;
	}
	public void setTzclick(int tzclick) {
		this.tzclick = tzclick;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getLtname() {
		return ltname;
	}
	public void setLtname(String ltname) {
		this.ltname = ltname;
	}
	public String getYhzsname() {
		return yhzsname;
	}
	public void setYhzsname(String yhzsname) {
		this.yhzsname = yhzsname;
	}
	public String getYhphoto() {
		return yhphoto;
	}
	public void setYhphoto(String yhphoto) {
		this.yhphoto = yhphoto;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
	
	public int getPageNo() {
		return pageNo;
	}
	public void setPageNo(int pageNo) {
		this.pageNo = pageNo;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	
	
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	
	
	public int getTerdayCount() {
		return TerdayCount;
	}
	public void setTerdayCount(int terdayCount) {
		TerdayCount = terdayCount;
	}
	public int getYesterdayCount() {
		return YesterdayCount;
	}
	public void setYesterdayCount(int yesterdayCount) {
		YesterdayCount = yesterdayCount;
	}
	@Override
	public String toString() {
		return "\nTieZiBean [tid=" + tid + ", ltid=" + ltid + ", yhid=" + yhid
				+ ", tzname=" + tzname + ", tzzy=" + tzzy + ", tztime="
				+ tztime + ", weight=" + weight + ", tztext=" + tztext
				+ ", tzphoto=" + tzphoto + ", tzclick=" + tzclick + ", status="
				+ status + ", ltname=" + ltname + ", yhzsname=" + yhzsname
				+ ", yhphoto=" + yhphoto + ", pageNo=" + pageNo + ", pageSize="
				+ pageSize + ", count=" + count + ", TerdayCount="
				+ TerdayCount + ", YesterdayCount=" + YesterdayCount + "]";
	}
	public TieZiBean(int tid, int ltid, int yhid, String tzname, String tzzy,
			String tztime, int weight, String tztext, String tzphoto,
			int tzclick, int status, String ltname, String yhzsname,
			String yhphoto, int pageNo, int pageSize, int count,
			int terdayCount, int yesterdayCount) {
		super();
		this.tid = tid;
		this.ltid = ltid;
		this.yhid = yhid;
		this.tzname = tzname;
		this.tzzy = tzzy;
		this.tztime = tztime;
		this.weight = weight;
		this.tztext = tztext;
		this.tzphoto = tzphoto;
		this.tzclick = tzclick;
		this.status = status;
		this.ltname = ltname;
		this.yhzsname = yhzsname;
		this.yhphoto = yhphoto;
		this.pageNo = pageNo;
		this.pageSize = pageSize;
		this.count = count;
		TerdayCount = terdayCount;
		YesterdayCount = yesterdayCount;
	}
	public TieZiBean() {
		super();
	}
	
	
	
	
	
	
}
