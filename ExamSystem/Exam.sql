/*
教师表（教师号，名称，密码，邮箱，电话）
考生表（考生号，名称，密码，各类信息）
试题(试题号，试题名,(保留课程号，课程名))
考试计划 (考生号，试题号，开始时间，当前时间)
考试（试题号，考生号，日期，成绩，错的题号）
*/


create table Teacher(
       tId varchar(10) check (regexp_like(tId,'T1[0-9][0-9][0-9]') )primary key,   --可查regexp_like 的用法    --教师号T1开头作为约束
       --tId varchar2(10) primary key,   --教师号
       tName varchar2(50) not null,    --教师名
       tPwd varchar2(20) not null,     --教师密码
       tphoto blob,                           --教师照片
       tSex char(2) default '男' --约束 只能为男或女                                    --性别约束男女
            constraint CK_Teacher_tSex check(tSex in('男','女')), 
       tAddr varchar2(50),      --地址   
       tTel varchar2(15)  not null unique,     --联系电话
       tEmail varchar2(20) not null unique,   --邮箱不可重复
       texists  char default 1,     --教师是否存在  默认为1存在  0不存在
       tread char default 0     --教师是否阅卷   1已阅  0未阅
)
create sequence seq_Teacher_tId
       START WITH 1002  -- 从1开始计数 
       INCREMENT BY 1    -- 每次加1个
       maxvalue 9999  --最大值为9999

delete from teacher where tid='T1007';

insert into Teacher values('T'||seq_Teacher_tId.Nextval,'周海军','a123456',null,'女','湖南衡阳',15886486651,'573059382@qq.com',1,0);
insert into Teacher values('T'||seq_Teacher_tId.Nextval,'杨婷','a123456',null,'女','湖南衡阳',15886486652,'573059381@qq.com',1,0);
insert into Teacher values('T'||seq_Teacher_tId.Nextval,'张影','a123456',null,'女','湖南衡阳',15886486653,'573059383@qq.com',1,0);

select * from teacher for update;
drop table Teacher;
select max(tid) tid from Teacher;
select * from teacher
select  substr((select max(tid) tid from Teacher),2) tid from(select max(tid) tid from Teacher);
delete from teacher
commit;

--创建班级
create table Classes(
       cId varchar2(20) check (regexp_like(cId,'C1[0-9][0-9][0-9]') )primary key,  --班级编号
       cName varchar2(20) not null,
       cexists char default 1 --状态 1存在 0不存在
);

create sequence seq_Classes_cId
       START WITH 1000  -- 从1开始计数 
       INCREMENT BY 1    -- 每次加1个
       

insert into Classes values('C'||seq_Classes_cId.nextval,'源辰C19班',1);
insert into Classes values('C'||seq_Classes_cId.nextval,'源辰C20班',1);
insert into Classes values('C1001','源辰C18班',1);


update classes set cexists=1
select * from Classes 

delete from Classes where cid>'C1001'
drop table classes;
drop sequence seq_Classes_cId;
commit;

--创建考生（用户）表
create table Student(
       stuId varchar(10) check (regexp_like(stuId,'S1[0-9][0-9][0-9]') )primary key,     --学生号S1开头作为约束  
   --  stuId varchar2(10) primary key,
       sName varchar2(50) not null,                                                     --学生名
       sPwd varchar2(20) not null,                                                      --密码
       cID varchar2(20)                                                                 --学生所在班级
               constraint FK_QClass_Student_sId references Classes(cId),
       sSex varchar(10) default '男' --约束 只能为男或女                                    --性别约束男女
            constraint CK_Student_sSex check(sSex in('男','女')),
       sAge varchar(20), 
            constraint CK_Student_sAge check(sAge between 7 and 30),                   --年龄约束7-30岁
       sAddr varchar2(50),                                                             --地址
       sqq    varchar2(20)  not null unique,                                          --联系QQ
	     semail varchar2(20) not null unique,       --邮箱不可重复
       sTel varchar2(15)  not null unique,     --联系电话
       sphoto blob,                 --学生照片
       sexists  char default 1,     --学生是否存在  默认为1存在  0不存在
       sstate   char               --学生考试状态     1已考  0未考
)
insert into Student values('S1001','张三','a123456','C1000','男',20,'湖南长沙',5702546,'573059384@qq.com','15886480861',null,1,1);
insert into Student values('S1002','李四','a123456','C1001','男',21,'湖南衡阳',5456465456,'573059385@qq.com','15886480862',null,1,1);
insert into Student values('S1003','王五','a123456','C1001','男',21,'湖南衡阳',5457465446,'573059386@qq.com','15886450863',null,1,1);


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

