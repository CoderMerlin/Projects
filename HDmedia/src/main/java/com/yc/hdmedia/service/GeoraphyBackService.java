package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.ProvinceBack;



public interface GeoraphyBackService {

	List<ProvinceBack> getAllProvinces(int pageNo,int pageSize);
	
	/**
	 * 总记录数
	 * @return
	 */
	public int total();
	

}
