package com.yc.hdmedia.service.impl;

import java.util.ArrayList;
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
	public GuanLi login(GuanLi guanLi) {
		return guanLiMapper.login(guanLi);
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
	public int del(int[] glid) {
		return guanLiMapper.deleteGuanLiById(glid);
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

	@Override
	public int updateGuanLiInfo(GuanLi guanLi) {
		return guanLiMapper.updateGuanLiByGLId(guanLi);
	}

	@Override
	public GuanLi getGuanLiById(int glid) {
		return guanLiMapper.getGuanLiById(glid);
	}

	@Override
	public int addGuanLi(GuanLi guanLi) {
		return guanLiMapper.addGuanLi(guanLi);
	}

	
	/**
	 * 管理员登录后修改最后登录时间
	 */
	@Override
	public int updateGuanLiLoginTime(int glid, String glzhtime) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("glid", glid);
		params.put("glzhtime", glzhtime);
		return guanLiMapper.updateGuanLiLoginTime(params);
	}

	/**
	 * 管理员导出excel
	 * @param toglids
	 * @return
	 */
	@Override
	public List<GuanLi> getoracleGlInfoToExcel(String toglids) {
		System.out.println(toglids);
		List<String> params1=new ArrayList<String>();
		if(toglids.contains(",") && toglids.indexOf("or")<=0){ //说明同时删除多个
			String[] more=toglids.split(",");
			for (String aa : more) {
				params1.add(aa);
			}
			return guanLiMapper.getMoreGLInfoToExcel(params1);
		}else{
			return guanLiMapper.getGLInfoToExcel(toglids);
		}
	}

}
