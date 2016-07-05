package com.exam.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.exam.dao.DBHelper;


/**
 * ѧ����Ϣ
 * @author Administrator
 *
 */
public class StuInfo extends DBHelper{
	/**
	 * �û���¼�ķ���
	 * @param usid���˺�
	 * @param pwd������
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
	 * ��������ѧ��
	 * @return
	 */
	public List<Map<String,Object>> findAllStu(){
		String sql="select * from Student where sexists=1 order by stuId";
		return this.select(sql, null);
	}
	
	/**
	 * ���ұ��༶����ѧ��
	 * stuid ��ѧ��ѧ��
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
	 * ����ѧ����Ϣ
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
	 * ����ѧ����Ϣ
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
	 * ���ѧ����Ϣ
	 * @param stuId��ѧ����
	 * @param sName��ѧ����
	 * @param sPwd������
	 * @param sClass���༶
	 * @param sSex���Ա�
	 * @param sAge������
	 * @param sAddr����ַ
	 * @param sQq��qq
	 * @param sEmail:����
	 * @param sTel���绰
	 * @param sPhoto����Ƭ
	 * @return
	 */
	public int addStudentInfo(String stuId,String sName,String sPwd,String sClass,String sSex,String sAge,String sAddr,String sQq,String sEmail,String sTel,byte[] sPhoto){
		String sql="insert into Student values(?,?,?,?,?,?,?,?,?,?,?,1,0)";
		List<Object> params=new ArrayList<Object>();
		params.add(stuId);   //��� ѧ����
		params.add(sName);   //����
		params.add(sPwd);    //����
		params.add(sClass);	//�༶
		params.add(sSex);	//�Ա�
		params.add(sAge); 	//����
		params.add(sAddr);	  //��ַ
		params.add(sQq);       //qq
		params.add(sEmail);	//����
		params.add(sTel);	//�绰
		params.add(sPhoto);   //��Ƭ
		return this.update(sql, params);
	}
	

	/**
	 * ��ѧ������״̬��Ϊ0
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
	 * ѧ���ύ�ɼ�
	 * @param stuid ѧ��
	 * @param qId �Ծ���
	 * @param escore ѧ���Ĵ�,��"_"�ֿ�
	 * @return
	 */
	public int submit(String stuid,String qid,String skey){ //�����Ծ����ȷ����ѧ���𰸱Ƚϣ��ó����������¿��Խ����
		List<Object> parampid=new ArrayList<Object>(); //��Ų����б�pid
		List<Object> params=new ArrayList<Object>(); //��Ų����б�pid

		int escore=0; //��¼�������ŵĳɼ�
		
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
		//д��ѧ���ɼ�
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
		String sql="select e.*,q.* from exam e,question q where e.qid=q.qid and qexists='δ��' and e.qid='"+qid+"'";
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
				String sql3="update Question set qexists='����' where qid='"+qid+"'"; 
				this.update(sql3, null); //���Ծ�״̬��Ϊ����
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
			String sql3="update Question set qexists='����' where qid='"+qid+"'"; 
			this.update(sql3, null); //���Ծ�״̬��Ϊδ��
		}
	}
	
	
	
	/**
	 * ��ѯ��ѧ�������Ծ�Ĵ�
	 * @param stuid��������ID
	 * @param qid:�Ծ�ı��
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
	 * �鿴��ѧ�������п��Եķ���
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
	 * �����Ծ���Ϣ
	 * @return
	 */
	public List<Map<String,Object>> findTest(){
		String sql="select qId,courseName,cID,sTime from Question q,questionBank qtb where q.qbid=qtb.qbid";
		return this.select(sql, null);
	}
	
	
	/**
	 * ���ҿ��Խ��
	 * @stuid ѧ�����
	 * @return
	 */
	public  List<Map<String,Object>> findExamResult(String stuid){
		List<Object> params=new ArrayList<Object>();
		String sql="select q.qid,qname,stime,escore from Exam e,Question q where q.qid=e.qid and stuid=? and qexists='����'";
		params.add(stuid);
		return this.select(sql, params);
	}
	
	/**
	 * �������е�̸��
	 * @return
	 */
	public List<Map<String,Object>> findAllTalk(){
		String sql="select * from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId";
		return this.select(sql, null);
	}
	
	/**
	 * �鿴����̸��������
	 */
	public List<Map<String,Object>> findAllTalkContent(){
		String sql="select ttId,talkcontent,tpraise,s.stuId,s.sphoto,s.sName from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId";
		return this.select(sql, null);
	}
	
	/**
	 * ���̸��
	 * @param ttid��̸����ID
	 * @param stuId:ѧ����Id
	 * @param talkcontent:̸��������
	 * @param tpraise:������
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
	 * ��ѯstuid��ѧ��������
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
	 * ���޵�ѧ��
	 * @param tpraise������
	 * @param stuId��ѧ��
	 * @return
	 */
	public int addTalkTpraise(String tpraise,String stuId){
		String sql="update talk set tpraise=? where stuId=?";
		List<Object> params=new ArrayList<Object>(); //��Ų����б�
		params.add(tpraise);
		params.add(stuId);
		
		return this.update(sql, params);
	}
	
	/**
	 * ���ҳ����лظ�������
	 * @return
	 */
	public  List<Map<String,Object>> findAllDiscussContent(){
		String sql="select * from discuss";
		return this.select(sql, null);
	}
	
	/**
	 * ����õ�����۵����лظ�
	 * @param stuid��������ѧ���Ļظ�
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
	 * ��ѯ�����۵����лظ�
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
	 * ��ӻظ�
	 * @param ttid:����ظ���ť��Ӧ����
	 * @param stuid:����ѧ����id
	 * @param DiscussContent:�ظ�������
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
