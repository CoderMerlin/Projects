package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.TieZi;
import com.yc.hdmedia.entity.TieZiBean;

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
	
/*=================*/
	
	/**
	 * 查询帖子总数
	 * @return
	 */
	TieZiBean findCount();
	
	/**
	 * 根据论坛ID分页查询帖子信息
	 * @param pageNo :要查询的页数，如果为null，则查询全部
	 * @param pageSize:页面显示的条数
	 * @param ltid:所查询的论坛ID
	 * @return
	 */
	List<TieZiBean> findById(int pageNo,int pageSize,int ltid);
	
	/**
	 * 根据论坛ID查询帖子总数
	 * @param ltid:所查询的论坛ID
	 * @return
	 */
	TieZiBean findByIdCount(int ltid);
	
	/**
	 * 查询帖子今日总数
	 * @return
	 */
	TieZiBean findTerdayCount();
	/**
	 * 查询帖子昨天日总数
	 * @return
	 */
	TieZiBean findYesterdayCount();
	
	/**
	 * 根据ID查询帖子数据
	 * @param tid 帖子ID
	 * @return
	 */
	TieZiBean findByTids(int tid);
	
	int addTieZis(int ltid,int yhid,String tzname,String tzzy,String tztext,String tzphoto);
}
