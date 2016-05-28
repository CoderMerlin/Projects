package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
