package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.PropersonBack;
import com.yc.hdmedia.entity.Proscenery;
import com.yc.hdmedia.entity.ProvinceBack;

public interface GeoraphyBackMapper {

	public List<ProvinceBack> selectAllProvinces(Map<String,Object> params);

	public int selectTotal();


	/**
	 * 通过省份id查看所有人物信息
	 * @param prpid
	 * @return
	 */
	public List<PropersonBack> selectAllPersonsByPrid(int prpid);

	/**
	 * 后台通过省份id获得所有的人物名称
	 * @param prid
	 * @return
	 */
	public List<PropersonBack> selectAllPersonNamesByPrid(int prid);

	
	/**
	 * 通过人物id获取人物的信息
	 * @param prpid
	 * @return
	 */
	public PropersonBack selectPersonByPrpid(int prpid);

	/**
	 * 后台获取所有省份的名称
	 * @return
	 */
	public List<ProvinceBack> selectAllProvincePrnames();

	/**
	 * 添加人物信息
	 * @param propersonBack
	 * @return
	 */
	public int insertPersonInfo(PropersonBack propersonBack);

	public List<PropersonBack> getPropersonBack();

	public List<Proscenery> getProscenery();

}
