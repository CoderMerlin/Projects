package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.mapper.GuanLiMapper;
import com.yc.hdmedia.service.GuanLiService;

@Service("guanLiService")
public class GuanLiServiceImpl implements GuanLiService {
	
	@Autowired
	private GuanLiMapper guanLiMapper;

	@Override
	public boolean GuanLiLogin(String glname, String glpwd) {
		return false;
	}

	@Override
	public boolean addGuanLi(GuanLi guanLi) {
		return false;
	}

	@Override
	public boolean alterGuanli(GuanLi guanLi) {
		return false;
	}

	@Override
	public boolean alterGuanLipwd(int glid, String oldGuanLiPwd,String newGuanLiPwd) {
		return false;
	}

	@Override
	public boolean del(int glid) {
		return false;
	}

	@Override
	public int total() {
		return guanLiMapper.selectTotal();
	}

	@Override
	public List<GuanLi> findAllGuanLis(int pageNo, int pageSize) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return guanLiMapper.selectAllGuanLis(params);
	}

	@Override
	public GuanLi findGuanLiByGLId(int glid) {
		return guanLiMapper.selectGuanLiByGLId(glid);
	}

}
