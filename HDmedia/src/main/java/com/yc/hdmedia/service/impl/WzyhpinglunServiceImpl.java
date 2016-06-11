package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.Wzyhpinglun;
import com.yc.hdmedia.mapper.IWzyhpinglunMapper;
import com.yc.hdmedia.service.IWzyhpinglunService;


@Service("iWzyhpinglunService")
public class WzyhpinglunServiceImpl implements IWzyhpinglunService {
	
	@Autowired
	private IWzyhpinglunMapper iWzyhpinglunMapper;
	public int addWzyhpinglun(Wzyhpinglun wzyhpinglun) {
		return iWzyhpinglunMapper.addWzyhpinglun(wzyhpinglun);
	}

	public int updateWzyhpinglun(Wzyhpinglun wzyhpinglun){
		return iWzyhpinglunMapper.updateWzyhpinglun(wzyhpinglun);
	}

	public int del(String wzplids) {
		int result=0;
		if(wzplids.contains(",")){
			String[] str=wzplids.split(",");
			for(String wzplid:str){
				result+=iWzyhpinglunMapper.del(wzplid);
			}
		}else{
			String wzplid=wzplids;
			result+=iWzyhpinglunMapper.del(wzplid);
		}
		return result;
	}

	public int total() {
		return iWzyhpinglunMapper.total();
	}

	public List<Wzyhpinglun> findAllPingLun(Integer pageNo, Integer pageSize) {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("pageNo",pageNo*pageSize);
		params.put("pageSize",(pageNo-1)*pageSize);
		return iWzyhpinglunMapper.findAllPingLun(params);
	}
	public List<Wzyhpinglun> findById(int wzplid) {
		return iWzyhpinglunMapper.findById(wzplid);
	}

}
