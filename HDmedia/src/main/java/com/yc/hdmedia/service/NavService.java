package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.NavBean;

public interface NavService {
	//查询导航栏
	List<NavBean> findNavBean();
}
