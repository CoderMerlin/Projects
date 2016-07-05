/*
��ʦ����ʦ�ţ����ƣ����룬���䣬�绰��
�����������ţ����ƣ����룬������Ϣ��
����(����ţ�������,(�����γ̺ţ��γ���))
���Լƻ� (�����ţ�����ţ���ʼʱ�䣬��ǰʱ��)
���ԣ�����ţ������ţ����ڣ��ɼ��������ţ�
*/


create table Teacher(
       tId varchar(10) check (regexp_like(tId,'T1[0-9][0-9][0-9]') )primary key,   --�ɲ�regexp_like ���÷�    --��ʦ��T1��ͷ��ΪԼ��
       --tId varchar2(10) primary key,   --��ʦ��
       tName varchar2(50) not null,    --��ʦ��
       tPwd varchar2(20) not null,     --��ʦ����
       tphoto blob,                           --��ʦ��Ƭ
       tSex char(2) default '��' --Լ�� ֻ��Ϊ�л�Ů                                    --�Ա�Լ����Ů
            constraint CK_Teacher_tSex check(tSex in('��','Ů')), 
       tAddr varchar2(50),      --��ַ   
       tTel varchar2(15)  not null unique,     --��ϵ�绰
       tEmail varchar2(20) not null unique,   --���䲻���ظ�
       texists  char default 1,     --��ʦ�Ƿ����  Ĭ��Ϊ1����  0������
       tread char default 0     --��ʦ�Ƿ��ľ�   1����  0δ��
)
create sequence seq_Teacher_tId
       START WITH 1002  -- ��1��ʼ���� 
       INCREMENT BY 1    -- ÿ�μ�1��
       maxvalue 9999  --���ֵΪ9999

delete from teacher where tid='T1007';

insert into Teacher values('T'||seq_Teacher_tId.Nextval,'�ܺ���','a123456',null,'Ů','���Ϻ���',15886486651,'573059382@qq.com',1,0);
insert into Teacher values('T'||seq_Teacher_tId.Nextval,'����','a123456',null,'Ů','���Ϻ���',15886486652,'573059381@qq.com',1,0);
insert into Teacher values('T'||seq_Teacher_tId.Nextval,'��Ӱ','a123456',null,'Ů','���Ϻ���',15886486653,'573059383@qq.com',1,0);

select * from teacher for update;
drop table Teacher;
select max(tid) tid from Teacher;
select * from teacher
select  substr((select max(tid) tid from Teacher),2) tid from(select max(tid) tid from Teacher);
delete from teacher
commit;

--�����༶
create table Classes(
       cId varchar2(20) check (regexp_like(cId,'C1[0-9][0-9][0-9]') )primary key,  --�༶���
       cName varchar2(20) not null,
       cexists char default 1 --״̬ 1���� 0������
);

create sequence seq_Classes_cId
       START WITH 1000  -- ��1��ʼ���� 
       INCREMENT BY 1    -- ÿ�μ�1��
       

insert into Classes values('C'||seq_Classes_cId.nextval,'Դ��C19��',1);
insert into Classes values('C'||seq_Classes_cId.nextval,'Դ��C20��',1);
insert into Classes values('C1001','Դ��C18��',1);


update classes set cexists=1
select * from Classes 

delete from Classes where cid>'C1001'
drop table classes;
drop sequence seq_Classes_cId;
commit;

--�����������û�����
create table Student(
       stuId varchar(10) check (regexp_like(stuId,'S1[0-9][0-9][0-9]') )primary key,     --ѧ����S1��ͷ��ΪԼ��  
   --  stuId varchar2(10) primary key,
       sName varchar2(50) not null,                                                     --ѧ����
       sPwd varchar2(20) not null,                                                      --����
       cID varchar2(20)                                                                 --ѧ�����ڰ༶
               constraint FK_QClass_Student_sId references Classes(cId),
       sSex varchar(10) default '��' --Լ�� ֻ��Ϊ�л�Ů                                    --�Ա�Լ����Ů
            constraint CK_Student_sSex check(sSex in('��','Ů')),
       sAge varchar(20), 
            constraint CK_Student_sAge check(sAge between 7 and 30),                   --����Լ��7-30��
       sAddr varchar2(50),                                                             --��ַ
       sqq    varchar2(20)  not null unique,                                          --��ϵQQ
	     semail varchar2(20) not null unique,       --���䲻���ظ�
       sTel varchar2(15)  not null unique,     --��ϵ�绰
       sphoto blob,                 --ѧ����Ƭ
       sexists  char default 1,     --ѧ���Ƿ����  Ĭ��Ϊ1����  0������
       sstate   char               --ѧ������״̬     1�ѿ�  0δ��
)
insert into Student values('S1001','����','a123456','C1000','��',20,'���ϳ�ɳ',5702546,'573059384@qq.com','15886480861',null,1,1);
insert into Student values('S1002','����','a123456','C1001','��',21,'���Ϻ���',5456465456,'573059385@qq.com','15886480862',null,1,1);
insert into Student values('S1003','����','a123456','C1001','��',21,'���Ϻ���',5457465446,'573059386@qq.com','15886450863',null,1,1);


