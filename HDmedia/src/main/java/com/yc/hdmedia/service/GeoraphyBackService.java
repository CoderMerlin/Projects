package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.PropersonBack;
import com.yc.hdmedia.entity.ProvinceBack;



public interface GeoraphyBackService {

	List<ProvinceBack> getAllProvinces(int pageNo,int pageSize);
	
	/**
	 * 总记录数
	 * @return
	 */
	public int total();

	/**
	 * 通过省份id查看所有人物信息
	 * @param prpid
	 * @return
	 */
	List<PropersonBack> getAllPersonsByPrid(int prid);

	/**
	 * 后台通过省份id获得所有的人物名称
	 * @param prid
	 * @return
	 */
	List<PropersonBack> getAllPersonNamesByPrid(int prid);

	
	/**
	 * 通过人物id获取人物的信息
	 * @param prpid
	 * @return
	 */
	PropersonBack getPersonByPrpid(int prpid);

	
	/**
	 * 后台获取所有省份的名称
	 * @return
	 */
	List<ProvinceBack> getAllProvincePrnames();
	

}
