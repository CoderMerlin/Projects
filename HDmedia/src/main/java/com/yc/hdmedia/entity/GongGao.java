package com.yc.hdmedia.entity;

public class GongGao {
	private int gid;  //����id
	private String gtitle;//�������
	private String gtext;//��������
	private String gtime;//���洴��ʱ��
	private String gstatus;//״̬       -- 0 ��ʾɾ��   1��ʾ����
	private String ggyl1;//Ԥ���ֶ�1
	private String ggyl2;//Ԥ���ֶ�2
	public int getGid() {
		return gid;
	}
	public void setGid(int gid) {
		this.gid = gid;
	}
	public String getGtitle() {
		return gtitle;
	}
	public void setGtitle(String gtitle) {
		this.gtitle = gtitle;
	}
	public String getGtext() {
		return gtext;
	}
	public void setGtext(String gtext) {
		this.gtext = gtext;
	}
	public String getGtime() {
		return gtime;
	}
	public void setGtime(String gtime) {
		this.gtime = gtime;
	}
	public String getGstatus() {
		return gstatus;
	}
	public void setGstatus(String gstatus) {
		this.gstatus = gstatus;
	}
	public String getGgyl1() {
		return ggyl1;
	}
	public void setGgyl1(String ggyl1) {
		this.ggyl1 = ggyl1;
	}
	public String getGgyl2() {
		return ggyl2;
	}
	public void setGgyl2(String ggyl2) {
		this.ggyl2 = ggyl2;
	}
	@Override
	public String toString() {
		return "GongGao [gid=" + gid + ", gtitle=" + gtitle + ", gtext="
				+ gtext + ", gtime=" + gtime + ", gstatus=" + gstatus
				+ ", ggyl1=" + ggyl1 + ", ggyl2=" + ggyl2 + "]";
	}
	
	
}