select * from student for update;
delete from student where stuid='S1003';


UPDATE Student SET sexists=1
update student set spwd='a123456' where semail='12345678@qq.com';
select * from student where cid='C1001' and sexists=1
select  substr((select max(stuid) stuid from Student),2) stuid from(select max(stuid) stuid from Student)

delete from student where stuid='S1022';
delete from exam where stuid='S1021';
select * from Student
select stuid,sName from Student where cid='C1001'
select count(*) from student where cid='C1001'
drop table student
commit;

--�����γ̱�
create table course(
       courseId int primary key,   --�γ̱��
       courseName varchar2(100) not null unique,    --�γ�����
       courseexists  char default 1     --ѧ���Ƿ����  Ĭ��Ϊ1����  0������
);

create sequence seq_course_courseId
       START WITH 1  -- ��1��ʼ���� 
       INCREMENT BY 1    -- ÿ�μ�1��

insert into course values(seq_course_courseId.nextval,'Java',1);
insert into course values(seq_course_courseId.nextval,'Oracle',1);

select * from course;
select count(*) from course;

drop table course;
drop sequence seq_course_courseId;


--��������(��Դ)
create table questionBank(
       qbId int primary key,         --��Ŀ���
       courseName varchar2(100)    --�γ�����
                  constraint FK_courseName references course(courseName),
       qbContent varchar2(1000) not null unique,     --��Ŀ����
       qbOption varchar2(500),   --��Ŀѡ��
       rkey varchar2(500) not null,       --��ȷ��
       qbKind varchar2(100) not null       --��Ŀ����
);
create sequence seq_questionBank_qId
       START WITH 1  -- ��1��ʼ���� 
       INCREMENT BY 1    -- ÿ�μ�1��
       


insert into questionBank values(seq_questionBank_qId.nextval,'Java','1+1=?','A.1_B.2_C.3_D.4','B','��ѡ��');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','1+2=?','A.1_B.2_C.3_D.4','C','��ѡ��');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','1+3=?','A.1_B.2_C.3_D.4','D','��ѡ��');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','1?','','1','�����');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','2?','','2','�����');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','3?','','3','�����');

insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','2+1=?','A.1_B.2_C.3_D.4','C','��ѡ��');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','2+2=?','A.1_B.2_C.3_D.4','D','��ѡ��');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','2+3=?','A.5_B.2_C.3_D.4','A','��ѡ��');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','A?','','1','�����');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','B?','','2','�����');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','C?','','3','�����');

--seq_questionBank_qId.Currval
select qbContent,rkey from questionBank where qbid=16


select * from questionBank order by qbid;
select count(*) from questionBank where courseName='Java'
select distinct qbKind from questionBank
select * from questionBank where qbKind='��ѡ��' and courseName='Java'

update questionBank set coursename='HTML' where qbid='4';
update questionBank set coursename='Java' where qbid='4';


drop table questionBank;
drop sequence seq_questionBank_qId;
select * from teacher for update;
commit;


--�����Ծ�  
create table Question(
       cID varchar2(20)                                                   --���Ǹ�����Ծ�
               constraint FK_QClass_Question_cID references Classes(cId),
       tId varchar2(20) 
           constraint FK_Teacher_Question_tId references Teacher(tId),
       qId varchar2(10),  --�Ծ���
       qName varchar2(50) not null unique,     --�Ծ����� 
       qbId varchar2(400),            --��Ŀ��� ��� �������Ծ��������Ŀ
       rkey varchar2(400),            --��ȷ��
       qnumber varchar2(10)   not null,  --���������
       sTime date,      --��ʦ���ÿ���ʱ��
       duration int,             --����ʱ��(����ʱ��)
       --eTime date default sysdate, --ѧ����ʼ����ʱ��Ĭ��Ϊϵͳʱ��
       qexists varchar2(10)      --�������״��  
               constraint FK_Question_qexists check(qexists in ('����','δ��','����','δ��','����')),
       --Ԥ��ѧ��
       qterm varchar2(20),    --����   --��ĩ  
             constraint PK_Question primary key (qId)  -- ��������                 
)
drop table Question;


select rkey,skey from Question q,Exam e where q.qid=e.qid and stuid='S1003' and qName='Test';
select escore,qname,Stuid from Question q,Exam e where q.qid=e.qid and stuid='S1001' 

create sequence seq_question_qid
       START WITH 20150000  -- ��1��ʼ���� 
       INCREMENT BY 1    -- ÿ�μ�1��
       
drop sequence seq_question_qid