--创建课程表
create table course(
       courseId int primary key,   --课程编号
       courseName varchar2(100) not null unique,    --课程名称
       courseexists  char default 1     --学生是否存在  默认为1存在  0不存在
);

create sequence seq_course_courseId
       START WITH 1  -- 从1开始计数 
       INCREMENT BY 1    -- 每次加1个

insert into course values(seq_course_courseId.nextval,'Java',1);
insert into course values(seq_course_courseId.nextval,'Oracle',1);

select * from course;
select count(*) from course;

drop table course;
drop sequence seq_course_courseId;


--创建题库表(题源)
create table questionBank(
       qbId int primary key,         --题目编号
       courseName varchar2(100)    --课程名称
                  constraint FK_courseName references course(courseName),
       qbContent varchar2(1000) not null unique,     --题目内容
       qbOption varchar2(500),   --题目选项
       rkey varchar2(500) not null,       --正确答案
       qbKind varchar2(100) not null       --题目种类
);
create sequence seq_questionBank_qId
       START WITH 1  -- 从1开始计数 
       INCREMENT BY 1    -- 每次加1个
       


insert into questionBank values(seq_questionBank_qId.nextval,'Java','1+1=?','A.1_B.2_C.3_D.4','B','单选题');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','1+2=?','A.1_B.2_C.3_D.4','C','单选题');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','1+3=?','A.1_B.2_C.3_D.4','D','单选题');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','1?','','1','填空题');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','2?','','2','填空题');
insert into questionBank values(seq_questionBank_qId.nextval,'Java','3?','','3','填空题');

insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','2+1=?','A.1_B.2_C.3_D.4','C','单选题');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','2+2=?','A.1_B.2_C.3_D.4','D','单选题');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','2+3=?','A.5_B.2_C.3_D.4','A','单选题');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','A?','','1','填空题');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','B?','','2','填空题');
insert into questionBank values(seq_questionBank_qId.nextval,'Oracle','C?','','3','填空题');

--seq_questionBank_qId.Currval
select qbContent,rkey from questionBank where qbid=16


select * from questionBank order by qbid;
select count(*) from questionBank where courseName='Java'
select distinct qbKind from questionBank
select * from questionBank where qbKind='单选题' and courseName='Java'

update questionBank set coursename='HTML' where qbid='4';
update questionBank set coursename='Java' where qbid='4';


drop table questionBank;
drop sequence seq_questionBank_qId;
select * from teacher for update;
commit;


--创建试卷  
create table Question(
       cID varchar2(20)                                                   --给那个班的试卷
               constraint FK_QClass_Question_cID references Classes(cId),
       tId varchar2(20) 
           constraint FK_Teacher_Question_tId references Teacher(tId),
       qId varchar2(10),  --试卷编号
       qName varchar2(50) not null unique,     --试卷名称 
       qbId varchar2(400),            --题目编号 题号 包含本试卷的所有题目
       rkey varchar2(400),            --正确答案
       qnumber varchar2(10)   not null,  --试题的数量
       sTime date,      --教师设置开考时间
       duration int,             --持续时间(考试时间)
       --eTime date default sysdate, --学生开始考试时间默认为系统时间
       qexists varchar2(10)      --试题存在状况  
               constraint FK_Question_qexists check(qexists in ('销毁','未考','开考','未阅','已阅')),
       --预留学期
       qterm varchar2(20),    --期中   --期末  
             constraint PK_Question primary key (qId)  -- 联合主键                 
)
drop table Question;


