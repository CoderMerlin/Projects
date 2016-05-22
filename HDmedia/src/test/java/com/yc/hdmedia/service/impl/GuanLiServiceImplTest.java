package com.yc.hdmedia.service.impl;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.service.GuanLiService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:spring.xml")
public class GuanLiServiceImplTest {
	
	@Autowired
	private GuanLiService guanLiService;
	@Test
	public void testFindAllGuanLis() {
		List<GuanLi> guanLis=guanLiService.findAllGuanLis(1,10);
		LogManager.getLogger().debug("查询所有的管理员列表==>"+guanLis);
		System.out.println("张艳容修改成功");
	}

}
