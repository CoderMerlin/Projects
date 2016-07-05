package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.Dietary;
import com.yc.hdmedia.entity.DietaryType;
import com.yc.hdmedia.mapper.DietaryMapper;
import com.yc.hdmedia.service.DietaryService;

@Service("dietaryService")
public class DietaryServiceImpl implements DietaryService {
	
	@Autowired
	private DietaryMapper dietaryMapper;
	@Override
	public int addDietary(Dietary dietary) {
		System.out.println("实现=》"+dietary);
		return dietaryMapper.addDietary(dietary);
	}

	@Override
	public int updateDietary(Dietary dietary) {
		return dietaryMapper.updateDietary(dietary);
	}

	@Override
	public int delDietary(String dtids) {
		int result=0;
		if(dtids.contains(",")){
			String[] str=dtids.split(",");
			for(String dtid:str){
				result+=dietaryMapper.delDietary(dtid);
			}
		}else{
			String dtid=dtids;
			result=dietaryMapper.delDietary(dtid);
		}
		return result;
	}

	@Override
	public int total() {
		return dietaryMapper.total();
	}

	@Override
	public List<Dietary> findAll(Integer pageNo, Integer pageSize) {
		System.out.println(pageNo+"============="+pageSize);
		Map<String , Object> params=new HashMap<String , Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return dietaryMapper.findAll(params);
	}

	@Override
	public List<Dietary> findById(int dtid) {
		return dietaryMapper.findById(dtid);
	}

	@Override
	public List<Dietary> findcaixi() {
		return null;
	}

	@Override
	public List<Dietary> findfoodstory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findfoodmeitu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findtea() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findteastory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findteameitu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findteatext() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findjiulishi() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findjiumeitu() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Dietary> findjiustory() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<DietaryType> findAllType() {
		return dietaryMapper.findAllType();
	}

}
