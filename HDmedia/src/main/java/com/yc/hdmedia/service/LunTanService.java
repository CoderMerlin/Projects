package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.LunTanBean;

public interface LunTanService {
	//论坛首页查询
	List<LunTanBean> findLunTan_jlq();
	List<LunTanBean> findLunTan_whjlq();
	List<LunTanBean> findLunTan_zyfx();
	List<LunTanBean> findLunTan_zzhz();
	List<LunTanBean> findLunTan();
	
}
