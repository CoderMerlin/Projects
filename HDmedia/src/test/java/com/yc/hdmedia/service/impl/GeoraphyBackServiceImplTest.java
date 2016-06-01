package com.yc.hdmedia.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.hdmedia.entity.PropersonBack;
import com.yc.hdmedia.service.GeoraphyBackService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GeoraphyBackServiceImplTest {

	@Autowired
	private GeoraphyBackService georaphyBackService;
	
	
	@Test
	public void testAddPersonInfo() {
		int result=georaphyBackService.addPersonInfo(new PropersonBack(1,1001,"aaaa","dada","not",1));
		System.out.println(result);
	}

}
