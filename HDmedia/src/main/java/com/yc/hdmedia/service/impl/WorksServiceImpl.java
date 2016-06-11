package com.yc.hdmedia.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.WorksBean;
import com.yc.hdmedia.mapper.WorksMapper;
import com.yc.hdmedia.service.WorksService;

@Service("worksService")
public class WorksServiceImpl implements WorksService {
	@Autowired
	public WorksMapper worksMapper;

	@Override
	public List<WorksBean> findAllWorks() {
		return worksMapper.findAllWorks();
	}

	@Override
	public List<WorksBean> findWork_gdsf() {
		return worksMapper.findWork_gdsf();
	}
	
	@Override
	public List<WorksBean> findWork_gdhh() {
		return worksMapper.findWork_gdhh();
	}
	
	@Override
	public List<WorksBean> findWork_jxdsh() {
		return worksMapper.findWork_jxdsh();
	}

	@Override
	public List<WorksBean> findWork_xdsh() {
		return worksMapper.findWork_xdsh();

	}

	@Override
	public List<WorksBean> findWork_yhds() {
		return worksMapper.findWork_yhds();

	}

	@Override
	public List<WorksBean> findWork_px() {
		return worksMapper.findWork_px();
	}

	@Override
	public List<WorksBean> findWork_lb() {
		return worksMapper.findWork_lb();
	}

	


	

	
	

}
