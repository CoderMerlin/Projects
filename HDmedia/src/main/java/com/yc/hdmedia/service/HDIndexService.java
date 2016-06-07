package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.HDIndexBean;


/**
 * 弘道首页接口
 * @author HM
 *
 */
public interface HDIndexService {

	
	/**
	 * 首页获取所有信息
	 * @return
	 */
	List<HDIndexBean> getIndexAllInfo();
	
}
