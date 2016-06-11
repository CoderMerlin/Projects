package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.WenzhangType;


public interface IWenzhangTypeService {
	/**
	 * 添加文章类型信息
	 * @param Wenzhangtype：要添加的文章类型
	 * @return
	 */
	public int addWenzhangtype(WenzhangType wenzhangtype);
	
	/**
	 * 修改文章类型
	 * @param Wenzhangtype：要修改的文章类型
	 * @return
	 */
	public int update(WenzhangType wenzhangtype);
	
	/**
	 * 删除文章类型
	 * @param tid：要删除的文章类型编号，如果要同时删除多个，则用逗号隔开
	 * @return
	 */
	public int del(String wztypeids);
	
	/**
	 * 总记录数
	 * @return
	 */
	public int total();
	
	/**
	 * 分页查询文章类型信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<WenzhangType> find(Integer pageNo,Integer pageSize);
	
	/**
	 * 查询指定的文章类型信息
	 * @param tid：要查询的文章类型编号
	 * @return
	 */
	public WenzhangType find(int wztypeid);
	
	/**
	 * 查询所有文章类型
	 * @return
	 */
	public List<WenzhangType> finds();


}
