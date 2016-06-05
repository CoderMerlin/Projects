package com.yc.hdmedia.entity;


/**
 * 搜索页面显示的内容
 * @author HM
 *
 */
public class HDSearch {
	private int ltid;  //论坛id
	private String ltname; //论坛名称
	private String yhname;  //作者注册账号名
	private int tzclick;  //帖子点击数
	private String tzname; //帖子名称
	private String tztext; //帖子内容
	private String tztime;  //帖子时间
	public int getLtid() {
		return ltid;
	}
	public void setLtid(int ltid) {
		this.ltid = ltid;
	}
	public String getLtname() {
		return ltname;
	}
	public void setLtname(String ltname) {
		this.ltname = ltname;
	}
	public String getYhname() {
		return yhname;
	}
	public void setYhname(String yhname) {
		this.yhname = yhname;
	}
	public int getTzclick() {
		return tzclick;
	}
	public void setTzclick(int tzclick) {
		this.tzclick = tzclick;
	}
	public String getTzname() {
		return tzname;
	}
	public void setTzname(String tzname) {
		this.tzname = tzname;
	}
	public String getTztext() {
		return tztext;
	}
	public void setTztext(String tztext) {
		this.tztext = tztext;
	}
	public String getTztime() {
		return tztime;
	}
	public void setTztime(String tztime) {
		this.tztime = tztime;
	}
	@Override
	public String toString() {
		return "HDSearch [ltid=" + ltid + ", ltname=" + ltname + ", yhname="
				+ yhname + ", tzclick=" + tzclick + ", tzname=" + tzname
				+ ", tztext=" + tztext + ", tztime=" + tztime + "]";
	}
	public HDSearch(int ltid, String ltname, String yhname, int tzclick,
			String tzname, String tztext, String tztime) {
		super();
		this.ltid = ltid;
		this.ltname = ltname;
		this.yhname = yhname;
		this.tzclick = tzclick;
		this.tzname = tzname;
		this.tztext = tztext;
		this.tztime = tztime;
	}
	
	
	public HDSearch() {
		
	}
	
	
	
	
	
}
