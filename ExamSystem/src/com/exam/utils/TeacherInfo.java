package com.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;

/**
 * 教师信息的操作
 * @author Administrator
 *
 */
public class TeacherInfo extends DBHelper {
	
	/**
	 * 教师登录的信息
	 * @param tId
	 * @param tPwd
	 * @return
	 */
	public Map<String,Object> teacherLogin(String tId,String tPwd){
		String sql="select * from Teacher where tId=? and tPwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(tId);
		params.add(tPwd);
		
		List<Map<String,Object>> list=this.select(sql, params);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查询所有教师
	 * @return
	 */
	public List<Map<String, Object>> findAll(){
		String sql="select * from Teacher order by tId";
		return this.select(sql, null);
	}
	
	/**
	 * 更新教师信息
	 * @param tid 编号
	 * @param tname 姓名
	 * @param tsex 性别
	 * @param tpwd 密码
	 * @param temail 邮箱
	 * @param ttel 电话
	 * @param tphoto 照片
	 * @param texists 是否存在
	 * @param tread 是否阅卷
	 * @return
	 */
	public int updateTeacherInfo(String tid,String tname,String tsex,String tpwd,String temail,String ttel,String taddr,byte[] tphoto){
		String sql="update teacher set tname=?,tsex=?,tpwd=?,temail=?,ttel=?,tphoto=?,taddr=? where tid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(tname);
		params.add(tsex);
		params.add(tpwd);
		params.add(temail);
		params.add(ttel);
		params.add(tphoto);
		params.add(taddr);
		params.add(tid);
		return this.update(sql, params);
	}
	
	/**
	 * 更新教师信息
	 * @param tid 编号
	 * @param tname 姓名
	 * @param tsex 性别
	 * @param tpwd 密码
	 * @param temail 邮箱
	 * @param ttel 电话
	 * @param tphoto 照片
	 * @param texists 是否存在
	 * @param tread 是否阅卷
	 * @return
	 */
	public int updateTeacherInfo(String tid,String tname,String tsex,String tpwd,String temail,String ttel,String taddr){
		String sql="update teacher set tname=?,tsex=?,tpwd=?,temail=?,ttel=?,taddr=? where tid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(tname);
		params.add(tsex);
		params.add(tpwd);
		params.add(temail);
		params.add(ttel);
		params.add(taddr);
		params.add(tid);
		return this.update(sql, params);
	}
	
	
	/**
	 * 添加
	 * @param tid 编号
	 * @param tname 姓名
	 * @param tsex 性别
	 * @param tpwd 密码
	 * @param temail 邮箱
	 * @param ttel 电话
	 * @param tphoto 照片
	 * @param taddr 地址
	 * @param texists 是否存在
	 * @param tread 是否阅卷
	 * @return
	 */
	public int addTeacherInfo(String tid,String tname,String tpwd,byte[] tphoto,String tsex,String taddr,String ttel,String temail){
		String sql="insert into Teacher values(?,?,?,?,?,?,?,?,1,0)";
		List<Object> params=new ArrayList<Object>();
		params.add(tid);
		params.add(tname);
		params.add(tpwd);
		params.add(tphoto);
		params.add(tsex);
		params.add(taddr);
		params.add(ttel);
		params.add(temail);
		return this.update(sql, params);
	}
	
	/**
	 * 查询教师密码
	 * @param tid：教师Id
	 * @return
	 */
	public List<Map<String,Object>> findTeacherOldPwd(String tid){
		String sql="select tpwd from teacher where tid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(tid);
		return this.select(sql, params);
	}
	
	
}
