package com.yc.hdmedia.entity;

public class Dietary {
	private int dtid;
	private String photo;
	private int dtpid;
	private String dtpname;
	private String dtname;
	private String dttitle;
	private String dttext;
	private int status;
	public int getDtid() {
		return dtid;
	}
	public void setDtid(int dtid) {
		this.dtid = dtid;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}
	public int getDtpid() {
		return dtpid;
	}
	public void setDtpid(int dtpid) {
		this.dtpid = dtpid;
	}
	public String getDtpname() {
		return dtpname;
	}
	public void setDtpname(String dtpname) {
		this.dtpname = dtpname;
	}
	public String getDtname() {
		return dtname;
	}
	public void setDtname(String dtname) {
		this.dtname = dtname;
	}
	public String getDttitle() {
		return dttitle;
	}
	public void setDttitle(String dttitle) {
		this.dttitle = dttitle;
	}
	public String getDttext() {
		return dttext;
	}
	public void setDttext(String dttext) {
		this.dttext = dttext;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Dietary [dtid=" + dtid + ", photo=" + photo + ", dtpid="
				+ dtpid + ", dtpname=" + dtpname + ", dtname=" + dtname
				+ ", dttitle=" + dttitle + ", dttext=" + dttext + ", status="
				+ status + "]";
	}
	public Dietary(int dtid, String photo, int dtpid, String dtpname,
			String dtname, String dttitle, String dttext, int status) {
		super();
		this.dtid = dtid;
		this.photo = photo;
		this.dtpid = dtpid;
		this.dtpname = dtpname;
		this.dtname = dtname;
		this.dttitle = dttitle;
		this.dttext = dttext;
		this.status = status;
	}
	public Dietary() {
		super();
	}
	
	
}	
