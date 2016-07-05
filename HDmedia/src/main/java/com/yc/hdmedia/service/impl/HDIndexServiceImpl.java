package com.yc.hdmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.HDIndexBean;
import com.yc.hdmedia.mapper.HDIndexMapper;
import com.yc.hdmedia.service.HDIndexService;


@Service("hDIndexService")
public class HDIndexServiceImpl implements HDIndexService {

	@Autowired
	private HDIndexMapper hDIndexMapper;
	

	/**
	 * 首页获取所有信息
	 * @author HM
	 * @return
	 */
	@Override
	public List<HDIndexBean> getIndexAllInfo() {
		return hDIndexMapper.selectIndexAllInfo();
	}


}
