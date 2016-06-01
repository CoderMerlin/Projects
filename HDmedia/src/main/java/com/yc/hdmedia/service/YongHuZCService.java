package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.GuanLi;
import com.yc.hdmedia.entity.YongHuZC;

public interface YongHuZCService {

	public List<YongHuZC> findAllYongHuZC(int page, int rows);

	public int total();

	public int addYongHuZC(YongHuZC yongHuZC);

	public int updateYongHuZCInfo(YongHuZC yongHuZC);

	public YongHuZC getYongHuZCById(int yhzcid);

	public int delYongHuZCByIds(int[] yhzcids);
}
