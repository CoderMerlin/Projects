package com.exam.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.exam.dao.DBHelper;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * 试题信息的操作
 * @author Administrator
 *
 */
public class QuestionInfo extends DBHelper {
	private SimpleDateFormat sdf=new SimpleDateFormat("yyyy/MM/dd HH:mm");
	private String qbKind="";
	private int qnumber=0;
	private String courseName="";

	public void setValue(int num,String qk,String cn){
		qnumber=num;
		qbKind=qk;
		courseName=cn;
	}


	/**
	 * 查询课程种类
	 * @return
	 */
	public String[] findcourseName(){
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >();
		String sql="select distinct courseName from course";
		list=this.select(sql, null);

		String[] cname=new String[list.size()];

		for(int i=0;i<list.size();i++){
			cname[i]=String.valueOf(list.get(i).get("COURSENAME"));
		}
		return cname;
	}

	/**
	 * 查询题目种类
	 * @return
	 */
	public String[] fingQbkind(){
		List<Map<String, Object> > list=new ArrayList<Map<String, Object> >();
		String sql="select distinct qbKind from questionBank";

		list=this.select(sql, null);

		String[] qbkinds=new String[list.size()];

		for(int i=0;i<list.size();i++){
			qbkinds[i]=String.valueOf(list.get(i).get("QBKIND"));
		}

		return qbkinds;
	}

	/**
	 * 将随机取得的题目插入试卷表，生成一张试卷
	 * @param params
	 * @return
	 */
	public int insertQuestion(List<Object> params){
		StringBuffer sbQId=new StringBuffer(); //题目编号
		StringBuffer sbRKey=new StringBuffer(); //题目答案
		List<String[]> question=findQuestion(); //题目
		//String[] qId=question.get(0); //题目编号
		//String[] rKey=question.get(1); //题目答案

		for(int i=0;i<question.get(0).length;i++){
			sbQId.append(question.get(0)[i]+"_");
			sbRKey.append(question.get(1)[i]+"_");
		}
		String sql="insert into Question values(?,?,seq_question_qid.nextval,?,'"+sbQId+"','"+sbRKey+"',?,'',?,'未考',?)";


		return this.update(sql, params);
	}

	/**
	 * 更新exam表
	 * @param params
	 */
	public void insertQuestionNextOp(List<Object> params){
		String sql="select * from Question where qname='"+params.get(2)+"'";
		String qid=(String) this.select(sql, null).get(0).get("QID"); //试卷编号

		String sql2="select * from Student where cid='"+(String) params.get(0)+"' and sexists='1'";
		String sql3="select count(*) from Student where cid='"+(String) params.get(0)+"' and sexists='1'";
		int stuNum=(int) this.selectPloymer(sql3, null);
		String[] stuIDs=new String[stuNum];
		for(int i=0;i<stuNum;i++){ //获取学号数组
			stuIDs[i]=(String) this.select(sql2, null).get(i).get("STUID");
		}

		String sql4=null;
		for(int i=0;i<stuNum;i++){
			sql4="insert into Exam values('"+qid+"','"+stuIDs[i]+"',0,'',2)";
			this.update(sql4, null);
		}
	}

	/**
	 * 找到指定种类的题目，并随机取得题目
	 * @return 题目内容的字符串数组
	 */
	public List<String[]> findQuestion(){
		List<Object> kind=new ArrayList<Object>(); //存放指定的种类，用于查询
		List<Map<String, Object>> list=new ArrayList<Map<String, Object> >(); //存放查到指定种类的所有题目
		List<String[]> question=new ArrayList<String[]>(); //存放随机到的题目
		String[] qId=new String[qnumber]; //记录题目编号
		String[] rKeys=new String[qnumber]; //记录正确答案


		String sql="select * from questionBank where courseName=? and qbKind=?"; //查询语句
		kind.add(qbKind); //加入种类，用于查询
		kind.add(courseName); //加入课程名，用于查询
		list=this.select(sql, kind); //查到指定种类的所有题目


		int[] randomId=randomCommon(0, list.size(), qnumber); //用于随机取得该种类中的n个题目,取得不重复的题目编号

		for(int i=0;i<qnumber;i++){ //随机取题，赋值给qContent qId
			qId[i]=String.valueOf(list.get(randomId[i]).get("QBID"));
			rKeys[i]=String.valueOf(list.get(randomId[i]).get("RKEY"));
		}
		question.add(qId);
		question.add(rKeys);

		return question;
	}

