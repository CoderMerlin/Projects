package com.yc.hdmedia.entity;


import com.yc.hdmedia.utils.MD5Encryption;

public class GuanLi {
	
	private int    glid;
	private String glname;
	private String glpwd;
	private String glsex;
	private String glemail;
	private String glphone;
	private String glzsname;
	private String glindentity;
	private String glzhtime;
	private int    glstatus;
	private String glyl1;  
	private String glyl2;
	
	
	public int getGlid() {
		return glid;
	}
	
	public int getGlids() {  
		
		
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
		glpwd=MD5Encryption.createPassword(glpwd);
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
	public String getGlzhtime() {
		return glzhtime;
	}
	public void setGlzhtime(String glzhtime) {
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
	
	
	public GuanLi() {
		/*Date currDate = Calendar.getInstance().getTime();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		glzhtime= sdf.format(currDate);*/
	}

	@Override
	public String toString() {
		return "\nGuanLi [glid=" + glid + ", glname=" + glname + ", glpwd="
				+ glpwd + ", glsex=" + glsex + ", glemail=" + glemail
				+ ", glphone=" + glphone + ", glzsname=" + glzsname
				+ ", glindentity=" + glindentity + ", glzhtime=" + glzhtime
				+ ", glstatus=" + glstatus + ", glyl1=" + glyl1 + ", glyl2="
				+ glyl2 + "]";
	}
	
	
	
}
