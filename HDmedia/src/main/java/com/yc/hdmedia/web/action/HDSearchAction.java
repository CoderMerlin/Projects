package com.yc.hdmedia.web.action;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;
import org.springframework.beans.factory.annotation.Autowired;

import com.yc.hdmedia.entity.HDSearch;
import com.yc.hdmedia.service.HDSearchService;


public class HDSearchAction implements SessionAware{
	
	private Map<String, Object> session;
	@Autowired
	private HDSearchService hDSearchService;
	private String content;
	
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * 根据内容搜索相关帖子
	 * @return
	 */
	public String getTeiZiByContent(){
		List<HDSearch> searchs=hDSearchService.getTeiZiByContent(content);
		return "fail";
	}
	
	@Override
	public void setSession(Map<String, Object> session) {
		this.session=session;
	}

}
