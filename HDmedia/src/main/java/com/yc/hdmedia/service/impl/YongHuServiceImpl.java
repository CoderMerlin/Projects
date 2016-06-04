package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.entity.YHBean;
import com.yc.hdmedia.entity.YongHu;
import com.yc.hdmedia.entity.YongHuZC;
import com.yc.hdmedia.mapper.YongHuMapper;
import com.yc.hdmedia.service.YongHuService;

@Service("yongHuService")
public class YongHuServiceImpl implements YongHuService {
	@Autowired
	private YongHuMapper yongHuMapper;
	
	public List<YongHu> findAllYongHu(int pageNo, int pageSize) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return yongHuMapper.getAllYongHu(params);
	}

	@Override
	public int total() {
		return yongHuMapper.getTotal();
	}

	@Override
	public YongHu findYongHuZCId(int yhzcid) {
		return yongHuMapper.getYongHuInfo(yhzcid);
	}

	@Override
	public int updateYungHuInfo(YongHu yongHu) {
		return yongHuMapper.updateYongHu(yongHu);
	}

	@Override
	public int addYungHuInfo(YongHu yongHu) {
		return yongHuMapper.addYongHu(yongHu);
	}
	
	@Override
	public List<YHBean> findYongHuByBean(YHBean yhBean) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", yhBean.getPage()*yhBean.getRows());
		params.put("pageSize", (yhBean.getPage()-1)*yhBean.getRows());
		return yongHuMapper.findYongHuByBean(yhBean);
	}

	
	@Override
	public YongHu getYongHuInfo(int yhzcid) {
		return yongHuMapper.selectYongHuInfo(yhzcid);
	}

}