select rkey,skey from Question q,Exam e where q.qid=e.qid and stuid='S1003' and qName='Test';
select escore,qname,Stuid from Question q,Exam e where q.qid=e.qid and stuid='S1001' 

create sequence seq_question_qid
       START WITH 20150000  -- 从1开始计数 
       INCREMENT BY 1    -- 每次加1个
       
drop sequence seq_question_qid

select * from Question where qexists not in ('销毁') order by qbid
select * from question where qid='20150000'; 

select * from Question where qexists not in ('销毁') and cid='C1000'
select count(*) from Question where qexists not in ('销毁') and cid='C1000'


update Question set qexists='未考';
update Question set stime=null ;
update Question set stime=to_date('2015/09/01 16:08','yyyy/mm/dd hh24:mi'), qexists='开考' where qname='Oracle'


select s.stuid,sname,qname,escore from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid='C1000' and q.qexists='已阅' order by s.stuid
select * from question
delete from question
commit

select seq_question_qid.nextval from dual


       
drop table question;
drop sequence seq_question_qid;
--创建考试表
create table Exam(   
	   qId varchar2(10)                  --试题
			      constraint FK_Exam_Question_qId references Question(qId),
     stuId varchar2(10)                --学生
            constraint FK_Exam_Student_stuId references Student(stuId), 
     --qstate  int,         --试题状态    0未考  1开考  2未阅  3已阅
     eScore int,                   --成绩  
     skey varchar2(1000),       --学生答案
     eexists char  default 1, --考试表是否存在    1存在  0已删除
             constraint PK_Exam primary key (qId,stuId)  -- 联合主键   
)



select count(*) from exam where stuid in('S1001');

select escore from exam where stuid='S1001' and qId=20150001

select avg(escore) from exam where qid='20150000';

select * from Question where qexists not in ('销毁') and cid='C1000' order by qid;

select avg(escore) from exam group by qid
select count(*) from Question where qexists in ('已阅') and cid='C1001'
select * from Exam;
delete  from exam;
drop table Exam;
commit;

select s.stuid,sname,qname,escore from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid='C1001'
select count(*) from student s,question q,exam e where s.stuid=e.stuid and e.qid=q.qid and s.cid='C1001'
--创建吐槽区
create table Talk(
    ttid  int  primary key,      --谈话id    
    stuId varchar2(10)              --获取学生编号
            constraint FK_Talk_Student_stuId references Student(stuId),
    
    talkcontent varchar2(1000) not null,      --谈话内容 
    tpraise   int, 
    ttexists  char default 1    --谈话存在的状态    1存在  0已删除
)
select ttId,talkcontent,tpraise,s.sName from talk t, Student s where  t.stuId=s.stuId and ttexists=1 order by ttId
update talk set tpraise=4 where stuId='S1001'
select * from talk

--创建标识列
create sequence seq_talk_ttid --序列名 谈话的楼层
        START WITH 1  -- 从1开始计数 
       INCREMENT BY 1    -- 每次加1个

insert into Talk values(seq_talk_ttid.nextval,'S1002','谁说的',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1001','今年考卷好难！',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1003','好无聊啊！',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1004','组团求约！',1,1);
insert into Talk values(seq_talk_ttid.nextval,'S1021','约约约！',1,1);



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


--创建评论版
create table Discuss(     
       did int,     --评论id
       ttid int     --获取谈话id
              constraint FK_talk_ttid references Talk(ttid),
       stuId varchar2(10)              --获取评论学生编号
            constraint FK_Student_stuId references Student(stuId),
       discusscontent varchar2(500),    --评论内容
       dexists char default 1  --评论的状态    1存在  0不存在
)
create sequence seq_Discuss_did --序列名 评论的楼层
        START WITH 1  -- 从1开始计数 
       INCREMENT BY 1    -- 每次加1个

insert into Discuss values(seq_Discuss_did.nextval,1,'S1001','说的对！',1);
insert into Discuss values(seq_Discuss_did.nextval,2,'S1001','说的对1111111！',1);
insert into Discuss values(seq_Discuss_did.nextval,3,'S1002','我说的',1);
insert into Discuss values(seq_Discuss_did.nextval,2,'S1021','无语！',1);
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
       
       

