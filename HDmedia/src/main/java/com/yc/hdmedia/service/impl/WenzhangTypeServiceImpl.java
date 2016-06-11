package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.WenzhangType;
import com.yc.hdmedia.mapper.WenZhangTypeMapper;
import com.yc.hdmedia.service.IWenzhangTypeService;


@Service("iWenzhangTypeService")
public class WenzhangTypeServiceImpl implements IWenzhangTypeService {
	
	@Autowired
	private WenZhangTypeMapper wenZhangTypeMapper;
	public int addWenzhangtype(WenzhangType wenzhangtype) {
		return wenZhangTypeMapper.addWenzhangtype(wenzhangtype);
	}

	public int update(WenzhangType wenzhangtype) {
		return wenZhangTypeMapper.update(wenzhangtype);
	}

	public int del(String wztypeids) {
		int result=0;
		if(wztypeids.contains(",")){
			String[] str=wztypeids.split(",");
			for(String wztypeid:str){
				result+=wenZhangTypeMapper.del(Integer.parseInt(wztypeid));
			}
		}else{
			int wztypeid=Integer.parseInt(wztypeids);
			result=wenZhangTypeMapper.del(wztypeid);
		}
		return result;	}

	public int total() {
		return wenZhangTypeMapper.total();
	}

	public List<WenzhangType> find(Integer pageNo, Integer pageSize) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo",pageNo*pageSize);
		params.put("pageSize",(pageNo-1)*pageSize);
		return wenZhangTypeMapper.findAllWzType(params);
	}

	public WenzhangType find(int wztypeid) {
		
		return wenZhangTypeMapper.findByWzId(wztypeid);
	}

	public List<WenzhangType> finds() {
		return wenZhangTypeMapper.findAllWz();
	}



}
