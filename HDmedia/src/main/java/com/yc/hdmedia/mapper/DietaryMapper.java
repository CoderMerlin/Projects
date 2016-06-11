package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.Dietary;
import com.yc.hdmedia.entity.DietaryType;

@Service("dietaryMapper")
public interface DietaryMapper {
	/**
	 * 添加
	 * @param DietaryDao
	 * @return
	 */
	public int addDietary(Dietary dietary);
	/**
	 * 修改
	 * @param DietaryDao
	 * @return
	 */
	public int updateDietary(Dietary dietary);
	/**
	 * 删除
	 * @param DietaryDao
	 * @return
	 */
	public int delDietary(String dtid);
	/**
	 * 信息总记录数
	 * @param DietaryDao
	 * @return
	 */
	public int total();
	/**
	 * 分页查询作者信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<Dietary> findAll(Map<String,Object> params);
	
	public List<Dietary> findById(int dtid);
	
	public List<Dietary> findcaixi();
	public List<Dietary> findfoodstory();
	public List<Dietary> findfoodmeitu();
	public List<Dietary> findtea();
	public List<Dietary> findteastory();
	public List<Dietary> findteameitu();
	public List<Dietary> findteatext();
	public List<Dietary> findjiulishi();
	public List<Dietary> findjiumeitu();
	public List<Dietary> findjiustory();
	
	public List<DietaryType> findAllType();
	
}
