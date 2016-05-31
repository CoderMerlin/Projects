package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.PropersonBack;
import com.yc.hdmedia.entity.ProvinceBack;
import com.yc.hdmedia.mapper.GeoraphyBackMapper;
import com.yc.hdmedia.service.GeoraphyBackService;

/**
 * 地域的实现
 * @author HM
 *
 */
@Service("georaphyBackService")
public class GeoraphyBackServiceImpl implements GeoraphyBackService {
	@Autowired
	private GeoraphyBackMapper georaphyBackMapper;
	
	
	//查询所有的省份城市
	@Override
	public List<ProvinceBack> getAllProvinces(int pageNo,int pageSize) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return georaphyBackMapper.selectAllProvinces(params);
	}


	@Override
	public int total() {
		return georaphyBackMapper.selectTotal();
	}



	/**
	 * 通过省份id查看所有人物信息
	 * @param prpid
	 * @return
	 */
	@Override
	public List<PropersonBack> getAllPersonsByPrid(int prid) {
		return georaphyBackMapper.selectAllPersonsByPrid(prid);
	}


	/**
	 * 后台通过省份id获得所有的人物名称
	 * @param prid
	 * @return
	 */
	@Override
	public List<PropersonBack> getAllPersonNamesByPrid(int prid) {
		return georaphyBackMapper.selectAllPersonNamesByPrid(prid);
	}

	//通过人物id获取人物的信息
	@Override
	public PropersonBack getPersonByPrpid(int prpid) {
		return georaphyBackMapper.selectPersonByPrpid(prpid);
	}


	/**
	 * 后台获取所有省份的名称
	 * @return
	 */
	@Override
	public List<ProvinceBack> getAllProvincePrnames() {
		return georaphyBackMapper.selectAllProvincePrnames();
	}

}
