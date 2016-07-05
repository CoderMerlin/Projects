package com.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;

public class RegisterInfo extends DBHelper{
	
	public List<Map<String,Object>> findStuId(String stuid){
		String sql="select * from student where stuid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(stuid);
		return this.select(sql,params);
	}
	
	
	public List<Map<String,Object>> findTid(String tid){
		String sql="select * from Teacher where tid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(tid);
		return this.select(sql,params);
	}
	
	/**
	 * 查询学号最大的ID为数字
	 * @return
	 */
	public List<Map<String,Object>> findMaxStuId(){
		String sql="select  substr((select max(stuid) stuid from Student),2) stuid from(select max(stuid) stuid from Student)";
		return this.select(sql, null);
	}
	
	/**
	 * 查找教师最大的TID账号,只有数字
	 * @return
	 */
	public List<Map<String,Object>> findMaxTid(){
		String sql="select  substr((select max(tid) tid from Teacher),2) tid from(select max(tid) tid from Teacher)";
		return this.select(sql, null);
	}
	
	
	/**
	 * 找回密码，通过邮箱修改密码
	 * @param tpwd：教师的新密码
	 * @param temail：教师的邮箱
	 * @return
	 */
	public int updateTpwd(String tpwd,String temail){
		String sql="update teacher set tpwd=? where temail=?";
		List<Object> params=new ArrayList<Object>();
		params.add(tpwd);
		params.add(temail);
		return this.update(sql, params);
	}
	
	/**
	 * 找回密码，通过邮箱修改密码
	 * @param spwd：学生的新密码
	 * @param semail：学生的邮箱
	 * @return
	 */
	public int updateStupwd(String spwd,String semail){
		String sql="update student set spwd=? where semail=?";
		List<Object> params=new ArrayList<Object>();
		params.add(spwd);
		params.add(semail);
		return this.update(sql, params);
	}

}
