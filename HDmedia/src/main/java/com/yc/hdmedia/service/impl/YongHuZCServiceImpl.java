package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.entity.YongHuZC;
import com.yc.hdmedia.mapper.YongHuZCMapper;
import com.yc.hdmedia.service.YongHuZCService;

@Service("yongHuZCService")
public class YongHuZCServiceImpl implements YongHuZCService {
	@Autowired
	private YongHuZCMapper yongHuZCMapper;
	@Override
	public List<YongHuZC> findAllYongHuZC(int pageNo, int pageSize) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return yongHuZCMapper.getAllYongHuZC(params);
	}

	@Override
	public int total() {
		return yongHuZCMapper.selectTotal();
	}

	@Override
	public int addYongHuZC(YongHuZC yongHuZC) {
		return yongHuZCMapper.addYongHuZC(yongHuZC);
	}

	@Override
	public int updateYongHuZCInfo(YongHuZC yongHuZC) {
		return yongHuZCMapper.updateYongHuZCById(yongHuZC);
	}

	@Override
	public YongHuZC getYongHuZCById(int yhzcid) {
		return yongHuZCMapper.getYongHuZCById(yhzcid);
	}

	@Override
	public int delYongHuZCByIds(int[] yhzcids) {
		return yongHuZCMapper.deleteYongHuZCById(yhzcids);
	}

	

}
