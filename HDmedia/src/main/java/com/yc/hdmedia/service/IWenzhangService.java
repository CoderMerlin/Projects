package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.Wenzhang;
import com.yc.hdmedia.entity.WenzhangType;


public interface IWenzhangService {
	/**
	 * 添加作品信息
	 * @param wenzhang 添加的作品
	 * @return
	 */
	public int addWenzhang(Wenzhang wenzhang);
	
	/**
	 * 修改作品信息
	 * @param wenzhang 要修改的作品
	 * @return
	 */
	public int updateWenzhang(Wenzhang wenzhang);

	
	/**
	 * 删除作品
	 * @param wzid 删除作品的ID
	 * @return
	 */
	public int del(String wzid);
	
	/**
	 * 总记录数
	 * @return
	 */
	public int total();
	
	/**
	 * 分页查询作品信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<Wenzhang> find(Integer pageNo,Integer pageSize);

	/**
	 * 查询指定的作品信息
	 * @param aid：要查询的作品编号
	 * @return
	 */
	public List<Wenzhang>  findById(int wzid);
	
	/**
	 * 查询所有的文章标题
	 * @return
	 */
	public List<Wenzhang> finds();
	
}
