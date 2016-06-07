package com.yc.hdmedia.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.hdmedia.entity.GongGao;
import com.yc.hdmedia.service.GongGaoService;
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GongGaoServiceImpTest {
	@Autowired
	private GongGaoService gongGaoService;
	@Test
	public void testAddGongGao() {
		
		int result = gongGaoService.addGongGao(new GongGao(1,"sadadadaa","dadadada","2016-06-05","1","",""));
		
	}

}
