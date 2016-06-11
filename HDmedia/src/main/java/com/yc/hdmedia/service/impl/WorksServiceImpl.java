package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.Works;
import com.yc.hdmedia.entity.WorksBean;
import com.yc.hdmedia.entity.WorksType;
import com.yc.hdmedia.mapper.WorksMapper;
import com.yc.hdmedia.service.WorksService;

@Service("worksService")
public class WorksServiceImpl implements WorksService {
	
	@Autowired
	private WorksMapper worksMapper;
	@Override
	public int addWorks(Works works) {
		return worksMapper.addWorks(works);
	}

	@Override
	public int updateWorks(Works works) {
		return worksMapper.updateWorks(works);
	}

	@Override
	public int delWorks(String works_ids) {
		int result=0;
		if(works_ids.contains(",")){
			String[] str=works_ids.split(",");
			for(String works_id:str){
				result+=worksMapper.delWorks(works_id);
			}
		}else{
			String works_id=works_ids;
			result=worksMapper.delWorks(works_id);
		}
		return result;
	}

	@Override
	public int total() {
		return worksMapper.total();
	}

	@Override
	public List<Works> findAllWorks(Integer pageNo, Integer pageSize) {
		Map<String , Object> params=new HashMap<String , Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return worksMapper.findAllWorks(params);
	}

	@Override
	public List<WorksType> findAllType() {
		return worksMapper.findAllType();
	}

	@Override
	public List<Works> finds() {
		return null;
	}

	@Override
	public List<Works>  findById(int works_id) {
		return worksMapper.findById(works_id);
	}

	@Override
	public List<Works> findIndex() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWorks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_gdsf() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_gdhh_left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_gdhh_rigth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_gdhh_center() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_jxdsh_left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_jxdsh_rigth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_xdsh_left() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_xdsh_rigth() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_yhds() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_px() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork_lb() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Works> findWork() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorksBean> findAllWorks() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorksBean> findWork_gdhh() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorksBean> findWork_jxdsh() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<WorksBean> findWork_xdsh() {
		// TODO Auto-generated method stub
		return null;
	}

}
