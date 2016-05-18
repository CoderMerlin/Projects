package com.yc.hdmedia.service;

import java.util.List;

import com.yc.hdmedia.entity.GuanLi;

public interface GuanLiService {

	/**
	 * 后台管理员登录
	 * @param glname :管理员账号
	 * @param glpwd :管理员密码
	 * @return
	 */
	public boolean GuanLiLogin(String glname, String glpwd);
	
	/**
	 * 添加管理员信息
	 * @param guanLi：要添加的管理员
	 * @return
	 */
	public boolean addGuanLi(GuanLi guanLi);
	
	/**
	 * 修改管理员信息
	 * @param guanLi
	 * @return
	 */
	public boolean alterGuanli(GuanLi guanLi);
	
	/**
	 * 密码修改
	 * @param glid：要修改的管理员编号
	 * @param oldGuanLiPwd：旧密码
	 * @param newGuanLiPwd：新密码
	 * @return
	 */
	public boolean alterGuanLipwd(int glid,String oldGuanLiPwd,String newGuanLiPwd);
	
	/**
	 * 删除管理员
	 * @param glid：要删除的管理员编号，如果要同时删除多个，则用逗号隔开
	 * @return
	 */
	public boolean del(int glid);
	
	
	/**
	 * 总记录数
	 * @return
	 */
	public int total();
	
	/**
	 * 分页查询管理员信息
	 * @param pageNo：要查询的页数，如果为null，则查询全部
	 * @param pageSize：页面显示的条数
	 * @return
	 */
	public List<GuanLi> findAllGuanLis(int pageNo,int pageSize);
	
	/**
	 * 查询指定的管理员信息
	 * @param glid：要查询的管理员编号
	 * @return
	 */
	public GuanLi findGuanLiByGLId(int glid);

}
