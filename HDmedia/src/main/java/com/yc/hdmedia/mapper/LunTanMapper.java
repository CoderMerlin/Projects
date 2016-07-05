package com.yc.hdmedia.mapper;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.yc.hdmedia.entity.LunTan;
import com.yc.hdmedia.entity.LunTanBean;

/**
 * 论坛
 * @auth大富大贵or Administrator
 *
 */
@Service("lunTanMapper")
public interface LunTanMapper {

	/**
	 * 添加论坛类的栏目
	 * @param luntan
	 * @return
	 */
	public int addLunTan(LunTan luntan);
	
	/**
	 * 添加论坛图片 也可以修改
	 * @param luntan
	 * @return
	 */
	public int addLtPic(Map<String , Object> params);
	
	/**
	 * 通过论坛id删除论坛的相应栏目 可以同时删除多个
	 * @param ltid
	 * @return
	 */
	public int delLunTan(int ltid);
	
	
	/**
	 * 更新论坛栏目的内容
	 * @param luntan
	 * @return
	 */
	public int updateLunTan(LunTan luntan);

	/**
	 * 总记录数
	 * @return
	 */
	public int total();
	
	
	/**
	 * 分页查询论坛栏目
	 * @param pageNo：要查询的页数，如果为null,则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<LunTan> find(Map<String , Object> params);
	
	public List<LunTan> findById(String ltid);
	public List<LunTan> finds();
	
	/**
	 * 查找论坛图片
	 * @param ltid
	 * @return
	 */
	public LunTan findltPic(int ltid);
	
	
	//查询论坛首页
			List<LunTanBean> findLunTan_jlq();

			List<LunTanBean> findLunTan_whjlq();
				
			List<LunTanBean> findLunTan_zyfx();
			List<LunTanBean> findLunTan_zzhz();

			List<LunTanBean> findLunTan();
}
