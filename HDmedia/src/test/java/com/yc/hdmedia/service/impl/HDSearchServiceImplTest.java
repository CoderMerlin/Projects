package com.yc.hdmedia.service.impl;


import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.hdmedia.entity.HDSearch;
import com.yc.hdmedia.service.HDSearchService;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class HDSearchServiceImplTest {
	@Autowired
	private HDSearchService hDSearchService;
	@Test
	public void testGetTeiZiByContent() {
		List<HDSearch> searchs=hDSearchService.getTeiZiByContent("Java");
		
		System.out.println("收到的内容"+searchs);
	}

}
