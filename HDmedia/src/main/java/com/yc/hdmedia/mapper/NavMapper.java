package com.yc.hdmedia.mapper;

import java.util.List;

import com.yc.hdmedia.entity.NavBean;


public interface NavMapper {
	//查询导航栏
	public List<NavBean> findNavBean();
}
