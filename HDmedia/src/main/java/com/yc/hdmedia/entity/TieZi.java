package com.yc.hdmedia.entity;

/**
 * 帖子实体类奋斗奋斗
 * @author Administrator
 *
 */
public class TieZi{	
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
	
	public String toString() {
		return "TieZi [tid=" + tid + ", ltid=" + ltid + ", yhid=" + yhid
				+ ", tzname=" + tzname + ", tzzy=" + tzzy + ", tztime="
				+ tztime + ", weight=" + weight + ", tztext=" + tztext
				+ ", tzphoto=" + tzphoto + ", tzclick=" + tzclick + ", status="
				+ status + ", ltname=" + ltname + ", yhzsname=" + yhzsname
				+ ", yhphoto=" + yhphoto + "]";
	}
	public TieZi(int tid, int ltid, int yhid, String tzname, String tzzy,
			String tztime, int weight, String tztext, String tzphoto,
			int tzclick, int status, String ltname, String yhzsname,
			String yhphoto) {
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
	}
	public TieZi() {
		super();
	}
	
	
}
