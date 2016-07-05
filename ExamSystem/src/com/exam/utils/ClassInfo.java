package com.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;

/**
 * �༶��Ϣ�Ĳ���
 * @author Administrator
 *
 */
public class ClassInfo extends DBHelper {
	
	/**
	 * ��ѯ���д��ڵİ༶
	 * @return
	 */
	public List<Map<String, Object>> findAll(){
		String sql="select * from Classes where cexists=1 order by cId";
		return this.select(sql, null);
	}

	/**
	 * ��ѯ���д��ڵİ༶�ı��
	 * @return
	 */
	public String[] findCName(){
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >();
		String sql="select cname from classes where cexists=1";
		
		list=this.select(sql, null);
		String[] cname=new String[list.size()];
		
		for(int i=0;i<list.size();i++){
			cname[i]=String.valueOf(list.get(i).get("CID"));
		}
		
		return cname;
	}
	
	/**
	 * ��ѯ���д��ڵİ༶�ı��
	 * @return
	 */
	public String[] findCId(){
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >();
		String sql="select cid from classes where cexists=1";
		
		list=this.select(sql, null);
		String[] cid=new String[list.size()];
		
		for(int i=0;i<list.size();i++){
			cid[i]=String.valueOf(list.get(i).get("CID"));
		}
		
		return cid;
	}
	
	/**
	 * ����༶��Ϣ
	 * @param cid ���
	 * @param cname ����
	 * @return
	 */
	public int addClassInfo(String cname){
		String sql="insert into Classes values('C'||seq_Classes_cId.nextval,?,1)";
		List<Object> params=new ArrayList<Object>();
		params.add(cname);
		return this.update(sql, params);
	}
	
	/**
	 * ɾ���༶�����İ༶״̬Ϊ0
	 */
	public int delClass(String cid){
		List<Object> list=new ArrayList<Object>();
		String sql="update classes set cexists=0 where cId=?";
		list.add(cid);
		
		return this.update(sql, list);
	}
	
	/**
	 * ��ȡ�ð�������Ϳ��Կγ̵�����
	 * @param cid �༶���
	 * @return
	 */
	public int getNum(String cid){
		int num=0;
		List<Object> params=new ArrayList<Object>();
		String sql1="select count(*) from student where cid=? and sexists=1";
		
		params.add(cid);
		
		num=(int) this.selectPloymer(sql1, params); //�ð༶��ѧ������
		
		return num;
	}
	
	
	/**
	 * �ҵ��ð༶���д��ڵ�ѧ���ɼ���Ϣ
	 * @param cid �༶���
	 * @return
	 */
	public List<Object> findStuEscoreInfo(String cid){
		List<Object> result=new ArrayList<Object>(); //���ɸѡ�����Ľ��
		List<Object> params=new ArrayList<Object>(); //���ռλ��
		
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >(); //��Ŷ�ȡ�����Ľ��
		int ifoNum=0;

		//�ҳ�cid��Ŀ�����Ϣ
		String sql="select s.stuid,sname,qname,escore,skey from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid=? and q.qexists='����' and sexists=1 order by s.stuid";
		String sql2="select count(*) from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid=? and q.qexists='����'"; //ͳ�ƹ�������Ϣ
		
		params.add(cid); //����Ҫ��ѯ�İ༶�ı�Ÿ�ֵ��params
		
		list=this.select(sql, params);
		ifoNum=(int) this.selectPloymer(sql2, params);
		
		String[] stuid=new String[list.size()]; //ѧ�����
		String[] sname=new String[list.size()]; //ѧ������
		String[] qname=new String[list.size()]; //�Ծ�����
		String[] escore=new String[list.size()]; //�ɼ�
		
		for(int i=0;i<list.size();i++){
			stuid[i]=String.valueOf(list.get(i).get("STUID"));
			sname[i]=String.valueOf(list.get(i).get("SNAME"));
			qname[i]=String.valueOf(list.get(i).get("QNAME"));
			if(list.get(i).get("SKEY")!=null && !"".equals( list.get(i).get("SKEY") )){
				escore[i]=String.valueOf(list.get(i).get("ESCORE"));
			}else{
				escore[i]="ȱ��";
			}
		}
		
		result.add(stuid);
		result.add(sname);
		result.add(qname);
		result.add(escore);
		result.add(ifoNum);
		
		return result;
	}
	
	/**
	 * �ҵ��ð༶���д��ڵ�ѧ����Ϣ
	 * @param cid �༶���
	 * @return
	 */
	public List<Map<String, Object>> findClassStuInfo(String cid){
		List<Object> params=new ArrayList<Object>();
		String sql="select * from student where cid=? and sexists=1 order by stuid";
		params.add(cid);
		
		return this.select(sql, params);
	}
	
	/**
	 * �ҵ��༶����
	 * @param cid �༶���
	 * @return
	 */
	public String findCName(String cid){
		String sql="select * from Classes where cid="+"'"+cid+"'";
		
		return (String) this.select(sql, null).get(0).get("CNAME");
	}

	/**
	 * ��ȡ�İ༶��ƽ����
	 * @param cid �༶���
	 * @return
	 */
	public List<Object> findAVGScore(String cid){
		List<Object> result=new ArrayList<Object>(); //��Ž��
		String sql="select * from question where qexists='����' and cid='"+cid+"'"; //�ҵ����ĵ��Ծ�
		List<Map<String, Object>> qids=this.select(sql, null);
		String[] avg=new String[qids.size()];
		String[] qname=new String[qids.size()];
		String sql2=null;
		for(int i=0;i<qids.size();i++){ //ͳ��ƽ����
			sql2="select avg(escore),q.qname from exam e, question q where e.qid=q.qid and e.qid='"+qids.get(i).get("QID")+"' group by q.qname";
			avg[i]=(String) this.select(sql2, null).get(0).get("AVG(ESCORE)");
			qname[i]=(String) this.select(sql2, null).get(0).get("QNAME");
		}
		
		result.add(qids.size());
		result.add(avg);
		result.add(qname);
		
		return result;
	}
	
	
//	public int addStudentInfo(String stuId,String sName,String sPwd,String sClass,String sSex,String sAge,String sAddr,String sQq,String sEmail,String sTel,byte[]  sPhoto){
//		List<Object> params=new ArrayList<Object>();
//		String sql="insert into Student values(?,?,?,?,?,?,?,?,?,?,?,1,0)";
//	}
}
