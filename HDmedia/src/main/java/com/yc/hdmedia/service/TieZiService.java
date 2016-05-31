package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.TieZi;

/**
 * 帖子突然一天
 * @author Administrator
 *
 */
public interface TieZiService {
	/**
	 * 添加
	 * @param TieZiDao
	 * @return
	 */
	public int addTieZi(TieZi tieZi);
	/**
	 * 修改
	 * @param TieZiDao
	 * @return
	 */
	public int updateTieZi(TieZi tieZi);
	/**
	 * 删除
	 * @param TieZiDao
	 * @return
	 */
	public int delTieZi(String tid);
	/**
	 * 信息总记录数
	 * @param TieZiDao
	 * @return
	 */
	public int total();
	/**
	 * 分页查询作者信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<TieZi> find(Integer pageNo,Integer pageSize);
	
	public List<TieZi> find(int tid);
	public List<TieZi> findTZ();
	public List<TieZi> findslcw(int ltid);
	public List<TieZi> findstzlcw(int tid);
	
	public TieZi findshow(int ltid);
}
