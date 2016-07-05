package com.yc.hdmedia.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.hdmedia.entity.TieZiBean;
import com.yc.hdmedia.service.TieZiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class TieZiServiceImplTest {

	
	@Autowired
	private TieZiService tieZiService;
	@Test
	public void testFindByTids() {
		
		TieZiBean TZ= tieZiService.findByTids(1022);
		System.out.println("帖子信息"+TZ);
	}

}
