package com.yc.hdmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.HDSearch;
import com.yc.hdmedia.mapper.TieZiMapper;
import com.yc.hdmedia.service.HDSearchService;

@Service("hDSearchService")
public class HDSearchServiceImpl implements HDSearchService {
	
	@Autowired
	private TieZiMapper tieZiMapper;
	
	@Override
	public List<HDSearch> getTeiZiByContent(String content) {
		return tieZiMapper.selectTeiZiByContent(content);
	}

}
