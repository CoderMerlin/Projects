package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.WorksBean;



public interface WorksService {
	//查询书画首页的信息
	public List<WorksBean> findAllWorks();
	public List<WorksBean> findWork_gdsf();
	public List<WorksBean> findWork_gdhh();
	public List<WorksBean> findWork_jxdsh();
	public List<WorksBean> findWork_xdsh();
	public List<WorksBean> findWork_yhds();
	public List<WorksBean> findWork_px();
	public List<WorksBean> findWork_lb();
}
