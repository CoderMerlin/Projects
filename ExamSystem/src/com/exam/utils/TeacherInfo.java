package com.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;

/**
 * ��ʦ��Ϣ�Ĳ���
 * @author Administrator
 *
 */
public class TeacherInfo extends DBHelper {
	
	/**
	 * ��ʦ��¼����Ϣ
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
	 * ��ѯ���н�ʦ
	 * @return
	 */
	public List<Map<String, Object>> findAll(){
		String sql="select * from Teacher order by tId";
		return this.select(sql, null);
	}
	
	/**
	 * ���½�ʦ��Ϣ
	 * @param tid ���
	 * @param tname ����
	 * @param tsex �Ա�
	 * @param tpwd ����
	 * @param temail ����
	 * @param ttel �绰
	 * @param tphoto ��Ƭ
	 * @param texists �Ƿ����
	 * @param tread �Ƿ��ľ�
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
	 * ���½�ʦ��Ϣ
	 * @param tid ���
	 * @param tname ����
	 * @param tsex �Ա�
	 * @param tpwd ����
	 * @param temail ����
	 * @param ttel �绰
	 * @param tphoto ��Ƭ
	 * @param texists �Ƿ����
	 * @param tread �Ƿ��ľ�
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
	 * ���
	 * @param tid ���
	 * @param tname ����
	 * @param tsex �Ա�
	 * @param tpwd ����
	 * @param temail ����
	 * @param ttel �绰
	 * @param tphoto ��Ƭ
	 * @param taddr ��ַ
	 * @param texists �Ƿ����
	 * @param tread �Ƿ��ľ�
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
	 * ��ѯ��ʦ����
	 * @param tid����ʦId
	 * @return
	 */
	public List<Map<String,Object>> findTeacherOldPwd(String tid){
		String sql="select tpwd from teacher where tid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(tid);
		return this.select(sql, params);
	}
	
	
}
