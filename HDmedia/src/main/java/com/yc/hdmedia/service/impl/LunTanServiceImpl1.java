package com.yc.hdmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.LunTanBean;
import com.yc.hdmedia.mapper.LunTanMapper;
import com.yc.hdmedia.service.LunTanService;

@Service("lunTanService")
public class LunTanServiceImpl1 implements LunTanService {
	@Autowired
	public LunTanMapper lunTanMapper;
	@Override
	public List<LunTanBean> findLunTan_jlq() {
		return lunTanMapper.findLunTan_jlq();
	}
	@Override
	public List<LunTanBean> findLunTan_whjlq() {
		return lunTanMapper.findLunTan_whjlq();
	}
	@Override
	public List<LunTanBean> findLunTan_zyfx() {
		return lunTanMapper.findLunTan_zyfx();
	}
	@Override
	public List<LunTanBean> findLunTan_zzhz() {
		return lunTanMapper.findLunTan_zzhz();
	}
	@Override
	public List<LunTanBean> findLunTan() {
		return lunTanMapper.findLunTan();
	}

}