select * from Question where qexists not in ('����') order by qbid
select * from question where qid='20150000'; 

select * from Question where qexists not in ('����') and cid='C1000'
select count(*) from Question where qexists not in ('����') and cid='C1000'


update Question set qexists='δ��';
update Question set stime=null ;
update Question set stime=to_date('2015/09/01 16:08','yyyy/mm/dd hh24:mi'), qexists='����' where qname='Oracle'


select s.stuid,sname,qname,escore from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid='C1000' and q.qexists='����' order by s.stuid
select * from question
delete from question
commit

select seq_question_qid.nextval from dual


       
drop table question;
drop sequence seq_question_qid;
--�������Ա�
create table Exam(   
	   qId varchar2(10)                  --����
			      constraint FK_Exam_Question_qId references Question(qId),
     stuId varchar2(10)                --ѧ��
            constraint FK_Exam_Student_stuId references Student(stuId), 
     --qstate  int,         --����״̬    0δ��  1����  2δ��  3����
     eScore int,                   --�ɼ�  
     skey varchar2(1000),       --ѧ����
     eexists char  default 1, --���Ա��Ƿ����    1����  0��ɾ��
             constraint PK_Exam primary key (qId,stuId)  -- ��������   
)



select count(*) from exam where stuid in('S1001');

select escore from exam where stuid='S1001' and qId=20150001

select avg(escore) from exam where qid='20150000';

select * from Question where qexists not in ('����') and cid='C1000' order by qid;

select avg(escore) from exam group by qid
select count(*) from Question where qexists in ('����') and cid='C1001'
select * from Exam;
delete  from exam;
drop table Exam;
commit;

select s.stuid,sname,qname,escore from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid='C1001'
select count(*) from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid='C1001'
--�����²���
create table Talk(
    ttid  int  primary key,      --̸��id    
    stuId varchar2(10)              --��ȡѧ�����
            constraint FK_Talk_Student_stuId references Student(stuId),
    
    talkcontent varchar2(1000) not null,      --̸������ 
    tpraise   int, 
    ttexists  char default 1    --̸�����ڵ�״̬    1����  0��ɾ��
)
select ttId,talkcontent,tpraise,s.sName from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId
update talk set tpraise=4 where stuId='S1001'
select * from talk

--������ʶ��
create sequence seq_talk_ttid --������ ̸����¥��
        START WITH 1  -- ��1��ʼ���� 
       INCREMENT BY 1    -- ÿ�μ�1��

insert into Talk values(seq_talk_ttid.nextval,'S1002','˭˵��',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1001','���꿼����ѣ�',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1003','�����İ���',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1004','������Լ��',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1021','ԼԼԼ��',1,1);



drop table talk
drop sequence seq_talk_ttid
commit
select * from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId;
select tpraise from talk ;
update talk set tpraise=2 where stuId='S1001';
select talkcontent from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId;
select ttId,talkcontent,tpraise from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId;
select ttId,talkcontent,tpraise,s.sName from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId
select sname from student where stuid='S1001';


--�������۰�
create table Discuss(     
       did int,     --����id
       ttid int     --��ȡ̸��id
              constraint FK_talk_ttid references Talk(ttid),
       stuId varchar2(10)              --��ȡ����ѧ�����
            constraint FK_Student_stuId references Student(stuId),
       discusscontent varchar2(500),    --��������
       dexists char default 1  --���۵�״̬    1����  0������
)
create sequence seq_Discuss_did --������ ���۵�¥��
        START WITH 1  -- ��1��ʼ���� 
       INCREMENT BY 1    -- ÿ�μ�1��

insert into Discuss values(seq_Discuss_did.nextval,1,'S1001','˵�Ķԣ�',1);
insert into Discuss values(seq_Discuss_did.nextval,2,'S1001','˵�Ķ�1111111��',1);
insert into Discuss values(seq_Discuss_did.nextval,3,'S1002','��˵��',1);
insert into Discuss values(seq_Discuss_did.nextval,2,'S1021','���',1);
select * from discuss;

select did,discusscontent  from talk t,discuss d where t.ttid=d.ttid and  d.stuid='S1001' and d.ttid=1;

select discusscontent, d.ttid, d.stuid, sname from talk t,discuss d,student s where t.ttid=d.ttid and  d.stuid=s.stuid  and d.ttid=1 ; 
commit;
select t.ttid,t.stuid,t.talkcontent,d.did,d.stuid,d.discusscontent,sname from discuss d,talk t, student s 
       where d.ttid=t.ttid and  s.stuid=d.stuid and t.Stuid='S1002';
drop table discuss;
drop sequence seq_Discuss_did;

select t.ttid,t.stuid,t.talkcontent,d.did,d.stuid,d.discusscontent,sname from discuss d,talk t, student s 
       where d.ttid=t.ttid and  s.stuid=d.stuid and t.Stuid='S1003';
       
       

