package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.Works;
import com.yc.hdmedia.entity.WorksBean;
import com.yc.hdmedia.entity.WorksType;

@Service("worksMapper")
public interface WorksMapper {
	
	public int addWorks(Works works);
	
	public int updateWorks(Works works);
	
	public int delWorks(String works_id);
	
	public int total();
	
	public List<Works> findAllWorks(Map<String,Object> params);
	public List<WorksType> findAllType();
	
	public List<Works> finds();
	public List<Works>  findById(int works_id);	
	
	
	/**
	 * 查询首页的文化资讯
	 * @return
	 */
	public List<Works> findIndex();
	
	
	
	public List<Works> findWorks();
	
	public List<Works> findWork_gdsf();
	public List<Works> findWork_gdhh_left();
	public List<Works> findWork_gdhh_rigth();
	public List<Works> findWork_gdhh_center();
	
	public List<Works> findWork_jxdsh_left();
	public List<Works> findWork_jxdsh_rigth();
	
	public List<Works> findWork_xdsh_left();
	public List<Works> findWork_xdsh_rigth();

	
	public List<Works> findWork();
	
	//查询书画首页的信息
	List<WorksBean> findAllWorks();
	//查询古代绘画
	List<WorksBean> findWork_gdhh();
	//查询近现代书画
	List<WorksBean> findWork_jxdsh();
	//查询现代书画
	List<WorksBean> findWork_xdsh();
	//插叙油画雕塑
	List<WorksBean> findWork_yhds();
	//查询评选信息
	List<WorksBean> findWork_px();
	//查询轮播图片
	List<WorksBean> findWork_lb();
}