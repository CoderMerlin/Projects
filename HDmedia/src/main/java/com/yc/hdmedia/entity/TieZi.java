package com.yc.hdmedia.entity;

import java.io.Serializable;

/**
 * 帖子实体类奋斗奋斗
 * @author Administrator
 *
 */
public class TieZi  implements Serializable{
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

	public String getYhphoto() {
		return yhphoto;
	}



	public void setYhphoto(String yhphoto) {
		this.yhphoto = yhphoto;
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ltid;
		result = prime * result + ((ltname == null) ? 0 : ltname.hashCode());
		result = prime * result + status;
		result = prime * result + tid;
		result = prime * result + tzclick;
		result = prime * result + ((tzname == null) ? 0 : tzname.hashCode());
		result = prime * result + ((tzphoto == null) ? 0 : tzphoto.hashCode());
		result = prime * result + ((tztext == null) ? 0 : tztext.hashCode());
		result = prime * result + ((tztime == null) ? 0 : tztime.hashCode());
		result = prime * result + ((tzzy == null) ? 0 : tzzy.hashCode());
		result = prime * result + weight;
		result = prime * result + yhid;
		result = prime * result
				+ ((yhzsname == null) ? 0 : yhzsname.hashCode());
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
		TieZi other = (TieZi) obj;
		if (ltid != other.ltid)
			return false;
		if (ltname == null) {
			if (other.ltname != null)
				return false;
		} else if (!ltname.equals(other.ltname))
			return false;
		if (status != other.status)
			return false;
		if (tid != other.tid)
			return false;
		if (tzclick != other.tzclick)
			return false;
		if (tzname == null) {
			if (other.tzname != null)
				return false;
		} else if (!tzname.equals(other.tzname))
			return false;
		if (tzphoto == null) {
			if (other.tzphoto != null)
				return false;
		} else if (!tzphoto.equals(other.tzphoto))
			return false;
		if (tztext == null) {
			if (other.tztext != null)
				return false;
		} else if (!tztext.equals(other.tztext))
			return false;
		if (tztime == null) {
			if (other.tztime != null)
				return false;
		} else if (!tztime.equals(other.tztime))
			return false;
		if (tzzy == null) {
			if (other.tzzy != null)
				return false;
		} else if (!tzzy.equals(other.tzzy))
			return false;
		if (weight != other.weight)
			return false;
		if (yhid != other.yhid)
			return false;
		if (yhzsname == null) {
			if (other.yhzsname != null)
				return false;
		} else if (!yhzsname.equals(other.yhzsname))
			return false;
		return true;
	}


	public int getTid() {
		return tid;
	}

	public int getTids() {
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

	

	@Override
	public String toString() {
		return "TieZi [tid=" + tid + ", ltid=" + ltid + ", yhid=" + yhid
				+ ", tzname=" + tzname + ", tzzy=" + tzzy + ", tztime="
				+ tztime + ", weight=" + weight + ", tztext=" + tztext
				+ ", tzphoto=" + tzphoto + ", tzclick=" + tzclick + ", status="
				+ status + ", ltname=" + ltname + ", yhzsname=" + yhzsname
				+ "]";
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
