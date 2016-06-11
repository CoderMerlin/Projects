package com.yc.hdmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.NavBean;
import com.yc.hdmedia.mapper.NavMapper;
import com.yc.hdmedia.service.NavService;

@Service("navService")
public class NavServiceImpl implements NavService {
	@Autowired
	public NavMapper navMapper;
	@Override
	public List<NavBean> findNavBean() {
		return navMapper.findNavBean();
	}

}
