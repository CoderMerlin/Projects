package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.entity.YongHuZC;

public interface YongHuZCMapper {

	List<YongHuZC> getAllYongHuZC(Map<String, Object> params);

	int  selectTotal();

	int addYongHuZC(YongHuZC yongHuZC);

	int deleteYongHuZCById(int[] yhzcids);

	int updateYongHuZCById(YongHuZC yongHuZC);

	YongHuZC getYongHuZCById(int yhzcid);
	
}
