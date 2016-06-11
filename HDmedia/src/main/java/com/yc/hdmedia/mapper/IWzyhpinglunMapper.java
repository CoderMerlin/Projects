package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.Wzyhpinglun;

public interface IWzyhpinglunMapper {
	/**
	 * 添加评论信息
	 * @param wenzhang 添加的评论
	 * @return
	 */
	public int addWzyhpinglun(Wzyhpinglun wzyhpinglun);
	
	/**
	 * 修改评论信息
	 * @param wenzhang 要修改的评论
	 * @return
	 */
	public int updateWzyhpinglun(Wzyhpinglun wzyhpinglun);
	
	/**
	 * 删除评论
	 * @param wzid 删除评论的ID
	 * @return
	 */
	public int del(String wzplid);
	
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
	public List<Wzyhpinglun> findAllPingLun(Map<String ,Object> params);

	/**
	 * 查询指定的评论信息
	 * @param aid：要查询的评论编号
	 * @return
	 */
	public List<Wzyhpinglun> findById(int wzplid);
}	
