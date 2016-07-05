package com.exam.dao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;

public class UserInfoDao extends DBHelper{

		/**
		 * 记录学生登录
		 * @param usid：账户
		 * @param pwd：密码
		 * @return
		 */
	public Map<String,Object> studentLogin(String stuid,String spwd){
		String sql="select * from student where stuId=? and spwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(stuid);
		params.add(spwd);
		
		List<Map<String,Object>> list=this.select(sql, params);
		if (list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 记录教师登录
	 * @param usid：账户
	 * @param pwd：密码
	 * @return
	 */
public Map<String,Object> teacherLogin(String tId,String tPwd){
	String sql="select * from Teacher where tId=? and tPwd=?";
	List<Object> params=new ArrayList<Object>();
	params.add(tId);
	params.add(tPwd);
	
	List<Map<String,Object>> list=this.select(sql, params);
	if (list!=null && list.size()>0){
		return list.get(0);
	}else{
		return null;
	}
}

	
	
	
	 /**
	  * 查询所有
	  * @return
	  */
	
	
	public List<Map<String,Object>> findAll(){
		String sql="select * from student order by stuId desc";
		return this.select(sql, null);
	}
	
	
	/**
	 * 添加用户信息
	 * @param uname
	 * @param pwd
	 * @param sex
	 * @param age
	 * @param tel
	 * @param photo
	 * @return
	 */
	public int addUserInfo(String stuId,String sName,String sPwd,String cID,String sSex,String sAge,String sAddr,String sqq,byte[] sphoto){
		String sql="insert into Student values(?,?,?,?,?,?,?,?,?,null,null)";
		List<Object> params=new ArrayList<Object>();
		params.add(stuId);
		params.add(sName);
		params.add(sPwd);
		params.add(cID);
		params.add(sSex);
		params.add(sAge);
		params.add(sAddr);
		params.add(sqq);
		params.add(sphoto);
		
		return this.update(sql, params);
	}
	
	public List<Map<String,Object>> findTest(){
		String sql="select qId,courseName,cID,sTime from Question q,questionBank qtb where q.qbid=qtb.qbid";
		return this.select(sql, null);
	}
	
}
