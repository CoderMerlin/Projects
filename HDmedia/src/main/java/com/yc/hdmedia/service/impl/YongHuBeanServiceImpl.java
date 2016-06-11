package com.yc.hdmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.YongHuBean;
import com.yc.hdmedia.mapper.YongHuZCMapper;
import com.yc.hdmedia.service.YongHuBeanService;

@Service("yongHuBeanService")
public class YongHuBeanServiceImpl implements YongHuBeanService {
	
	@Autowired
	private YongHuZCMapper yongHuZCMapper;

	@Override
	public List<YongHuBean> findYongHuBeanByInfo(YongHuBean yongHuBean) {
		return yongHuZCMapper.findYongHuBeanByInfo(yongHuBean);
	}

	
}
