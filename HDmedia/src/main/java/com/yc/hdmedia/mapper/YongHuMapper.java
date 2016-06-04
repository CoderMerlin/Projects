package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.entity.YHBean;
import com.yc.hdmedia.entity.YongHu;

public interface YongHuMapper {

	List<YongHu> getAllYongHu(Map<String,Object> params);
	int getTotal();
	YongHu getYongHuInfo(int yhzcid);
	int updateYongHu(YongHu yongHu);
	int addYongHu(YongHu yongHu);
	List<YHBean> findYongHuByBean(YHBean yhBean);
	

	/**
	 * 前台获取用户信息
	 * @param yhzcid
	 * @return
	 * @author HM
	 */
	YongHu selectYongHuInfo(int yhzcid);

}
