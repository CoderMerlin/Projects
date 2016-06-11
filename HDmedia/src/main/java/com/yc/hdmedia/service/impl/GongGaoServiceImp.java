package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.GongGao;
import com.yc.hdmedia.mapper.GongGaoMapper;
import com.yc.hdmedia.service.GongGaoService;

@Service("gongGaoService")
public class GongGaoServiceImp implements GongGaoService {

	@Autowired
	private GongGaoMapper gongGaoMapper;
	
	@Override
	public int addGongGao(GongGao gonggao) {
		return gongGaoMapper.addGongGao(gonggao);
	}

	@Override
	public int updateGongGao(GongGao gonggao) {
		return gongGaoMapper.updateGongGao(gonggao);
	}

	@Override
	public int del(int[] gid) {
		return gongGaoMapper.del(gid);
	}

	@Override
	public int total() {
		return gongGaoMapper.total();
	}

	@Override
	public List<GongGao> getAllGongGao(int pageNo, int pageSize) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return gongGaoMapper.getAllGongGao(params);
	}

	@Override
	public GongGao findGongGaoByGid(int gid) {
		return gongGaoMapper.findGongGaoByGid(gid);
	}


}
