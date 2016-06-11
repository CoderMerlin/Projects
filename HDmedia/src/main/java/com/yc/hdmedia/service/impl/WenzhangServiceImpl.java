package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.yc.hdmedia.entity.Wenzhang;
import com.yc.hdmedia.mapper.WenzhangMapper;
import com.yc.hdmedia.service.IWenzhangService;

@Service("iWenzhangService")
public class WenzhangServiceImpl implements IWenzhangService {
	
	@Autowired
	private WenzhangMapper wenzhangMapper;
	public int addWenzhang(Wenzhang wenzhang) {
		return wenzhangMapper.addWenzhang(wenzhang);
	}

	public int updateWenzhang(Wenzhang wenzhang) {
		return wenzhangMapper.updateWenzhang(wenzhang);
	}

	public int del(String wzids) {
		int result=0;
		if(wzids.contains(",")){
			String[] str=wzids.split(",");
			for(String  wzid:str){
				result+=wenzhangMapper.del(wzid);
			}
		}else{
			String wzid=wzids;
			result=wenzhangMapper.del(wzid);
		}
		return result;
	}

	public int total() {
		return wenzhangMapper.total();
	}

	public List<Wenzhang> find(Integer pageNo, Integer pageSize) {
		Map<String , Object> params=new HashMap<String , Object>();
		params.put("pageNo",pageNo*pageSize);
		params.put("pageSize",(pageNo-1)*pageSize);
		return wenzhangMapper.findAllWenZhang(params);
	}

	public List<Wenzhang> findById(int wzid) {
		return wenzhangMapper.findById(wzid);
	}

	public List<Wenzhang> finds() {
		return wenzhangMapper.finds();
	}

}
