package com.yc.hdmedia.service.impl;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.hdmedia.service.GeoraphyBackService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GeoraphyBackServiceImplTest {

	@Autowired
	private GeoraphyBackService georaphyBackService;
	
	
	@Test
	public void testGetAllPersonsByPrid() {
		
	}

}
