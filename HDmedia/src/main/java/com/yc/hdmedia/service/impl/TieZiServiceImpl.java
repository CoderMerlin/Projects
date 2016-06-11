package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.TieZi;
import com.yc.hdmedia.entity.TieZiBean;
import com.yc.hdmedia.mapper.TieZiMapper;
import com.yc.hdmedia.service.TieZiService;

/**
 * 帖子太热太热
 * @author Administrator
 *
 */
@Service("tieZiService")
public class TieZiServiceImpl implements TieZiService {

	@Autowired
	private TieZiMapper tieZiMapper;
	@Override
	public int addTieZi(TieZi tieZi) {
		return tieZiMapper.addTieZi(tieZi);
	}
	@Override
	public int updateTieZi(TieZi tieZi) {
		System.out.println("进入到更新帖子的实现类中!!!!!!");
		return tieZiMapper.updateTieZi(tieZi);
	}

	@Override
	public int delTieZi(String tids) {
		int result=0;
		if(tids.contains(",")){
			String[] str=tids.split(",");
			for(String tid:str){
				result+=tieZiMapper.delTieZi(Integer.parseInt(tid));
			}
		}else{
			int tid=Integer.parseInt(tids);
			result=tieZiMapper.delTieZi(tid);
		}
		return result;
	}

	@Override
	public int total() {
		return tieZiMapper.total();
	}

	@Override
	public List<TieZi> find(Integer pageNo, Integer pageSize) {
		Map<String , Object> params=new HashMap<String , Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return tieZiMapper.findAllTieZi(params);
	}

	@Override
	public List<TieZi> find(int tid) {
		return tieZiMapper.find(tid);
	}

	@Override
	public List<TieZi> findTZ() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TieZi> findslcw(int ltid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<TieZi> findstzlcw(int tid) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public TieZi findshow(int ltid) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public TieZiBean findCount() {
		return tieZiMapper.findCount();

	}

	@Override
	public List<TieZiBean> findById(int pageNo, int pageSize, int ltid) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		params.put("ltid",ltid);
		return tieZiMapper.findById(params);
	}

	@Override
	public TieZiBean findByIdCount(int ltid) {
		return tieZiMapper.findByIdCount(ltid);
	}

	@Override
	public TieZiBean findTerdayCount() {
		return tieZiMapper.findTerdayCount();
	}

	@Override
	public TieZiBean findYesterdayCount() {
		return tieZiMapper.findYesterdayCount();
	}

	@Override
	public TieZiBean findByTids(int tid) {
		return tieZiMapper.findByTids(tid);
	}
	
	@Override
	public int addTieZis(int ltid, int yhid, String tzname, String tzzy,String tztext,String tzphoto) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("ltid", ltid);
		params.put("yhid", yhid);
		params.put("tzname", tzname);
		params.put("tzzy", tzzy);
		params.put("tztext", tztext);
		params.put("tzphoto", tzphoto);
		return tieZiMapper.addTieZis(params);
	}
	
}
