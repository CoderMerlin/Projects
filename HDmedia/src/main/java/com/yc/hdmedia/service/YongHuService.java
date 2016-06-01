package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.YHBean;
import com.yc.hdmedia.entity.YongHu;


public interface YongHuService {

	List<YongHu> findAllYongHu(int page, int rows);

	int total();

	YongHu findYongHuZCId(int yhzcid);

	int updateYungHuInfo(YongHu yongHu);

	int addYungHuInfo(YongHu yongHu);

	List<YHBean> findYongHuByBean(YHBean yhBean);

}
