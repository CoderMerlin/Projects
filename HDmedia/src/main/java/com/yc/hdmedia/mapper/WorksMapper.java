package com.yc.hdmedia.mapper;

import java.util.List;

import com.yc.hdmedia.entity.WorksBean;

public interface WorksMapper {
	//查询书画首页的信息
	List<WorksBean> findAllWorks();
	//查询古代书法
	List<WorksBean> findWork_gdsf();
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
