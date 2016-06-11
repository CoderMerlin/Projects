package com.yc.hdmedia.mapper;

import java.util.List;

import com.yc.hdmedia.entity.HDIndexBean;



/**
 * 首页页面信息实现接口
 * @author HM
 *
 */
public interface HDIndexMapper {

	

	/**
	 * 首页获取所有信息
	 * @author HM
	 * @return
	 */
	List<HDIndexBean> selectIndexAllInfo();

}
