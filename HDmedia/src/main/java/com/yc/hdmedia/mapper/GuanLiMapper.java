package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.GuanLi;

public interface GuanLiMapper {

	public List<GuanLi> selectAllGuanLis(Map<String,Object> params);
	
	public GuanLi selectGuanLiByGLId(int glid);

	public int selectTotal();
	
}
