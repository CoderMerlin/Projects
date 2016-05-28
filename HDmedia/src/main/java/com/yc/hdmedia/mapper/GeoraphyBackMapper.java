package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.ProvinceBack;

public interface GeoraphyBackMapper {

	public List<ProvinceBack> selectAllProvinces(Map<String,Object> params);

	public int selectTotal();
}