	/**
	 * 随机指定范围内N个不重复的数
	 * @param min 指定范围最小值
	 * @param max 指定范围最大值
	 * @param n 随机数个数
	 */
	public int[] randomCommon(int min, int max, int n){
		if (n > (max - min + 1) || max < min) {
			return null;
		}
		int[] result = new int[n];
		for(int i=0;i<n;i++){
			result[i]=max+1;
		}
		int count = 0;
		while(count < n) {
			int num = (int) (Math.random() * (max - min)) + min;
			boolean flag = true;
			for (int j = 0; j < n; j++) {
				if(num == result[j]){
					flag = false;
					break;
				}
			}
			if(flag){
				result[count] = num;
				count++;
			}
		}
		return result;
	}


	/**
	 * 查找题库中的所有题目
	 * @return
	 */
	public List<Map<String,Object>> findAllQuestion(){
		String sql="select * from questionBank order by qbid";
		return this.select(sql, null);
	}

	/**
	 * 查找题库中相关课程的题目
	 * @return
	 */
	public List<Map<String,Object>> findAllQuestion(String coursename){
		String sql="select * from questionBank where coursename=? order by qbid ";
		List<Object> params=new ArrayList<Object>();
		params.add(coursename);
		return this.select(sql, params);
	}


	/**
	 * 根据课程种类对题库的题目进行排序
	 * @return
	 */
	public List<Map<String,Object>> QuestionSortByCoursename(){
		String sql="select * from questionBank order by courseName";
		return this.select(sql, null);
	}

	/**
	 * 更新题库中的题目
	 * @param qdib 题目编号
	 * @param 需要修改的字段
	 * @content 要修改的内容
	 * @return
	 */
	public void updateQuestion(String qbid,String param,String content){
		List<Object> list=new ArrayList<Object>();
		String sql="update questionBank set "+param+"=? where qbid=?";
		list.add(content);
		list.add(qbid);

		this.update(sql, list);
	}

	/**
	 * 删除题库中的题目
	 * @param qbid 题目编号
	 * @return
	 */
	public int delQuestion(String qbid){
		List<Object> list=new ArrayList<Object>();
		String sql="delete from questionBank where qbId=?";
		list.add(qbid);

		return this.update(sql, list);
	}

	/**
	 * 查找所有没被销毁的试卷
	 * @return
	 */
	public List<Map<String,Object>> findAllTest(){
		String sql="select * from Question where qexists not in ('销毁') order by qid";
		return this.select(sql, null);
	}

	/**
	 * 查找该班级可以考试考试的试卷
	 * @param stuid 该学生学号
	 * @return
	 */
	public List<Map<String,Object>> findStuTest(String stuid){
		//		List<Object> params=new ArrayList<Object>();
		//		String sql="select cid from Student where stuid=?";
		//		String sql2="select * from question where (qexists='开考' or qexists='未阅') and cid=";
		//		params.add(stuid);
		//		String cid=(String)this.select(sql, params).get(0).get("CID");
		//		sql2+="'"+cid+"'";
		//		return this.select(sql2, null);
		String sql="select q.* from question q,exam e where e.eexists='1' and e.stuid='"+stuid+"'and e.qid=q.qid";

		return this.select(sql, null);
	}

