package com.yc.hdmedia.entity;

/**
 * 论坛实体类 下次的说法
 * @author Administrator
 *
 */
public class LunTan {
	private int ltid;  //论坛id
	private String ltname; //论坛名称
	private String lttime; //论坛创建时间
	private int ltstatus; //状态
	private String ltyl1; //论坛类型
	private String ltyl2; //论坛图片
	public int getLtid() {
		return ltid;
	}

	public int getLtids() {
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


	public String getLttime() {
		return lttime;
	}


	public void setLttime(String lttime) {
		this.lttime = lttime;
	}


	public int getLtstatus() {
		return ltstatus;
	}


	public void setLtstatus(int ltstatus) {
		this.ltstatus = ltstatus;
	}


	public String getLtyl1() {
		return ltyl1;
	}


	public void setLtyl1(String ltyl1) {
		this.ltyl1 = ltyl1;
	}


	public String getltyl2() {
		return ltyl2;
	}


	public void setltyl2(String ltyl2) {
		this.ltyl2 = ltyl2;
	}

	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ltid;
		result = prime * result + ((ltname == null) ? 0 : ltname.hashCode());
		result = prime * result + ((ltyl2 == null) ? 0 : ltyl2.hashCode());
		result = prime * result + ltstatus;
		result = prime * result + ((lttime == null) ? 0 : lttime.hashCode());
		result = prime * result + ((ltyl1 == null) ? 0 : ltyl1.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LunTan other = (LunTan) obj;
		if (ltid != other.ltid)
			return false;
		if (ltname == null) {
			if (other.ltname != null)
				return false;
		} else if (!ltname.equals(other.ltname))
			return false;
		if (ltyl2 == null) {
			if (other.ltyl2 != null)
				return false;
		} else if (!ltyl2.equals(other.ltyl2))
			return false;
		if (ltstatus != other.ltstatus)
			return false;
		if (lttime == null) {
			if (other.lttime != null)
				return false;
		} else if (!lttime.equals(other.lttime))
			return false;
		if (ltyl1 == null) {
			if (other.ltyl1 != null)
				return false;
		} else if (!ltyl1.equals(other.ltyl1))
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "LunTan [ltid=" + ltid + ", ltname=" + ltname + ", lttime="
				+ lttime + ", ltstatus=" + ltstatus + ", ltyl1=" + ltyl1
				+ ", ltyl2=" + ltyl2 + "]";
	}

	public LunTan(int ltid, String ltname, String lttime, int ltstatus,
			String ltyl1, String ltyl2) {
		super();
		this.ltid = ltid;
		this.ltname = ltname;
		this.lttime = lttime;
		this.ltstatus = ltstatus;
		this.ltyl1 = ltyl1;
		this.ltyl2 = ltyl2;
	}

	public LunTan() {
		super();
	}
	
	

	
	
	
	
	
	
}
