package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.HuiTie;
import com.yc.hdmedia.entity.HuitieBean;
import com.yc.hdmedia.mapper.HuiTieMapper;
import com.yc.hdmedia.service.HuiTieService;

/**
 * 回帖天热液体
 * @author Administrator
 *
 */
@Service("huiTieService")
public class HuiTieServiceImpl implements HuiTieService {
	@Autowired
	private HuiTieMapper huiTieMapper;
	
	public int addHuiTie(HuiTie huiTie) {
		return huiTieMapper.addHuiTie(huiTie);
	}

	public int updateHuiTie(HuiTie huiTie) {
		return huiTieMapper.updateHuiTie(huiTie);
	}

	public int delHuiTie(String htids) {
		int result=0;
		if(htids.contains(",")){
			String[] str=htids.split(",");
			for(String htid:str){
				result+=huiTieMapper.delHuiTie(Integer.parseInt(htid));
			}
		}else{
			int htid=Integer.parseInt(htids);
			result=huiTieMapper.delHuiTie(htid);
		}
		return result;
	}

	public int totalByTid(int tid) {
		return huiTieMapper.totalByTid(tid);
	}

	
	/*//fenyechaxun
	public List<HuiTie> find(Integer tid,Integer pageNo, Integer pageSize) {
		System.out.println(pageNo);
		System.out.println(pageSize);
		String sql="select * from (select a.*,rownum rn from (select ht.*,tz.tzname,yh.yhzsname,yh.yhphoto from huitie ht,yonghu yh,tiezi tz where ht.yhid=yh.yhid and ht.tid=tz.tid and tz.tid=? and ht.htstatus=1) a where rownum<=?)b where rn>?";
		List<Object> params=new ArrayList<Object>();
		params.add(tid);
		params.add(pageNo*pageSize);
		params.add((pageNo-1)*pageSize);
		return db.find(sql, params, HuiTie.class);
	}*/
	
	@Override
	public List<HuiTie> findAllHuiTie(int pageNo, int pageSize) {
		Map<String , Object> params=new HashMap<String , Object> ();
		params.put("pageNo",pageNo*pageSize);
		params.put("pageSize",(pageNo-1)*pageSize);
		return huiTieMapper.findAllHuiTie(params);
	}

	public List<HuiTie> find(int htid) {
		return huiTieMapper.find(htid);
	}

	public List<HuiTie> findslcw() {
		String sql="select * from huitie ht,";
		return null;
	}
	
	@Override
	public HuitieBean HuitieCount(int tid) {
		return huiTieMapper.HuitieCount(tid);
	}

	@Override
	public List<HuitieBean> findHuitieBean(int pageNo, int pageSize, int tid) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		params.put("tid",tid);		
		return huiTieMapper.findHuitieBean(params);
	}

	@Override
	public int addHuities(int tid, int yhid, String httext) {
		Map<String , Object> params=new HashMap<String , Object> ();
		params.put("tid", tid);
		params.put("yhid", yhid);
		params.put("httext", httext);
		return huiTieMapper.addHuities(params);
	}

	@Override
	public int total() {
		return huiTieMapper.total();
	}

	@Override
	public List<HuiTie> findHT() {
		return null;
	}

	@Override
	public int updateDianzan(int htid) {
		return huiTieMapper.updateDianzan(htid);
	}

	@Override
	public HuitieBean findByHtids(int htid) {
		return huiTieMapper.findByHtids(htid);
	}

	

}
