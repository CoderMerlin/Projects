package com.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;

/**
 * 班级信息的操作
 * @author Administrator
 *
 */
public class ClassInfo extends DBHelper {
	
	/**
	 * 查询所有存在的班级
	 * @return
	 */
	public List<Map<String, Object>> findAll(){
		String sql="select * from Classes where cexists=1 order by cId";
		return this.select(sql, null);
	}

	/**
	 * 查询所有存在的班级的编号
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
	 * 查询所有存在的班级的编号
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
	 * 插入班级信息
	 * @param cid 编号
	 * @param cname 名称
	 * @return
	 */
	public int addClassInfo(String cname){
		String sql="insert into Classes values('C'||seq_Classes_cId.nextval,?,1)";
		List<Object> params=new ArrayList<Object>();
		params.add(cname);
		return this.update(sql, params);
	}
	
	/**
	 * 删除班级，更改班级状态为0
	 */
	public int delClass(String cid){
		List<Object> list=new ArrayList<Object>();
		String sql="update classes set cexists=0 where cId=?";
		list.add(cid);
		
		return this.update(sql, list);
	}
	
	/**
	 * 获取该班的人数和考试课程的数量
	 * @param cid 班级编号
	 * @return
	 */
	public int getNum(String cid){
		int num=0;
		List<Object> params=new ArrayList<Object>();
		String sql1="select count(*) from student where cid=? and sexists=1";
		
		params.add(cid);
		
		num=(int) this.selectPloymer(sql1, params); //该班级的学生人数
		
		return num;
	}
	
	
	/**
	 * 找到该班级所有存在的学生成绩信息
	 * @param cid 班级编号
	 * @return
	 */
	public List<Object> findStuEscoreInfo(String cid){
		List<Object> result=new ArrayList<Object>(); //存放筛选出来的结果
		List<Object> params=new ArrayList<Object>(); //存放占位符
		
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >(); //存放读取出来的结果
		int ifoNum=0;

		//找出cid班的考生信息
		String sql="select s.stuid,sname,qname,escore,skey from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid=? and q.qexists='已阅' and sexists=1 order by s.stuid";
		String sql2="select count(*) from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid=? and q.qexists='已阅'"; //统计工几条信息
		
		params.add(cid); //将需要查询的班级的编号赋值给params
		
		list=this.select(sql, params);
		ifoNum=(int) this.selectPloymer(sql2, params);
		
		String[] stuid=new String[list.size()]; //学生编号
		String[] sname=new String[list.size()]; //学生姓名
		String[] qname=new String[list.size()]; //试卷名称
		String[] escore=new String[list.size()]; //成绩
		
		for(int i=0;i<list.size();i++){
			stuid[i]=String.valueOf(list.get(i).get("STUID"));
			sname[i]=String.valueOf(list.get(i).get("SNAME"));
			qname[i]=String.valueOf(list.get(i).get("QNAME"));
			if(list.get(i).get("SKEY")!=null && !"".equals( list.get(i).get("SKEY") )){
				escore[i]=String.valueOf(list.get(i).get("ESCORE"));
			}else{
				escore[i]="缺考";
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
	 * 找到该班级所有存在的学生信息
	 * @param cid 班级编号
	 * @return
	 */
	public List<Map<String, Object>> findClassStuInfo(String cid){
		List<Object> params=new ArrayList<Object>();
		String sql="select * from student where cid=? and sexists=1 order by stuid";
		params.add(cid);
		
		return this.select(sql, params);
	}
	
	/**
	 * 找到班级名字
	 * @param cid 班级编号
	 * @return
	 */
	public String findCName(String cid){
		String sql="select * from Classes where cid="+"'"+cid+"'";
		
		return (String) this.select(sql, null).get(0).get("CNAME");
	}

	/**
	 * 获取改班级的平均分
	 * @param cid 班级编号
	 * @return
	 */
	public List<Object> findAVGScore(String cid){
		List<Object> result=new ArrayList<Object>(); //存放结果
		String sql="select * from question where qexists='已阅' and cid='"+cid+"'"; //找到已阅的试卷
		List<Map<String, Object>> qids=this.select(sql, null);
		String[] avg=new String[qids.size()];
		String[] qname=new String[qids.size()];
		String sql2=null;
		for(int i=0;i<qids.size();i++){ //统计平均分
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
