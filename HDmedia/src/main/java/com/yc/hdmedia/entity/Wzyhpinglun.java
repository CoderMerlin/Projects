package com.yc.hdmedia.entity;

public class Wzyhpinglun {
	private int wzplid; //评论id
	private int yhzcid; //发表该评论的用户id
	private String wzpltext; //评论内容
	private String wzpltime; //评论时间
	private int wzid; //被评论作品id
	private int status; //该评论的状态
	private String wztitle; //文章标题
	@Override
	public String toString() {
		return "Wzyhpinglun [wzplid=" + wzplid + ", yhzcid=" + yhzcid
				+ ", wzpltext=" + wzpltext + ", wzpltime=" + wzpltime
				+ ", wzid=" + wzid + ", status=" + status + ", wztitle="
				+ wztitle + "]";
	}
	public int getWzplid() {
		return wzplid;
	}
	public int getWzplids() {
		return wzplid;
	}
	public void setWzplid(int wzplid) {
		this.wzplid = wzplid;
	}
	public int getYhzcid() {
		return yhzcid;
	}
	public void setYhzcid(int yhzcid) {
		this.yhzcid = yhzcid;
	}
	public String getWzpltext() {
		return wzpltext;
	}
	public void setWzpltext(String wzpltext) {
		this.wzpltext = wzpltext;
	}
	public String getWzpltime() {
		return wzpltime;
	}
	public String getWzpltimes() {
		if(wzpltime!=null && wzpltime.length()>10){
			return wzpltime.substring(0,10);
		}
		return wzpltime;
	}
	
	public void setWzpltime(String wzpltime) {
		this.wzpltime = wzpltime;
	}
	public int getWzid() {
		return wzid;
	}
	public void setWzid(int wzid) {
		this.wzid = wzid;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getWztitle() {
		return wztitle;
	}
	public void setWztitle(String wztitle) {
		this.wztitle = wztitle;
	}
	public Wzyhpinglun(int wzplid, int yhzcid, String wzpltext,
			String wzpltime, int wzid, int status, String wztitle) {
		super();
		this.wzplid = wzplid;
		this.yhzcid = yhzcid;
		this.wzpltext = wzpltext;
		this.wzpltime = wzpltime;
		this.wzid = wzid;
		this.status = status;
		this.wztitle = wztitle;
	}
	public Wzyhpinglun() {
		super();
	}
	
	
	
	
}
