package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.LunTan;


/**
 * 论坛
 * @author A也太容易dministrator
 *
 */
public interface ILunTanService {
	
	
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
	public int addLtPic(LunTan luntan);
	
	/**
	 * 通过论坛id删除论坛的相应栏目 可以同时删除多个
	 * @param ltid
	 * @return
	 */
	public int delLunTan(String ltids);
	
	
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
	public List<LunTan> find(Integer pageNo,Integer pageSize);
	
	
	public List<LunTan> finds();
	
	/**
	 * 查找论坛图片
	 * @param ltid
	 * @return
	 */
	public LunTan findltPic(int ltid);
	
//	/**
//	 * 修改论坛图片
//	 * @param luntan
//	 * @return
//	 */
//	public int updateLtPic(LunTan luntan);
	
}
