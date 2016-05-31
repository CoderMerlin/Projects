package com.yc.hdmedia.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.LunTan;
import com.yc.hdmedia.mapper.LunTanMapper;
import com.yc.hdmedia.service.ILunTanService;

/**
 * 论坛
 * @autho天天人员r Administrator
 *
 */
@Service("iLunTanService")
public class LunTanServiceImpl implements ILunTanService {
	
	@Autowired
	private LunTanMapper lunTanMapper;
	
	/**
	 * 添加论坛栏目类型
	 */
	public int addLunTan(LunTan luntan) {
		return lunTanMapper.addLunTan(luntan);
	}

	/**
	 * 删除论坛类型
	 */
	public int delLunTan(String ltids) {
		int result=0;
		if(ltids.contains(",")){
			String[] str=ltids.split(",");
			for(String ltid:str){
				result+=lunTanMapper.delLunTan(Integer.parseInt(ltid));
			}
		}else{
			int ltid=Integer.parseInt(ltids);
			result=lunTanMapper.delLunTan(ltid);
		}
		return result;
	}

	/**
	 * 更新论坛的
	 */
	public int updateLunTan(LunTan luntan) {
		System.out.println(luntan);
		int result=lunTanMapper.updateLunTan(luntan);
		System.out.println("进入修改的实现类中====>"+result);
		return result;
	}

	/**
	 * 查询总记录数
	 */
	public int total() {
		return lunTanMapper.total();
	}

	/**
	 * 分页查询
	 */
	public List<LunTan> find(Integer pageNo, Integer pageSize) {
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("pageNo", pageNo*pageSize);
		params.put("pageSize", (pageNo-1)*pageSize);
		return lunTanMapper.find(params);
	}

	public List<LunTan> finds() {
		return lunTanMapper.finds();
	}
	
	
	/**
	 * 添加论坛图片
	 */
	public int addLtPic(LunTan luntan) {
		return lunTanMapper.addLtPic(luntan);
	}

	//查看论坛图片
	public LunTan findltPic(int ltid) {
		return lunTanMapper.findltPic(ltid);
	}



}
