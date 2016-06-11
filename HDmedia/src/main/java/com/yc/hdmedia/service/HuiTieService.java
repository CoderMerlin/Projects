package com.yc.hdmedia.service;
import java.util.List;

import com.yc.hdmedia.entity.HuiTie;
import com.yc.hdmedia.entity.HuitieBean;

//回帖Service工人体育
public interface HuiTieService {
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
	public int delHuiTie(String htids);
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
	/*public List<HuiTie> find(Integer tid,Integer pageNo,Integer pageSize);*/
	public List<HuiTie> findAllHuiTie(int pageNo,int pageSize);
	
	public List<HuiTie> find(int htid);
	
	public List<HuiTie> findHT();
	
	public List<HuiTie> findslcw();
	
	public HuitieBean HuitieCount(int tid);
	
	public List<HuitieBean> findHuitieBean(int pageNo,int pageSize,int tid);
}
