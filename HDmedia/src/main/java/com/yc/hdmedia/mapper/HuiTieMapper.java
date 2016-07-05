package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.HuiTie;
import com.yc.hdmedia.entity.HuitieBean;

/**
 * 回帖Mapper的撒伐 
 * @author Administrator
 *
 */
@Service("huiTieMapper")
public interface HuiTieMapper {
	
	/**
	 * 添加
	 * @param HuiTieDao
	 * @return
	 */
	public int addHuiTie(HuiTie huiTie);
	/**
	 * 修改
	 * @param HuiTieDao
	 * @return
	 */
	public int updateHuiTie(HuiTie huiTie);
	/**
	 * 删除
	 * @param HuiTieDao
	 * @return
	 */
	public int delHuiTie(int htid);
	/**
	 * 信息总记录数
	 * @param HuiTieDao
	 * @return
	 */
	public int totalByTid(int tid);
	public int total();
	/**
	 * 分页查询作者信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<HuiTie> findAllHuiTie(Map<String , Object> params);
	
	public List<HuiTie> find(int htid);
	
	public List<HuiTie> findHT();
	
	public List<HuiTie> findslcw();
	
	public HuitieBean HuitieCount(int tid);
	
	public List<HuitieBean> findHuitieBean(Map<String, Object> params);
	public int addHuities(Map<String, Object> params);
	
	public int updateDianzan(int htid);
	public HuitieBean findByHtids(int htid);
}
