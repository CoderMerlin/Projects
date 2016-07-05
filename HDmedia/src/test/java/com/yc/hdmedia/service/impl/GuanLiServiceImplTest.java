package com.yc.hdmedia.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.hdmedia.entity.Dietary;
import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.service.DietaryService;
import com.yc.hdmedia.service.GuanLiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GuanLiServiceImplTest {
	
	@Autowired
	private GuanLiService guanLiService;
	
	@Autowired
	private DietaryService dietaryService;
	@Test
	public void testFindAllGuanLis() {
		List<GuanLi> guanLis=guanLiService.findAllGuanLis(1,10);
		LogManager.getLogger().debug("查询所有的管理员列表==>"+guanLis);
		System.out.println("张艳容修改成功");
	}
	
	@Test
	public void testDt() {
		List<Dietary> dd=dietaryService.findAll(1,10);
		int result=dietaryService.total();
		LogManager.getLogger().debug("查询所有的管理员列表==>"+dd+"========"+result);
		System.out.println("张艳容修改成功");
	}
	
	
	@Test
	public void testgetoracleGlInfoToExcel() {
		List<GuanLi> guanlis=guanLiService.getoracleGlInfoToExcel("1001,1002");
		System.out.println("管理"+guanlis);
	}

}
