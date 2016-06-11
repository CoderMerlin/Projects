package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.GongGaoBean;
import com.yc.hdmedia.entity.HDSearch;
import com.yc.hdmedia.entity.TieZi;
import com.yc.hdmedia.entity.TieZiBean;

/**
 * 帖子法国代购
 * @author Administrator
 *
 */
public interface TieZiMapper {
	
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
	public int delTieZi(int tid);
	
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
	public List<TieZi> findAllTieZi(Map<String , Object> params);
	
	public List<TieZi> find(int tid);
	public List<TieZi> findTZ();
	public List<TieZi> findslcw(int ltid);
	public List<TieZi> findstzlcw(int tid);
	
	public TieZi findshow(int ltid);

	public List<HDSearch> selectTeiZiByContent(String content);
	
	TieZiBean findCount();
	
	List<GongGaoBean> findGongGao();

	List<TieZiBean> findById(Map<String, Object> params);

	TieZiBean findByIdCount(int ltid);

	TieZiBean findTerdayCount();

	TieZiBean findYesterdayCount();

	TieZiBean findByTids(int tid);

	public int addTieZis(Map<String, Object> params);

}