	/**
	 * 销毁试卷，将试卷的存在状态变为'销毁'
	 * @param qid 要操作的试卷的编号
	 * @return
	 */
	public int delTest(String qid){
		List<Object> list=new ArrayList<Object>();
		String sql="update Question set qexists='销毁' where qid=?";
		list.add(qid);

		return this.update(sql, list);
	}

	/**
	 * 开始考试，将纸卷状态改为'开考'，并赋开考日期
	 * @param qid 要操作的试卷的编号
	 * @return
	 */
	public int startTest(String qid){
		List<Object> list=new ArrayList<Object>();
		String stime=sdf.format(new Date());
		String sql="update Question set stime=to_date(?,'yyyy/mm/dd hh24:mi'), qexists='开考' where qid=? and qexists='未考'";
		list.add(stime);
		list.add(qid);

		return this.update(sql, list);
	}

	public void startTestNextOp(String qid){
		String sql2="update exam set eexists='1' where qid='"+qid+"' and eexists='2'";
		this.update(sql2, null);
	}

	/**
	 *将试卷的状态改为未阅，已考
	 */
	public void shutTest(String qid){
		String sql="update Question set qexists='未阅' where qid='"+qid+"' and qexists='开考'"; 
		this.update(sql, null); //将试卷状态改为未阅
		String sql2="update exam set eexists='0' where qid='"+qid+"'";
		this.update(sql2, null); //将试卷状态改为已考
	}

	/**
	 * 阅卷，将试卷的状态改为'阅卷'
	 * @param qid 要操作的试卷的编号
	 * @return
	 */
	public void readTest(String qid){
		List<Object> list=new ArrayList<Object>();
		String sql="update Question set qexists='已阅' where qid=? and qexists not in('未考')";
		String sql2="update exam set eexists='0' where qid=?";

		list.add(qid);

		this.update(sql, list);
		this.update(sql2, list);
	}

	/**
	 * 找到试卷的详细
	 * @param qid 试卷编号
	 * @return
	 */
	public List<Object> findTest(String qid){
		List<Object> params=new ArrayList<Object>(); //存放参数
		List<Map<String, Object>> list=new ArrayList<Map<String, Object> >(); //存放找到的内容
		List<Object> result=new ArrayList<Object>(); //存放结果

		String sql="select * from Question where qId=?";		
		//String sql2="select qnumber from Question where qid=?";

		String sql3 = null;

		params.add(qid);

		list=this.select(sql, params); //找到该套试卷的题目数量 	list.get(0)为题目数量
		int qnum=Integer.valueOf( (String) list.get(0).get("QNUMBER") );
		//String qbids=(String) this.select(sql, params).get(0).get("QBID");
		//System.out.println(qbids);

		String[] qbids=((String) this.select(sql, params).get(0).get("QBID")).split("_"); //获取该套试卷的题目编号
		String[] qbContents=new String[qnum];
		String[] qbOptions=new String[qnum];
		//String[] rkeys=new String[qnum];
		for(int i=0;i<qnum;i++){
			sql3="select * from questionBank where qbid="+qbids[i];
			qbContents[i]=(String) this.select(sql3, null).get(0).get("QBCONTENT");
			qbOptions[i]=(String) this.select(sql3, null).get(0).get("QBOPTION");
			//rkeys[i]=(String) this.select(sql3, null).get(0).get("RKEY");
		}

		//String qbkind=(String) this.select("select qbKind from questionBank where qbId="+"'"+qbids[0]+"'", null).get(0).get("QBKIND");

		result.add(qnum);
		result.add(qbContents);
		result.add(qbOptions);
		//result.add(rkeys);
		//result.add(qbkind);


		return result;
	}

