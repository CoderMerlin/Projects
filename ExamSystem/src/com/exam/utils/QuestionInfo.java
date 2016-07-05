package com.exam.utils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


import com.exam.dao.DBHelper;
import com.ibm.icu.text.SimpleDateFormat;

/**
 * ������Ϣ�Ĳ���
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
	 * ��ѯ�γ�����
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
	 * ��ѯ��Ŀ����
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
	 * �����ȡ�õ���Ŀ�����Ծ������һ���Ծ�
	 * @param params
	 * @return
	 */
	public int insertQuestion(List<Object> params){
		StringBuffer sbQId=new StringBuffer(); //��Ŀ���
		StringBuffer sbRKey=new StringBuffer(); //��Ŀ��
		List<String[]> question=findQuestion(); //��Ŀ
		//String[] qId=question.get(0); //��Ŀ���
		//String[] rKey=question.get(1); //��Ŀ��

		for(int i=0;i<question.get(0).length;i++){
			sbQId.append(question.get(0)[i]+"_");
			sbRKey.append(question.get(1)[i]+"_");
		}
		String sql="insert into Question values(?,?,seq_question_qid.nextval,?,'"+sbQId+"','"+sbRKey+"',?,'',?,'δ��',?)";


		return this.update(sql, params);
	}

	/**
	 * ����exam��
	 * @param params
	 */
	public void insertQuestionNextOp(List<Object> params){
		String sql="select * from Question where qname='"+params.get(2)+"'";
		String qid=(String) this.select(sql, null).get(0).get("QID"); //�Ծ���

		String sql2="select * from Student where cid='"+(String) params.get(0)+"' and sexists='1'";
		String sql3="select count(*) from Student where cid='"+(String) params.get(0)+"' and sexists='1'";
		int stuNum=(int) this.selectPloymer(sql3, null);
		String[] stuIDs=new String[stuNum];
		for(int i=0;i<stuNum;i++){ //��ȡѧ������
			stuIDs[i]=(String) this.select(sql2, null).get(i).get("STUID");
		}

		String sql4=null;
		for(int i=0;i<stuNum;i++){
			sql4="insert into Exam values('"+qid+"','"+stuIDs[i]+"',0,'',2)";
			this.update(sql4, null);
		}
	}

	/**
	 * �ҵ�ָ���������Ŀ�������ȡ����Ŀ
	 * @return ��Ŀ���ݵ��ַ�������
	 */
	public List<String[]> findQuestion(){
		List<Object> kind=new ArrayList<Object>(); //���ָ�������࣬���ڲ�ѯ
		List<Map<String, Object>> list=new ArrayList<Map<String, Object> >(); //��Ų鵽ָ�������������Ŀ
		List<String[]> question=new ArrayList<String[]>(); //������������Ŀ
		String[] qId=new String[qnumber]; //��¼��Ŀ���
		String[] rKeys=new String[qnumber]; //��¼��ȷ��


		String sql="select * from questionBank where courseName=? and qbKind=?"; //��ѯ���
		kind.add(qbKind); //�������࣬���ڲ�ѯ
		kind.add(courseName); //����γ��������ڲ�ѯ
		list=this.select(sql, kind); //�鵽ָ�������������Ŀ


		int[] randomId=randomCommon(0, list.size(), qnumber); //�������ȡ�ø������е�n����Ŀ,ȡ�ò��ظ�����Ŀ���

		for(int i=0;i<qnumber;i++){ //���ȡ�⣬��ֵ��qContent qId
			qId[i]=String.valueOf(list.get(randomId[i]).get("QBID"));
			rKeys[i]=String.valueOf(list.get(randomId[i]).get("RKEY"));
		}
		question.add(qId);
		question.add(rKeys);

		return question;
	}

	/**
	 * ���ָ����Χ��N�����ظ�����
	 * @param min ָ����Χ��Сֵ
	 * @param max ָ����Χ���ֵ
	 * @param n ���������
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
	 * ��������е�������Ŀ
	 * @return
	 */
	public List<Map<String,Object>> findAllQuestion(){
		String sql="select * from questionBank order by qbid";
		return this.select(sql, null);
	}

	/**
	 * �����������ؿγ̵���Ŀ
	 * @return
	 */
	public List<Map<String,Object>> findAllQuestion(String coursename){
		String sql="select * from questionBank where coursename=? order by qbid ";
		List<Object> params=new ArrayList<Object>();
		params.add(coursename);
		return this.select(sql, params);
	}


	/**
	 * ���ݿγ������������Ŀ��������
	 * @return
	 */
	public List<Map<String,Object>> QuestionSortByCoursename(){
		String sql="select * from questionBank order by courseName";
		return this.select(sql, null);
	}

	/**
	 * ��������е���Ŀ
	 * @param qdib ��Ŀ���
	 * @param ��Ҫ�޸ĵ��ֶ�
	 * @content Ҫ�޸ĵ�����
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
	 * ɾ������е���Ŀ
	 * @param qbid ��Ŀ���
	 * @return
	 */
	public int delQuestion(String qbid){
		List<Object> list=new ArrayList<Object>();
		String sql="delete from questionBank where qbId=?";
		list.add(qbid);

		return this.update(sql, list);
	}

	/**
	 * ��������û�����ٵ��Ծ�
	 * @return
	 */
	public List<Map<String,Object>> findAllTest(){
		String sql="select * from Question where qexists not in ('����') order by qid";
		return this.select(sql, null);
	}

	/**
	 * ���Ҹð༶���Կ��Կ��Ե��Ծ�
	 * @param stuid ��ѧ��ѧ��
	 * @return
	 */
	public List<Map<String,Object>> findStuTest(String stuid){
		//		List<Object> params=new ArrayList<Object>();
		//		String sql="select cid from Student where stuid=?";
		//		String sql2="select * from question where (qexists='����' or qexists='δ��') and cid=";
		//		params.add(stuid);
		//		String cid=(String)this.select(sql, params).get(0).get("CID");
		//		sql2+="'"+cid+"'";
		//		return this.select(sql2, null);
		String sql="select q.* from question q,exam e where e.eexists='1' and e.stuid='"+stuid+"'and e.qid=q.qid";

		return this.select(sql, null);
	}

	/**
	 * �����Ծ����Ծ�Ĵ���״̬��Ϊ'����'
	 * @param qid Ҫ�������Ծ�ı��
	 * @return
	 */
	public int delTest(String qid){
		List<Object> list=new ArrayList<Object>();
		String sql="update Question set qexists='����' where qid=?";
		list.add(qid);

		return this.update(sql, list);
	}

	/**
	 * ��ʼ���ԣ���ֽ��״̬��Ϊ'����'��������������
	 * @param qid Ҫ�������Ծ�ı��
	 * @return
	 */
	public int startTest(String qid){
		List<Object> list=new ArrayList<Object>();
		String stime=sdf.format(new Date());
		String sql="update Question set stime=to_date(?,'yyyy/mm/dd hh24:mi'), qexists='����' where qid=? and qexists='δ��'";
		list.add(stime);
		list.add(qid);

		return this.update(sql, list);
	}

	public void startTestNextOp(String qid){
		String sql2="update exam set eexists='1' where qid='"+qid+"' and eexists='2'";
		this.update(sql2, null);
	}

	/**
	 *���Ծ��״̬��Ϊδ�ģ��ѿ�
	 */
	public void shutTest(String qid){
		String sql="update Question set qexists='δ��' where qid='"+qid+"' and qexists='����'"; 
		this.update(sql, null); //���Ծ�״̬��Ϊδ��
		String sql2="update exam set eexists='0' where qid='"+qid+"'";
		this.update(sql2, null); //���Ծ�״̬��Ϊ�ѿ�
	}

	/**
	 * �ľ����Ծ��״̬��Ϊ'�ľ�'
	 * @param qid Ҫ�������Ծ�ı��
	 * @return
	 */
	public void readTest(String qid){
		List<Object> list=new ArrayList<Object>();
		String sql="update Question set qexists='����' where qid=? and qexists not in('δ��')";
		String sql2="update exam set eexists='0' where qid=?";

		list.add(qid);

		this.update(sql, list);
		this.update(sql2, list);
	}

	/**
	 * �ҵ��Ծ����ϸ
	 * @param qid �Ծ���
	 * @return
	 */
	public List<Object> findTest(String qid){
		List<Object> params=new ArrayList<Object>(); //��Ų���
		List<Map<String, Object>> list=new ArrayList<Map<String, Object> >(); //����ҵ�������
		List<Object> result=new ArrayList<Object>(); //��Ž��

		String sql="select * from Question where qId=?";		
		//String sql2="select qnumber from Question where qid=?";

		String sql3 = null;

		params.add(qid);

		list=this.select(sql, params); //�ҵ������Ծ����Ŀ���� 	list.get(0)Ϊ��Ŀ����
		int qnum=Integer.valueOf( (String) list.get(0).get("QNUMBER") );
		//String qbids=(String) this.select(sql, params).get(0).get("QBID");
		//System.out.println(qbids);

		String[] qbids=((String) this.select(sql, params).get(0).get("QBID")).split("_"); //��ȡ�����Ծ����Ŀ���
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
	 * ����ѧ�����Խ����ϸ TODO
	 * @param stuid ѧ��ѧ��
	 * @param qid �Ծ���
	 */
	public List<Object> findExamResult(String stuid,String qid){
		List<Object> result=new ArrayList<Object>();
		List<Object> params=new ArrayList<Object>(); //��Ų���
		String sql="select * from Question where qId=?";
		params.add(qid);
		int qnum=Integer.valueOf( (String) this.select(sql, params).get(0).get("QNUMBER") );

		String sql3="";
		String[] qbids=((String) this.select(sql, params).get(0).get("QBID")).split("_"); //��ȡ�����Ծ����Ŀ���
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
		result.add(rkeys); //��ȷ��
		result.add(skeys);


		return result;
	}

	/**
	 * �ҳ��Ծ�״̬
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
	 * �ҳ��Ծ�����
	 * @param qid �Ծ���
	 * @return
	 */
	public String findTestName(String qid){
		List<Object> list=new ArrayList<Object>();
		String sql="select * from question where qid=?";
		list.add(qid);

		return (String) this.select(sql, list).get(0).get("QNAME");
	}

	/**
	 * �ҳ�����е���Ŀ��ϸ
	 * @return
	 */
	public List<Object> findQBquestion(String qbId){
		List<Object> result=new ArrayList<Object>(); //�����Ҫ�Ľ��
		List<Object> params=new ArrayList<Object>(); //���ռλ��
		List<Map<String, Object>> selectResult=new ArrayList<Map<String,Object>>(); //����ҳ��Ľ��

		String sql="select distinct qbKind from questionBank";

		selectResult=this.select(sql, null);
		String[] qbkinds=new String[selectResult.size()];
		for(int i=0;i<selectResult.size();i++){
			qbkinds[i]=(String) selectResult.get(i).get("QBKIND");
		}
		result.add(qbkinds);

		String sql2="select * from questionBank where qbid=?";
		params.add(qbId);
		String kind=(String) this.select(sql2, params).get(0).get("QBKIND"); //qbid���������
		result.add(kind);

		String rkey=(String) this.select(sql2, params).get(0).get("RKEY");
		result.add(rkey); 


		return result;
	}

	/**
	 * ��ӿγ�
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
	 * �ҵ�ָ������Ŀ������
	 * @param coursename �γ���
	 * @param qbkind ��Ŀ����
	 */
	public double findCQNum(String coursename,String qbkind){
		List<Object> params=new ArrayList<Object>();
		String sql="select count(*) from questionBank where coursename=? and qbkind=?";
		params.add(coursename);
		params.add(qbkind);

		return this.selectPloymer(sql, params);
	}
}
