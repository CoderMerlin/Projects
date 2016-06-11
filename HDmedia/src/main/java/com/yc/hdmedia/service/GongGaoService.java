package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.GongGao;

public interface GongGaoService {
	public int addGongGao(GongGao gonggao); //添加公告
	
	public int updateGongGao(GongGao gonggao); //修改公告
		
	public int del(int[] gid);//删除
	
	public int total();//总记录数
	
	/**
	 * 分页查询公告信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<GongGao> getAllGongGao(int pageNo,int pageSize);
	
	/**
	 * 查询指定的公告信息
	 * @param gid：要查询的公告编号
	 * @return
	 */
	public GongGao findGongGaoByGid(int gid);
}