	/**
	 * 查找学生考试结果详细 TODO
	 * @param stuid 学生学号
	 * @param qid 试卷编号
	 */
	public List<Object> findExamResult(String stuid,String qid){
		List<Object> result=new ArrayList<Object>();
		List<Object> params=new ArrayList<Object>(); //存放参数
		String sql="select * from Question where qId=?";
		params.add(qid);
		int qnum=Integer.valueOf( (String) this.select(sql, params).get(0).get("QNUMBER") );

		String sql3="";
		String[] qbids=((String) this.select(sql, params).get(0).get("QBID")).split("_"); //获取该套试卷的题目编号
		String[] qbContents=new String[qnum];
		String option="";
		for(int i=0;i<qnum;i++){
			sql3="select * from questionBank where qbid="+qbids[i];
			option=(String) this.select(sql3, null).get(0).get("QBOPTION");
			qbContents[i]=(String) this.select(sql3, null).get(0).get("QBCONTENT");

			if(option!=null){
				qbContents[i]+="\n"+option.split("_")[0];
				qbContents[i]+="\n"+option.split("_")[1];
				qbContents[i]+="\n"+option.split("_")[2];
				qbContents[i]+="\n"+option.split("_")[3];
			}
		}

		String[] rkeys=((String) this.select(sql, params).get(0).get("RKEY")).split("_");

		String sql2="select * from Exam where qid=? and stuid=?";
		params.add(stuid);
		String[] skeys=((String) this.select(sql2, params).get(0).get("SKEY")).split("_");

		result.add(qnum);
		result.add(qbContents);
		result.add(rkeys); //正确答案
		result.add(skeys);


		return result;
	}

	/**
	 * 找出试卷状态
	 * @return
	 */
	public String findQexists(String qid){
		List<Object> list=new ArrayList<Object>();
		List<Map<String, Object>> qe=new ArrayList<Map<String,Object>>();
		String sql="select qexists from question where qid=?";
		list.add(qid);
		qe=this.select(sql, list);

		return String.valueOf(qe.get(0).get("QEXISTS"));
	}
	
	
	/**
	 * 找出试卷名字
	 * @param qid 试卷编号
	 * @return
	 */
	public String findTestName(String qid){
		List<Object> list=new ArrayList<Object>();
		String sql="select * from question where qid=?";
		list.add(qid);

		return (String) this.select(sql, list).get(0).get("QNAME");
	}

	/**
	 * 找出题库中的题目详细
	 * @return
	 */
	public List<Object> findQBquestion(String qbId){
		List<Object> result=new ArrayList<Object>(); //存放需要的结果
		List<Object> params=new ArrayList<Object>(); //存放占位符
		List<Map<String, Object>> selectResult=new ArrayList<Map<String,Object>>(); //存放找出的结果

		String sql="select distinct qbKind from questionBank";

		selectResult=this.select(sql, null);
		String[] qbkinds=new String[selectResult.size()];
		for(int i=0;i<selectResult.size();i++){
			qbkinds[i]=(String) selectResult.get(i).get("QBKIND");
		}
		result.add(qbkinds);

		String sql2="select * from questionBank where qbid=?";
		params.add(qbId);
		String kind=(String) this.select(sql2, params).get(0).get("QBKIND"); //qbid该题的种类
		result.add(kind);

		String rkey=(String) this.select(sql2, params).get(0).get("RKEY");
		result.add(rkey); 


		return result;
	}

	/**
	 * 添加课程
	 * @param list
	 * @return
	 */
	public int insertCourse(String courseName){
		List<Object> list=new ArrayList<Object>();
		String sql="insert into course values(seq_course_courseId.nextval,?,1)";
		list.add(courseName);

		return this.update(sql, list);
	}

	/**
	 * 找到指定的题目的数量
	 * @param coursename 课程名
	 * @param qbkind 题目种类
	 */
	public double findCQNum(String coursename,String qbkind){
		List<Object> params=new ArrayList<Object>();
		String sql="select count(*) from questionBank where coursename=? and qbkind=?";
		params.add(coursename);
		params.add(qbkind);

		return this.selectPloymer(sql, params);
	}
}
