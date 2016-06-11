package com.yc.hdmedia.entity;

public class Wenzhang {
	private int wzid; //文章ID
	private int glid; //修改该文章的管理员ID
	private int  wztypeid;  //文章类型ID
	private String wztitle;  //文章标题
	private String wztext;  //文章内容
	private String wzpicture;  //文章图片
	private int wzdianjinnum;  //文章点击数
	private int wzweight;  //文章权重
	private int wzstatus;  //文章状态
	private String wzname;  //类型名字
	private String wzyl1;	//文章作者
	@Override
	public String toString() {
		return "Wenzhang [wzid=" + wzid + ", glid=" + glid + ", wztypeid="
				+ wztypeid + ", wztitle=" + wztitle + ", wztext=" + wztext
				+ ", wzpicture=" + wzpicture + ", wzdianjinnum=" + wzdianjinnum
				+ ", wzweight=" + wzweight + ", wzstatus=" + wzstatus
				+ ", wzname=" + wzname + ", wzyl1=" + wzyl1 + "]";
	}
	public int getWzid() {
		return wzid;
	}
	public int getWzids() {
		return wzid;
	}
	public void setWzid(int wzid) {
		this.wzid = wzid;
	}
	public int getGlid() {
		return glid;
	}
	public void setGlid(int glid) {
		this.glid = glid;
	}
	public int getWztypeid() {
		return wztypeid;
	}
	public void setWztypeid(int wztypeid) {
		this.wztypeid = wztypeid;
	}
	public String getWztitle() {
		return wztitle;
	}
	public void setWztitle(String wztitle) {
		this.wztitle = wztitle;
	}
	public String getWztext() {
		return wztext;
	}
	public void setWztext(String wztext) {
		this.wztext = wztext;
	}
	public String getWzpicture() {
		return wzpicture;
	}
	public void setWzpicture(String wzpicture) {
		this.wzpicture = wzpicture;
	}
	public int getWzdianjinnum() {
		return wzdianjinnum;
	}
	public void setWzdianjinnum(int wzdianjinnum) {
		this.wzdianjinnum = wzdianjinnum;
	}
	public int getWzweight() {
		return wzweight;
	}
	public void setWzweight(int wzweight) {
		this.wzweight = wzweight;
	}
	public int getWzstatus() {
		return wzstatus;
	}
	public void setWzstatus(int wzstatus) {
		this.wzstatus = wzstatus;
	}
	public String getWzname() {
		return wzname;
	}
	public void setWzname(String wzname) {
		this.wzname = wzname;
	}
	public String getWzyl1() {
		return wzyl1;
	}
	public void setWzyl1(String wzyl1) {
		this.wzyl1 = wzyl1;
	}
	public Wenzhang(int wzid, int glid, int wztypeid, String wztitle,
			String wztext, String wzpicture, int wzdianjinnum, int wzweight,
			int wzstatus, String wzname,String wzyl1) {
		super();
		this.wzid = wzid;
		this.glid = glid;
		this.wztypeid = wztypeid;
		this.wztitle = wztitle;
		this.wztext = wztext;
		this.wzpicture = wzpicture;
		this.wzdianjinnum = wzdianjinnum;
		this.wzweight = wzweight;
		this.wzstatus = wzstatus;
		this.wzname = wzname;
		this.wzyl1=wzyl1;
	}
	public Wenzhang() {
		super();
	}
}
