package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.HDSearch;


/**
 * 弘道搜索接口
 * @author HM
 *
 */
public interface HDSearchService {

	/**
	 * 根据内容搜索相关帖子
	 * @param content 
	 * @return
	 */
	public List<HDSearch> getTeiZiByContent(String content);
	
	
}
