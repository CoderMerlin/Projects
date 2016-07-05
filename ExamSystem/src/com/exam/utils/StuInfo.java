package com.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;


/**
 * 学生信息
 * @author Administrator
 *
 */
public class StuInfo extends DBHelper{
	/**
	 * 用户登录的方法
	 * @param usid：账号
	 * @param pwd：密码
	 * @return
	 */
	public Map<String,Object> stuLogin(String stuId,String sPwd){
		String sql="select * from Student where stuId=? and sPwd=?";
		List<Object> params=new ArrayList<Object>();
		params.add(stuId);
		params.add(sPwd);
		
		List<Map<String,Object>> list=this.select(sql, params);
		if(list!=null && list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	/**
	 * 查找所有学生
	 * @return
	 */
	public List<Map<String,Object>> findAllStu(){
		String sql="select * from Student where sexists=1 order by stuId";
		return this.select(sql, null);
	}
	
	/**
	 * 查找本班级所有学生
	 * stuid 该学生学号
	 * @return
	 */
	public List<Map<String,Object>> findClassStu(String stuid){
		List<Object> params=new ArrayList<Object>();
		String sql="select cid from Student where stuid=?";
		String sql2="Select * from student where sexists=1 and cid=";
		
		params.add(stuid);
		String cid=(String)this.select(sql, params).get(0).get("CID");
		
		sql2+="'"+cid+"'";
		
		return this.select(sql2, null);
	}
	
	/**
	 * 更新学生信息
	 * @param uname
	 * @param pwd
	 * @param sex
	 * @param age
	 * @param tel
	 * @param photo
	 * @return
	 */
	public int updateStuInfo(String stuid,String sName,String sClass,String sSex,String sAge,String sAddr,String sQq,String sEmail,String sTel,byte[] sphoto){
		String sql="update student set sname=?,cid=?,ssex=?,sage=?,saddr=?,sqq=?,semail=?,stel=?,sphoto=? where stuid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(sName);
		params.add(sClass);
		params.add(sSex);
		params.add(sAge);
		params.add(sAddr);
		params.add(sQq);
		params.add(sEmail);
		params.add(sTel);
		params.add(sphoto);
		params.add(stuid);
		
		return this.update(sql, params);
	}
	
	/**
	 * 更新学生信息
	 * @param uname
	 * @param pwd
	 * @param sex
	 * @param age
	 * @param tel
	 * @param photo
	 * @return
	 */
	public int updateStuInfo(String stuid,String sName,String sClass,String sSex,String sAge,String sAddr,String sEmail,String sTel ){
		String sql="update student set sname=?,cid=?,ssex=?,sage=?,saddr=?,semail=?,stel=? where stuid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(sName);
		params.add(sClass);
		params.add(sSex);
		params.add(sAge);
		params.add(sAddr);
		params.add(sEmail);
		params.add(sTel);
		params.add(stuid);
		
		return this.update(sql, params);
	}
	
	/**
	 * 添加学生信息
	 * @param stuId：学生号
	 * @param sName：学生名
	 * @param sPwd：密码
	 * @param sClass：班级
	 * @param sSex：性别
	 * @param sAge：年龄
	 * @param sAddr：地址
	 * @param sQq：qq
	 * @param sEmail:邮箱
	 * @param sTel：电话
	 * @param sPhoto：照片
	 * @return
	 */
	public int addStudentInfo(String stuId,String sName,String sPwd,String sClass,String sSex,String sAge,String sAddr,String sQq,String sEmail,String sTel,byte[] sPhoto){
		String sql="insert into Student values(?,?,?,?,?,?,?,?,?,?,?,1,0)";
		List<Object> params=new ArrayList<Object>();
		params.add(stuId);   //添加 学生号
		params.add(sName);   //姓名
		params.add(sPwd);    //密码
		params.add(sClass);	//班级
		params.add(sSex);	//性别
		params.add(sAge); 	//年龄
		params.add(sAddr);	  //地址
		params.add(sQq);       //qq
		params.add(sEmail);	//邮箱
		params.add(sTel);	//电话
		params.add(sPhoto);   //照片
		return this.update(sql, params);
	}
	

	/**
	 * 将学生存在状态变为0
	 * @param stuid
	 * @return
	 */
	public int delStu(String stuid){
		List<Object> list=new ArrayList<Object>();
		String sql="UPDATE Student SET sexists=0 WHERE stuid=?";
		list.add(stuid);
		
		return this.update(sql, list);
	}
	
	
	/**
	 * 学生提交成绩
	 * @param stuid 学号
	 * @param qId 试卷编号
	 * @param escore 学生的答案,以"_"分开
	 * @return
	 */
	public int submit(String stuid,String qid,String skey){ //读出试卷的正确答案与学生答案比较，得出分数，更新考试结果表
		List<Object> parampid=new ArrayList<Object>(); //存放参数列表pid
		List<Object> params=new ArrayList<Object>(); //存放参数列表pid

		int escore=0; //记录该生该门的成绩
		
		String sql="select * from Question where qid=?";
		
		parampid.add(qid);
		
		int number=Integer.valueOf( (String) this.select(sql, parampid).get(0).get("QNUMBER") );
		int score=100/number;
		
		String[] rkeys=((String)this.select(sql, parampid).get(0).get("RKEY")).split("_");
		String[] skeys=skey.split("_");

		for(int i=0;i<number;i++){
			if(skeys[i].trim().equals(rkeys[i].trim())){
				escore+=score;
			}
		}
		//写入学生成绩
		String sql2="update exam set escore=? , skey=? ,eexists='0' where stuid=? and qid=?";
		params.add(escore);
		params.add(skey);
		params.add(stuid);
		params.add(qid);
		
		submitNextOp2(qid);
		
		return this.update(sql2, params);
	}
	
	@SuppressWarnings("unchecked")
	public void submitNextOp2(String qid){		
		List<Integer> is=new ArrayList<Integer>();
		String sql="select e.*,q.* from exam e,question q where e.qid=q.qid and qexists='未阅' and e.qid='"+qid+"'";
		List<Map<String, Object>> stuTestInfos=this.select(sql, null);
		List<String> stuids=new ArrayList<String>();
		for(Map m:stuTestInfos){
			if(m.get("SKEY")!=null && !"".equals(m.get("SKEY"))){
				stuids.add((String) m.get("STUID"));
			}
		}
		
		String sql2;
		for(int i=0;i<stuids.size();i++){
			sql2="select * from exam where qid='"+qid+"' and stuid='"+stuids.get(i)+"'";
			if(this.select(sql2, null).get(0).get("SKEY")==null || "".equals(this.select(sql2, null).get(0).get("SKEY"))){
				is.add(0);
			} else{
				is.add(1);
			}
		}
		
		if(stuids.size()>0){
			if(is.contains(0)){
				
			} else{
				String sql3="update Question set qexists='已阅' where qid='"+qid+"'"; 
				this.update(sql3, null); //将试卷状态改为已阅
				String sql4="update exam set eexists='0' where qid='"+qid+"'";
				this.update(sql4, null);
			}
		}
	}
	
	
	
	public void submitNextOp(String qid){
		boolean isAll=true;
		String sql5="select * from exam where qid='"+qid+"'";
		List<Map<String, Object>> list=this.select(sql5, null);
		for(Map<String, Object> m:list){
			if( "1".equals( m.get("EEXISTS") ) || "2".equals(m.get("EEXISTS"))){
				isAll=false;
			}
		}
		
		if(isAll){
			String sql3="update Question set qexists='已阅' where qid='"+qid+"'"; 
			this.update(sql3, null); //将试卷状态改为未阅
		}
	}
	
	
	
	/**
	 * 查询该学生所考试卷的答案
	 * @param stuid：考生的ID
	 * @param qid:试卷的编号
	 * @return
	 */
	public List<Map<String,Object>> findStuAnswer(String stuid,String qid){
		String sql="select rkey,skey from Question q,Exam e where q.qid=e.qid and stuid=? and q.qid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(stuid);
		params.add(qid);
		return this.select(sql,params);
	}
	
	/**
	 * 查看该学生的所有考试的分数
	 * @param stuid
	 * @return
	 */
	public List<Map<String,Object>> findStuAllExamScore(String stuid){
		String sql="select escore,qname,Stuid from Question q,Exam e where q.qid=e.qid and stuid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(stuid);
		return this.select(sql, params);
	}
	
	

	
	/**
	 * 查找试卷信息
	 * @return
	 */
	public List<Map<String,Object>> findTest(){
		String sql="select qId,courseName,cID,sTime from Question q,questionBank qtb where q.qbid=qtb.qbid";
		return this.select(sql, null);
	}
	
	
	/**
	 * 查找考试结果
	 * @stuid 学生编号
	 * @return
	 */
	public  List<Map<String,Object>> findExamResult(String stuid){
		List<Object> params=new ArrayList<Object>();
		String sql="select q.qid,qname,stime,escore from Exam e,Question q where q.qid=e.qid and stuid=? and qexists='已阅'";
		params.add(stuid);
		return this.select(sql, params);
	}
	
	/**
	 * 查找所有的谈话
	 * @return
	 */
	public List<Map<String,Object>> findAllTalk(){
		String sql="select * from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId";
		return this.select(sql, null);
	}
	
	/**
	 * 查看所有谈话的内容
	 */
	public List<Map<String,Object>> findAllTalkContent(){
		String sql="select ttId,talkcontent,tpraise,s.stuId,s.sphoto,s.sName from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId";
		return this.select(sql, null);
	}
	
	/**
	 * 添加谈话
	 * @param ttid：谈话的ID
	 * @param stuId:学生的Id
	 * @param talkcontent:谈话的内容
	 * @param tpraise:点赞数
	 * @return
	 */
	public int addTalkContent(String stuId,String talkcontent){
		String sql="insert into Talk values(seq_talk_ttid.nextval,?,?,1,1)";
		List<Object> list=new ArrayList<Object>();
		list.add(stuId);
		list.add(talkcontent);
		return this.update(sql, list);
	}
	
	/**
	 * 查询stuid中学生的姓名
	 * @param stuid
	 * @return
	 */
	public  List<Map<String,Object>> findTalkContentStuId(String stuid){
		List<Object> params=new ArrayList<Object>();
		String sql="select sname,sphoto from student where stuid=?";
		params.add(stuid);
		return this.select(sql, params);
	}
	
	/**
	 * 点赞的学生
	 * @param tpraise：点赞
	 * @param stuId：学生
	 * @return
	 */
	public int addTalkTpraise(String tpraise,String stuId){
		String sql="update talk set tpraise=? where stuId=?";
		List<Object> params=new ArrayList<Object>(); //存放参数列表
		params.add(tpraise);
		params.add(stuId);
		
		return this.update(sql, params);
	}
	
	/**
	 * 查找出所有回复的内容
	 * @return
	 */
	public  List<Map<String,Object>> findAllDiscussContent(){
		String sql="select * from discuss";
		return this.select(sql, null);
	}
	
	/**
	 * 查出该点击评论的所有回复
	 * @param stuid：该评论学生的回复
	 * @return
	 */
	public List<Map<String,Object>> findThisDiscussContent(String stuid,String ttid){
		String sql="select did,discusscontent  from talk t,discuss d where t.ttid=d.ttid and  d.stuid=? and d.ttid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(stuid);
		params.add(ttid);
		return this.select(sql, params);
	}
	
	/**
	 * 查询该评论的所有回复
	 * @param stuid
	 * @return
	 */
	public List<Map<String,Object>> findThisTalkAllDiscussContent(String ttid){
		String sql="select discusscontent, d.ttid, d.stuid, sname from talk t,discuss d,student s where t.ttid=d.ttid and  d.stuid=s.stuid  and d.ttid=?";
		List<Object> params=new ArrayList<Object>();
		params.add(ttid);
		return this.select(sql, params);
	}
	
	/**
	 * 添加回复
	 * @param ttid:点击回复按钮对应的人
	 * @param stuid:评论学生的id
	 * @param DiscussContent:回复的内容
	 * @return
	 */
	public int addDiscussContent(String ttid,String stuid,String DiscussContent){
		String sql="insert into discuss values(seq_talk_ttid.nextval,?,?,?,1)";
		List<Object> params=new ArrayList<Object>();
		params.add(ttid);
		params.add(stuid);
		params.add(DiscussContent);
		
		return this.update(sql, params);
	}
}
