--1.注册  （用户、管理员)
--注册时，用户填写的资料
create table yonghuzc (
	yhzcid int primary key,   --用户注册id
	yhname varchar2(100)  unique, --用户注册账号名
	yhemail varchar2(100) unique,  --用户注册邮箱
	yhpwd varchar2(100) not null,  --密码
	yhzctime date,   --用户注册时间
	yhzcstatus int,  --注册用户的状态  0表示不存在  1表示存在
	yhzcyl1  varchar2(100),  --预留字段1
	yhzcyl2  varchar2(100)   --预留字段2 f
)
create sequence seq_yonghuzc_yhzcid start with 1001 increment by 1;
drop sequence seq_yonghuzc_yhzcid;

--增删改查
insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'Mi丶','573059382@qq.com','a',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'黄迈1','573059381@qq.com','a',to_date('2015-12-30 11:15:44','yyyy-MM-dd hh:mi:ss'),1,'','');
insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'haha','573059381@qq.com','123456',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
drop table yonghuzc;
select * from yonghuzc;
commit;  --提交
--用户
create table yonghu(
   yhid int primary key,            --用户信息id    
   yhzcid int
		 constraints FK_yonghuzc_yhzcid references yonghuzc(yhzcid), --用户注册id    外键
   yhzsname varchar2(100),  --真实姓名
   yhsex  varchar2(10) default '男'
              -- constraint CK_yonghu_sex check( yhsex in('男',女))
              constraint CK_yonghu_yhsex check( yhsex='男' or yhsex='女'), --用户性别 
  
   yhage  int default 16
              -- constraint CK_yonghu_age check( yhage between 6 and 150),
              constraint CK_yonghu_yhage check( yhage>=6 and yhage<=150),          --用户年龄 限制在6到150岁
   yhphone varchar2(20) not null unique,  --用户手机号码
   yhindentity  varchar2(50), --用户身份证
   yhqq    varchar2(50),  --用户qq
   yhbirthday date,  --用户生日
   yhaddr varchar2(100), ---用户地址
   yhphoto clob,         --用户头像
   yhftnum   varchar2(1000),  --用户发帖次数
   yhdownload varchar2(2000),  --用户下载文件
   yhupload   varchar2(2000),  --用户上传文件
   yhjf      varchar2(100),  --用户积分
   yhqd   int,   --用户签到
   yhjy  int,   --用户经验
   yhzhtime  date,   --用户最后登录时间 
   yhqianming  varchar2(200),  --用户签名
   yhstatus int,  --用户状态   0表示用户不存在   1表示用户存在
   yhyl1   varchar2(100),   --预留字段1
   yhyl2   varchar2(100)   --预留字段2
)
select * from yonghu;
drop table yonghu;
create sequence seq_yonghu_yhid start with 1001 increment by 1;
drop sequence seq_yonghu_yhid;

--管理员表
create table guanli(
   glid int   primary key,                --管理员id
   glname  varchar2(50) not null unique,     --管理员名
   glpwd  varchar2(50) not null,   --管理员密码
   glsex  varchar2(10) default '男'
          constraint CK_guanli_glsex check( glsex='男' or glsex='女'), --用户性别 ,      --管理性别
   glemail varchar2(50) not null unique,    --管理员邮箱
   glphone varchar2(20),    --管理员电话
   glzsname varchar2(50), --管理员真实名字
   glindentity varchar2(50), --管理员身份证  
   glzhtime date,         --管理员最后登录时间
   glstatus  int,   --管理员状态   0不存在  1表示存在
   glyl1   varchar2(1000),        --预留字段1   --用来存照片
   glyl2   varchar2(1000)      --预留字段2
);
select * from Guanli where glid in(1001)
create sequence seq_guanli_glid start with 1001 increment by 1;
drop sequence seq_guanli_glid
--增删改查
insert into guanli values(seq_guanli_glid.nextval,'zs123','a123456','男','573059384@qq.com','15886480888','黄迈','65164651132157888',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi','a','男','573059321@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi1','a','男','573059381@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi2','a','男','573059383@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi3','a','男','573059385@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi4','a','男','573059386@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi5','a','男','573059387@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi6','a','男','573059388@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi7','a','男','573059389@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi8','a','男','573059380@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into guanli values(seq_guanli_glid.nextval,'Mi9','a','男','573059312@qq.com','15886480862','黄迈','651646511321546',to_date('2016-1-8 17:05:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
drop table guanli;
update GuanLi set glzhtime=to_date('2016-1-8 17:05:44','yyyy-MM-dd hh24:mi:ss') where glid=1024; 


update guanli set glindentity='4516516516516166516' where glid=1002;
select * from guanli;
delete guanli where glid=1023;
select glid,glname,glpwd from Guanli where glname="zs123" and glpwd=?
select glemail from guanli where glemail='57305938531@qq.com';
select * from Guanli where glid in(1021,1023)
--2.论坛
--===========================================================
--公告表
create table gonggao(
   gid int primary key,  --公告id
   gtitle varchar2(500) not null,  --公告标题
   gtext varchar2(500) not null,  --公告内容
   gtime date,        --公告创建时间
   gstatus varchar2(50), --状态       -- 0 表示删除   1表示存在
   ggyl1   varchar2(100),        --预留字段1
   ggyl2   varchar2(100)        --预留字段2
)

--增删改查
create sequence seq_gonggao_gid start with 1001 increment by 1;
insert into gonggao values(seq_gonggao_gid.nextval,'维护通知','2015年12月30号晚上10点社区进行维护,维护时间为4小时。',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
drop table gonggao;
update gonggao set status=0 where gid=1002;
select * from gonggao;
drop sequence seq_gonggao_gid
select * from (select a.*,rownum rn from (select * from gonggao gg order by gid) a where rownum<=10) b where rn>0
select count(gid) from gonggao
--论坛栏目表
 --论坛栏目表
create table luntan(
   ltid int primary key,            --论坛编号
   ltname varchar2(50) not null,     --论坛名称
   lttime date,     --论坛栏目创建时间
   ltstatus int, --状态       -- 0 表示删除   1表示存在
   ltyl1  varchar2(2000),        --类型
   ltyl2  varchar2(2000)        --图片
)

 select lt.ltname,yhzc.yhname,tz.tzname,tz.tzname,
 tz.tzclick,tz.tztext,tz.tztime from tiezi tz,
 luntan lt,yonghu yh, yonghuzc yhzc where status=1
 and tz.ltid=lt.ltid and tz.yhid=yh.yhid and yh.yhzcid=yhzc.yhzcid 
 and tztext like CONCAT(CONCAT('%', '人道主义'), '%') order by tzclick 


select * from (select a.*,rownum rn from (select * from luntan where ltstatus=1 order by ltid) a where  10 >=rownum)b where rn>0
--增删改查
create sequence seq_luntan_ltid start with 1001 increment by 1;
insert into luntan values(seq_luntan_ltid.nextval,'站长互助',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'交流区','');
insert into luntan values(seq_luntan_ltid.nextval,'建站交流',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'交流区','');

insert into luntan values(seq_luntan_ltid.nextval,'站长互助',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'文化交流社区','');

drop table luntan;
drop sequence seq_luntan_ltid
update luntan set status=0 where gid=1002;
select * from luntan;
 select yhid from (select distinct yhname,yhid  from luntan  where status=1) where ltname='新手水吧'
 SELECT top1 from  table where sid>200
order by sid

-------------------------------------------------------------------------------------

--文章类型表 (点击时候查看的文章)
create table wenzhangtype(
	wztypeid int  primary key,  --文章类型id
 	wzname varchar2(100),
 	status int, --状态   0表示不存在  1表示存在
	wzyl1 varchar2(100),         --预留字段1
	wzyl2 varchar2(100)        --预留字段2
);


create sequence seq_wenzhangtype_wztypeid start with 1001 increment by 1;
select * from  wenzhangtype;
drop sequence seq_wenzhangtype_wztypeid;
drop table wenzhangtype;
);
alter table wenzhangtype rename column  wztype to wzname
select wztypeid,wzname,status from wenzhangtype where status=1
create sequence seq_wenzhangtype_wztypeid start with 1001 increment by 1;
insert into wenzhangtype values(seq_wenzhangtype_wztypeid.nextval,'古代绘画',1,'','');
select * from  wenzhangtype;
drop sequence seq_wenzhangtype_wztypeid;
drop table wenzhangtype;

--文章表
create table wenzhang(
   wzid int primary key,  --文章id
   glid int
		constraints FK_guanli_glid references guanli(glid),   --管理员id   用来添加文章 
   wztypeid int 
		constraints FK_wenzhangtype_wztypeid references wenzhangtype(wztypeid),   --文章类型id
   wztitle varchar2(500) not null,  --文章标题
   wztext varchar2(4000) not null,   --文章内容
   wzpicture  clob,     --文章图片  
   wzdianjinnum int,  --文章点击数
   wzweight int,  --文章权重
   wzstatus int,      --1表示存在，0表示不存在	
   wzyl1  varchar2(100),        --预留字段1   //文章作者
   wzy12  varchar2(100)        --预留字段2
);
--首页查询文章等
select wzid,wzyl1,wztitle,wztext,wzpicture from wenzhang where wzstatus=1;

create sequence seq_wenzhang_wzid start with 1001 increment by 1;
drop sequence seq_wenzhang_wzid;

select * from wenzhang;
drop table wenzhang;
create sequence seq_wenzhang_wzid start with 1001 increment by 1;


--对文章评论表  
create table wzyhpinglun(
   wzplid int primary key,  --评论id
   yhzcid int 
        constraints FK_yh_wzt_yhid references yonghuzc(yhzcid),     --用户id    外键
   wzpltext  varchar2(500) not null,  --评论内容
   wzpltime  date,     --评论时间
   wzid int 
		constraints FK_wz_wzt_wid references wenzhang(wzid),   --文章id
   status int, --状态       -- 0 表示删除   1表示存在
   wzpl1   varchar2(100),        --预留字段1
   wzpl2  varchar2(100)        --预留字段2
);

create sequence seq_wzyhpinglun_wzplid start with 1001 increment by 1;
drop sequence seq_wzyhpinglun_wzplid;
drop table wzyhpinglun;
select * from wzyhpinglun;


--点赞表
create table wzsupportnum(  
   wzsuid int primary key,  --点赞id
   yhid int 
        constraints FK_yonghu_yhid references yonghu(yhid),   --用户id    外键
   wzplid int 
		constraints FK_wzyhpinglun_wzplid references wzyhpinglun(wzplid),   --文章评论id    外键
   supporttime  date,  --点赞时间
   supportstatus  int   --点赞类型    0表示已取消赞  1表示点赞
   wzsuyl varchar2(100),  --预留
   wzsuyl varchar2(100)   --预留
)

drop table wzsupportnum;


----------------------------------------------------------------------------------------



--帖子表
create table tiezi(
   tid   int primary key,       --帖子id
   ltid  int
         constraints FK_luntan_ltid references luntan(ltid),   --论坛栏目id    外键
   yhid  int
          constraints FK_yonghu_yhid references yonghu(yhid),  --用户id   外键
   tzname  varchar2(50),  --帖子名称
   tzzy  varchar2(2000),  --帖子摘要
   tztime  date,  --发帖时间
   weight int,       --权重      -1 表示置顶  从0开始
   tztext  clob,        --帖子详细内容
   tzphoto  clob,       --帖子图片
   tzclick int,--帖子点击数
   status int,  --状态 0 表示删除   1表示存在
   tzyl1  varchar2(100),        --预留字段1
   tzyl2  varchar2(100)       --预留字段2
   )
drop table tiezi;
create sequence seq_tiezi_tid start with 1001 increment by 1;
drop sequence seq_tiezi_tid;
insert into tiezi (tid, ltid, yhid,tzname,tzzy,tztime,weight,tztext,tzphoto,tzclick,status,tzyl1,tzyl2)
values (1001,1001,1001,'接口是干嘛用的?','首页上全都是水，你们就不怕山洪暴发么,我来点干货填一填,中午休息的时候看了这个帖子',
to_date('2015-10-10 00:00:00','yyyy-mm-dd hh24:mi:ss'),1,'先来看看什么样的语言有接口语法注意这里说的是编写程序的时候使用的接口语法至于其他形式完成接口功能的方式不讨论Java有，C++有，C#有JavaScript没有，Python没有，Ruby没有',
'',11,1,'','');






 

----------------------------------------------------这里2016.6.24
create table huitie(
   htid int primary key,  --回帖id
   yhid int 
        constraints FK_yonghu_huitie_yhid references yonghu(yhid),     --用户id    外键
   tid int 
		constraints FK_tiezi_tid references tiezi(tid),   --帖子id
   httext  clob,  --留言内容
   httime  date,     --留言时间
   htdianzan int,--回帖点赞
   htstatus int, --状态       -- 0 表示删除   1表示存在
   htyl1   varchar2(100),        --预留字段1
   htyl2  varchar2(100)        --预留字段2
)

drop table huitie;
drop sequence seq_huitie_htid;



select tid from (select distinct tzname,tid  from tiezi  where status=1) where tzname='zxl'
create sequence seq_huitie_htid start with 1001 increment by 1;
select ht.*,count(ht.htid),yh.yhzsname,yh.yhphoto,tz.tzname from huitie ht,yonghu yh,tiezi tz where ht.yhid=yh.yhid and ht.tid=tz.tid and ht.htstatus=1 order by ht.httime desc"
"select ht.*,yh.yhzsname,tz.tzname from huitie ht,yonghu yh,tiezi tz  where ht.htid=1002 and ht.yhid=yh.yhid and ht.tid=tz.tid
select ht.*,yh.yhzsname,tz.tzname from huitie ht,yonghu yh,tiezi tz where ht.yhid=yh.yhid and ht.tid=tz.tid and ht.htstatus

--作品内容跟文章可以建一联合表




--弘道首页
--======================================================================================
--一级导航栏表
create table hd_nav1(
    nav1id   int  primary key, --一级导航栏id
    nav1name  varchar(20) not null,  --导航栏名称 
    nav1status int,  --导航栏状态   0表示不存在 1表示存在
    nav1yl1   varchar2(100),        --预留字段1
    nav1yl2   varchar2(100)        --预留字段2
)
create sequence seq_hd_nav1_nav1id start with 1001 increment by 1;

--二级导航栏表
create table hd_nav2(
	nav2id   int  primary key, --导航栏id
	nav1id int 
        constraints FK_hd_nav1_nav1id references hd_nav1(nav1id),   --一级导航栏Id
    nav2name  varchar(20) not null,  --导航栏名称 
	nav2status int,  --导航栏状态   0表示不存在 1表示存在	
    nav2yl1   varchar2(100),        --预留字段1
    nav2yl2   varchar2(100)        --预留字段2
);
drop table hd_nav2;

create sequence seq_hd_nav2_nav2id start with 1001 increment by 1;
-------------------------------------------------------------------------Zheli 2016.6.24
--首页图片表  
create table index_img(
   index_imgid int  primary key,  --首页图片id
   yhid  int
          constraints FK_index_img_yonghu_yhid references yonghu(yhid),    --用户id (放用户图片)  外键
   img_position int,    --广告位置   
   img_begin date, --广告开始时间 
   img_end  date, --广告结束时间
   img_weight int,   --权重
   img_addr  varchar2(50),  --广告地址
   img_numb  int,    --广告点击数
   indexyl1   varchar2(100),        --预留字段1
   indexyl2  varchar2(100)        --预留字段2
)
drop table index_img;


--作者表
create table author(
      author_id int primary key,   --作者编号
      author_name varchar2(20) not null,    --作者名称
      author_sex varchar2(2)                --作者性别
                    constraints CK_author_sex check(author_sex in('男','女')),
      author_time varchar2(30) default '不详',             --作者所处年代
      author_area varchar2(30),      --作者所处地区

      author_photo  varchar(3000),            --作者图片
      author_details clob,     --作者详细信息
	  author_weight int,  --作者权重
	  author_type varchar2(100),    --作者类型   1表示古诗词作者  2表示文言文   3表示诸子百家  4表示书画风采 5表示地域名族  6表示中华美食  等
	  status int,	--0表示不存在 		 1表示存在

      autoryl1   varchar2(100),        --预留字段1
      autoryl2  varchar2(100)        --预留字段2
);
select * from author 
select author_id from author where author_name='李白' and status=1
create sequence seq_author_author_id start with 0001 increment by 1;
update author set status=0 where author_id in(3,21)
insert into author values(seq_author_author_id.nextval,'张艳容','女','1880','湖南','','',2,'酒',1,'','')
update author set author_details='著名文学家' where author_name='张艳容';

drop table author
delete  form author where author_id=21

);
select * from author 
select author_id from author where author_name='李白' and status=1
create sequence seq_author_author_id start with 1001 increment by 1;
update author set author_type='文言文' where author_id =3
insert into author values(seq_author_author_id.nextval,'刘义庆','男','南朝','江苏徐州','',
'刘义庆（公元403—公元444），字季伯，汉族，原籍南朝宋彭城（今江苏徐州）人，文学家，世居京口，南朝宋宗室，南朝宋文学家。刘义庆自幼才华出众，爱好文学',2,'文言文',1,'','');
insert into author values(seq_author_author_id.nextval,'王安石','男','宋朝','江西临川','',
'王安石（1021年12月18日－1086年5月21日），字介甫，号半山，汉族，临川（今江西抚州市临川区）人，北宋著名的思想家、政治家、文学家、改革家',2,'文言文',1,'','');
insert into author values(seq_author_author_id.nextval,'司马迁','男','西汉','陕西韩城或说山西河津','',
'司马迁(公元前145年-公元前90年)，字子长，夏阳(今陕西韩城南)人，一说龙门(今山西河津)人。中国西汉伟大的史学家、文学家、思想家',2,'文言文',1,'','');
insert into author values(seq_author_author_id.nextval,'唐顺之 ','男','明代','江苏常州','',
'唐顺之(1507年--1560年)，字应德，一字义修，号荆川。汉族，武进(今属江苏常州)人。明代儒学大师、军事家、散文家，抗倭英雄',2,'文言文',1,'','');
drop table author
drop sequence seq_author_author_id;
delete  form author where author_id=21

--作品类型
create table worktype(
	wtid  int primary key, --作品编号
	wtname varchar2(100) not null unique	--作品类型名
)
create sequence seq_wt_wtid start with 0001 increment by 1
insert into worktype values(seq_wt_wtid.nextval,'古诗词');
insert into worktype values(seq_wt_wtid.nextval,'文言文');
insert into worktype values(seq_wt_wtid.nextval,'诸子百家');
insert into worktype values(seq_wt_wtid.nextval,'书画风采');
insert into worktype values(seq_wt_wtid.nextval,'地域名族');
insert into worktype values(seq_wt_wtid.nextval,'中华美食');
select * from worktype;
select * from  works;
--作品类型  1 表示古诗词作品  2表示文言文作品 3表示诸子百家  4表示书画风采 5表示地域名族  6表示中华美食  等
--作品表
create table works(
       works_id int primary key, --作品编号
	   author_id int
            constraints FK_author_id references author(author_id),
       works_name varchar2(40) not null, --作品名称
       works_time varchar2(20),--作品的发表时间
       works_img  varchar2(3000),                     --作品图片
       works_click int,                 --点击量
	   works_weight int,                 --作品权重
       wtid int
            constraints FK_worktype_id references worktype(wtid),    --作品类型  1 表示古诗词作品  2表示文言文作品 3表示诸子百家  4表示书画风采 5表示地域名族  6表示中华美食  等
	   status int,	--0表示不存在 	1表示存在
       works_details varchar2(3000),       --详细信息
       works_yl1 varchar2(100),   
       works_yl2 varchar2(100)
);
drop table works;
drop sequence seq_works_works_id;
create sequence seq_works_works_id start with 1001 increment by 1
insert into works values(seq_works_works_id.nextval,1024,'陈元方候袁公','魏晋时期','',4,4,2,1,'作者:刘义庆   陈元方年十一时，候袁公。袁公问曰：“贤家君在太丘，远近称之，何所履行？元方曰：“老父在太丘强行绥之以德弱者抚之以仁恣其所安久而益敬。”袁公曰：“孤往者尝为邺令，正行此事。不知卿家君法孤，孤法卿父？”元方曰：“周公、孔子，异世而出，周旋动静，万里如一。周公不师孔子，孔子亦不师周公。”','小学文言文','');
insert into works values(seq_works_works_id.nextval,1021,'王安石待客','宋朝','',3,3,2,1,'王安石在相位，子妇之亲(1)萧氏子(2)至京师(3)，因谒(4)公，公约之饭。翌日(5)，萧氏子盛服而往，意为公必盛馔(6)。日过午，觉饥甚而不敢去(7)。又久之，方(8)命坐，果蔬(9)皆不具，其人已心怪之(10)。酒三行(11)，初供胡饼两枚，次供猪脔(12)数四，顷即供饭，旁置(13)菜羹而已。萧氏子颇骄纵，不复下箸(14)，惟啖(15)胡饼中间少许，留其四傍。公取自食之，其人愧甚(16)而退。','初中文言文','');
insert into works values(seq_works_works_id.nextval,1022,'《项王军壁垓下》','西汉时期','',2,2,2,1,'《项王军壁垓下》讲述项羽之死。项王军壁垓下，兵少食尽，汉军及诸侯兵围之数重。项王乃欲东渡乌江。项王笑曰：纵江东父兄怜而王我，我何面目见之？纵彼不言，籍独不愧于心乎？”乃自刎而死。文章围绕项羽这个悲剧英雄，描写了垓下之围、东城快战、乌江自刎三个场面。','高中文言文','');
insert into works values(seq_works_works_id.nextval,1023,'信陵君救赵论','明代','',1,1,2,1,'信陵君救赵论 作者： 唐顺之 论者以窃符为信陵君之罪，余以为此未足以罪信陵也。夫强秦之暴亟矣，今悉兵以临赵，赵必亡。赵，魏之障也。赵亡，则魏且为之后。赵、魏，又楚、燕、齐诸国之障也，赵、魏亡，则楚、燕、齐诸国为之后。天下之势，未有岌岌于此者也。故救赵者，亦以救魏；救一国者，亦以救六国也。窃魏之符以纾魏之患，借一国之师以分六国之灾，夫奚不可者？','古文观止','');
select * from works where works_yl1='古文观止' and rownum=1;
delete  works where works_yl1='古文观止'
select * from works where works_id=1001
select * from works where works_yl1='小学文言文' order by works_weight  desc
insert into works values(seq_works_works_id.nextval,1024,'孟母三迁','战国时期','',1,1,2,1,'','小学文言文','');
insert into works values(seq_works_works_id.nextval,1024,'揠苗助长','公元前372年','',1,1,2,1,'','小学文言文','');
insert into works values(seq_works_works_id.nextval,1021,'画蛇添足','楚国时期','',1,1,2,1,'','小学文言文','');
insert into works values(seq_works_works_id.nextval,1022,'北人食菱','明朝 ','',1,1,2,1,'','小学文言文','');

update works set works_details='孟母三迁，即孟轲的母亲为选择良好的环境教育孩子，多次迁居。《三字经》里说：“昔孟母，择邻处”。孟母三迁便出自于此。词语解释信息为孟子的母亲为了使孩子拥有一个真正好的教育环境，煞费苦心，曾两迁三地，现在有时用来指父母用心良苦，竭尽全力培养孩子。' where  works_name='孟母三迁';
update works set works_details='本成语故事讲述的是一位古代宋人为了使自己田地里的禾苗长得快，便将禾苗往上拔，结果禾苗反而快速地枯萎了。客观表现出事物的发展自有它的规律，纯靠良好的愿望和热情是不够的，很可能效果还会与主观愿望相反这一道理。这一寓言还告知一具体道理："欲速则不达"，”心急吃不了热豆腐“，要尊重自然规律。文章短小精练，先是用寥寥数语，形象地勾勒出一个令人可叹又可笑的“揠苗者“形象，然后又加以评论，可谓画龙点睛，使人顿悟作者的用意所在，叙议结合，相得益彰.' where  works_name='揠苗助长';
update works set works_details='画蛇添足，语出《战国策·齐策二》。原意为画蛇时给蛇添上脚。后比喻做了多余的事，非但无益，反而不合适。也比喻虚构事实，无中生有。这个故事讽刺了那些做事多此一举，反而得不偿失的人。后以“画蛇添足”比喻做多余的事有害无益。也比喻虚构事实，无中生有。' where  works_name='画蛇添足';
update works set works_details='北人①生而不识②菱③者，仕④于南方。席⑤上食（dàn）⑥菱，并壳⑦入口。或⑧曰⑨：“食⑩菱须去（11）壳。”其人自护（12）其短（13），曰：“我非不知，并（14）壳者，欲（15）以（16）清热也。”问者曰：“北土亦有此物否？”答（17）曰：“前山后山，何（18）地不有！”夫菱生于水，而曰土产，此坐（20）强（21）不知以为知也。' where  works_name='北人食菱';



insert into works values(seq_works_works_id.nextval,1021,'乐羊子妻','西汉','',1,1,2,1,'','初中文言文','');
insert into works values(seq_works_works_id.nextval,1021,'岳阳楼记','北宋','',1,1,2,1,'','初中文言文','');
insert into works values(seq_works_works_id.nextval,1023,'宋定伯捉鬼','东晋时期','',1,1,2,1,'','初中文言文','');
insert into works values(seq_works_works_id.nextval,1023,'木兰诗','北魏','',1,1,2,1,'','初中文言文','');

update works set works_details='《乐羊子妻》（yuè yáng zǐ qī），是一篇人物传记。它通过两个小故事，赞扬了乐羊子妻子的高尚品德和过人才识。乐羊子妻，是西汉梁国榖丘（今河南虞城县谷熟镇）人。[1]  乐羊子妻的两段话，不管是过去，现在，还是将来；并且对不同民族，不同政见，不同文化，不同宗教的人来说都有着深远的意义。她告诫人们：做人就必须具备高尚的品德，做事就必须有不可半途而废的精神。' where  works_name='乐羊子妻';
update works set works_details='《岳阳楼记》是北宋文学家范仲淹应好友巴陵郡太守滕子京之请，于北宋庆历六年（1046年）九月十五日为重修岳阳楼写的。其中的诗句“先天下之忧而忧，后天下之乐而乐”、“不以物喜，不以己悲”是较为出名和引用较多的句子。文章通过对洞庭湖的侧面描写衬托岳阳楼。滕子京是被诬陷擅自动用官钱而被贬的，范仲淹正是借作记之机，含蓄规劝他要“不以物喜，不以己悲”，试图以自己“先天下之忧而忧，后天下之乐而乐”的济世情怀和乐观精神感染老友。这是本文命意之所在，也决定了文章叙议结合的风格。《岳阳楼记》超越了单纯写山水楼观的狭境，将自然界的晦明变化、风雨阴晴和“迁客骚人”的“览物之情”结合起来写，从而将全文的重心放到了纵议政治理想方面，扩大了文章的境界。' where  works_name='岳阳楼记';
update works set works_details='《宋定伯捉鬼》是东晋时期出版的图书，作者是干宝。图书选自《搜神记》卷16，这则古代著名的不怕鬼的故事讲述的是宋定伯和一只鬼斗智斗勇的故事，他最终用人类的智能和勇气征服了鬼。故事告诉人们，人用自己的胆量和智能一定能够战胜一切妖魔鬼怪。' where  works_name='宋定伯捉鬼';
update works set works_details='《木兰诗》是一首北朝民歌，宋郭茂倩《乐府诗集》归入《横吹曲辞·梁鼓角横吹曲》中。这是一首长篇叙事诗，讲述了一个叫木兰的女孩，女扮男装，替父从军，在战场上建立功勋，回朝后不愿作官，只求回家团聚的故事，热情赞扬了这位女子勇敢善良的品质、保家卫国的热情和英勇无畏的精神。全诗以“木兰是女郎”来构思木兰的传奇故事，富有浪漫色彩；详略安排极具匠心，虽然写的是战争题材，但着墨较多的却是生活场景和儿女情态，富有生活气息；以人物问答及铺陈、排比、对偶、互文等手法描述人物情态，刻画人物心理，生动细致，神气跃然，使作品具有强烈的艺术感染力。' where  works_name='木兰诗';

insert into works values(seq_works_works_id.nextval,1024,'《送天台陈庭学序》','元末明初','',1,1,2,1,'','古文观止','');
insert into works values(seq_works_works_id.nextval,1022,'《阅江楼记》','明代','',1,1,2,1,'','古文观止','');
insert into works values(seq_works_works_id.nextval,1022,'《司马季主论卜》','明代','',1,1,2,1,'','古文观止','');
insert into works values(seq_works_works_id.nextval,1024,'《卖柑者言》','元末明初 ','',1,1,2,1,'','古文观止','');

update works set works_details='西南山水，惟川蜀最奇1，然去中州万里2，陆有剑阁栈道之险3，水有瞿塘、滟滪之虞。4。跨马行，则竹间山高者，累旬日不见其巅际；临上而俯视，绝壑万仞，杳莫测其所穷5，肝胆为之悼栗6。水行则江石悍利，波恶涡诡7，舟一失势尺寸，辄糜碎土沉8，下饱鱼鳖。其难至如此！故非仕有力者，不可以游；非材有文者，纵游无所得；非壮强者，多老死于其地；嗜奇之士恨焉！' where  works_name='《送天台陈庭学序》';
update works set works_details='金陵为帝王之州[1]。自六朝迄于南唐，类皆偏据一方，无以应山川之王气。逮我皇帝[2]，定鼎于兹[3]，始足以当之。由是声教所暨[4]，罔间朔南[5]；存神穆清[6]，与天同体。虽一豫一游[7]，亦可为天下后世法。京城之西北有狮子山[8]，自卢龙蜿蜒而来[9]。长江如虹贯，蟠绕其下。上以其地雄胜，诏建楼于巅，与民同游观之乐。遂锡嘉名为“阅江”云' where  works_name='《阅江楼记》';
update works set works_details='东陵侯既废1，过司马季主而卜焉2。季主曰：“君侯何卜也？”东陵侯曰：“久卧者思起，久蛰者思启3，久懑者思嚏4。吾闻之：‘蓄极则泄，闷极则达，热极则风，壅极则通。一冬一春，靡屈不伸5；一起一伏，无往不复。’仆窃有疑6，愿受教焉。”季主曰：“若是，则君侯已喻之矣7，又何卜为？”东陵侯曰：“仆未究其奥也，愿先生卒教之。”' where  works_name='《司马季主论卜》';
update works set works_details='今夫21佩虎符22、坐皋比23者，洸洸24乎干城之具25也，果能授孙、吴26之略27耶？峨28大冠、拖长绅29者，昂昂30乎庙堂31之器32也，果能建伊、皋33之业34耶？盗起35而不知御36，民困而不知救，吏奸而不知禁，法斁37而不知理，坐38糜39廪粟40而不知耻。观其坐高堂，骑大马，醉41醇醴而饫42肥鲜者，孰不巍巍43乎可畏，赫赫44乎可象45也？又何往而不金玉其外，败絮其中46也哉？今子是之不察，而以察吾柑！”' where  works_name='《卖柑者言》';

update works set works_details='诗人朱碧潭君汶，以名家子，少从父薄游，往来荆湖豫章，泛洞庭、彭蠡、九江之间，冲簸波涛，以为壮也。登匡庐山，游赤壁，览古名贤栖遁啸咏之迹，有发其志，遂学为诗，耽酒自放。当其酣嬉颠倒，笑呼欢适，以诗为娱，顾谓人莫知我。人亦皆易之，无以为意者。其诗不行于时。屋壁户牖，题墨皆满，涂污淋漓，以诧家人妇子而已。贫不自谋，家人诮之曰：“何物可憎，徒涴墙户，曾不可食，其为画饼耶！”取笔砚投掷之，欲以怒君，冀他有所为。君不为怒，亦不变也。' where  works_name=' 王慎中《朱碧潭诗序》';
update works set works_details='今臣蝼蚁之诚，虽万万不及缇萦，而陛下聪明仁圣，过于汉文远甚。臣欲乞纳在身官，以赎兄轼，非敢望末减其罪，但得免下狱死为幸。兄轼所犯，若显有文字，必不敢拒抗不承，以重得罪。若蒙陛下哀怜，赦其万死，使得出于牢狱，则死而复生，宜何以报！臣愿与兄轼，洗心改过，粉骨报效，惟陛下所使，死而后已。' where  works_name='苏辙《为兄轼下狱上书》';
update works set works_details='沛公军霸上，未得与项羽相见。沛公左司马曹无伤使人言于项羽曰：“沛公欲王（wàng）关中，使子婴为相，珍宝尽有之。”项羽大怒,曰：“旦日飨（xiǎng)士卒，为击破沛公军！”当是时，项羽兵四十万，在新丰鸿门；沛公兵十万，在霸上。范增说（shuì)项羽曰：“沛公居山东时，贪于财货，好美姬。今入关，财物无所取，妇女无所幸，此其志不在小。吾令人望其气，皆为龙虎，成五采，此天子气也。急击勿失！”' where  works_name='鸿门宴';
update works set works_details='壬戌1之秋，七月既望2，苏子与客泛舟游于赤壁之下。清风徐3来，水波不兴4。举酒属5客，诵明月之诗6，歌窈窕之章7。少焉8，月出于东山之上，徘徊于斗牛9之间。白露10横江11，水光接天。纵一苇之所如，凌万顷之茫然12。浩浩乎如冯虚御风13，而不知其所止；飘飘乎如遗世14独立，羽化15而登仙16。' where  works_name='前赤壁赋';




insert into works values(seq_works_works_id.nextval,1021,'王慎中《朱碧潭诗序》','明','',1,1,2,1,'','高中文言文','');
insert into works values(seq_works_works_id.nextval,1021,'苏辙《为兄轼下狱上书》','神宗元丰二年','',1,1,2,1,'','高中文言文','');
insert into works values(seq_works_works_id.nextval,1022,'鸿门宴','公元前206年','',1,1,2,1,'','高中文言文','');
insert into works values(seq_works_works_id.nextval,1023,'前赤壁赋','北宋 ','',1,1,2,1,'','高中文言文','');

select w.*,a.author_name from works w,author a  where a.author_id=w.author_id and works_id=1001
select * from works where works_yl1='古文观止' and rownum = 1




-----作品评选表

--饮食表


--总表：饮食表
create table dietarytype(
	dtpid int primary key,--Id
	dtpname varchar2(100) not null
);
create sequence seq_dtp_dtpid start with 10000 increment by 1;
insert into dietarytype values(seq_dtp_dtpid.nextval,'中华菜系');
insert into dietarytype values(seq_dtp_dtpid.nextval,'美图共赏');
insert into dietarytype values(seq_dtp_dtpid.nextval,'美食典故');
insert into dietarytype values(seq_dtp_dtpid.nextval,'名茶共赏');
insert into dietarytype values(seq_dtp_dtpid.nextval,'品茶思人生');
insert into dietarytype values(seq_dtp_dtpid.nextval,'茶语茶话');
insert into dietarytype values(seq_dtp_dtpid.nextval,'酒的历史');
insert into dietarytype values(seq_dtp_dtpid.nextval,'名酒赏析');
insert into dietarytype values(seq_dtp_dtpid.nextval,'把酒话英雄');
select * from dietarytype
select * from (select a.*,rownum rn from (select dt.*,dtp.dtpname from dietary dt,dietarytype dtp where dt.status=1 and dt.dtpid=dtp.dtpid order by dt.dtid)a  where 10>=rownum)b where rn>0
select count(1) from dietary where status=1
create table dietary(
      dtid int primary key,--Id
	  photo varchar(3000),--图片
	  dtpid int 
	  	constraints FK_dietarytype_dtpid references dietarytype(dtpid),--饮食类型		 1.表示中华菜系  2表示美图共赏 3表示美食典故 4名茶共赏 5品茶思人生 6茶语茶话 7酒的历史 8名酒赏析 9把酒话英雄
      dtname varchar2(40) not null ,--名称
	  dttitle varchar2(100) not null ,--标题
      dttext clob,--内容
      status  int ,--0表示不存在  1表示存在
      dt22  varchar2(500),--预留字段
      dt33  varchar2(500)--预留字段
)
insert into dietary values(seq_dietary_dtid.nextval,null,1,'鲁菜','鲁菜',null,1,'','');
select * from dietary
select * from dietarytype 
drop table dietary
drop sequence seq_dietary_dtid
create sequence seq_dietary_dtid start with 1001 increment by 1;
--图片类型表
create table picshow(
       psid int primary key,--id
	   pstype int ,--图片类型		 1.表示美食  			2表示茶			 3表示美酒    
       psname varchar2(40),--图片所属类型的详细名称
       pspics varchar2(200) not null,--图片
       pstitile varchar2(100),--图片标题
       pstext varchar2(200),--图片内容
       status  int ,--0表示不存在  1表示存在
       ps22  varchar2(500),--预留字段
       ps33  varchar2(500)--预留字段 
)
create sequence seq_picshow_psid start with 0001 increment by 1;

--创建地理中的省份表
create table province(
	prid int primary key,  --id
	prname varchar2(100),  --省份的名称
	prcontent varchar2(500),  --省份的介绍内容
	prstatus int, --状态 0表示不存在，1表示存在
	pryl1 varchar2(500),  --预留字段
	pryl2 varchar2(500)
)
select prname from province where prstatus=1;
create sequence seq_province_prid start with 1001 increment by 1;
drop table province;
drop sequence seq_province_prid;
--增删改查

insert into province (prid, prname, prcontent,prstatus,pryl1,pryl2)
values (1001, '北京','北京，中华人民共和国首都、直辖市和国家中心城市，中国的政治、文化、科教和国际交往中心，
				中国经济、金融的决策和管理中心，中华人民共和国中央人民政府和全国人民代表大会所在地，也是世界上最大的城市之一。', 1,'','');
insert into province (prid, prname, prcontent,prstatus,pryl1,pryl2)
values (1002, '天津','天津位于华北平原海河五大支流汇流处，东临渤海，北依燕山，海河在城中蜿蜒而过，海河是天津的母亲河
。天津滨海新区被誉为“中国经济第三增长极”。天津是夏季达沃斯论坛常驻举办城市。',1, '','');

select * from province;


--创建省份中人物表
create table properson(
	prpid int primary key,  --id
	prid int,  --省份的id
	ppname varchar2(100),  --人物名字
	ppcontent varchar2(4000),  --人物的介绍 varchar2的最大长度
	ppimg varchar2(500),   --人物图片
	ppstatus int,    --人物状态  0不存在  1存在
	ppyl1 varchar2(500),  --预留字段
	ppyl2 varchar2(500)
)


select * from properson;

delete from properson where prpid=1068;
drop table properson;
create sequence seq_properson_ppid start with 1003 increment by 1;
drop sequence seq_properson_ppid
insert into properson values (seq_properson_ppid.nextval,1001,'aaa','wwww','',1,'','')
insert into properson (prpid,prid, ppname, ppcontent,ppimg,ppstatus,ppyl1,ppyl2)
values (1001,1001, '王实甫','王实甫(1260年-1336年)，名德信。大都(今北京市)人。元代杂剧作家。中国著名剧作《西厢记》的作者。关于他生平情况的历史记载却很少。后人推测他的生卒年大约是1260-1336年，主要创作活动大约在元成宗元贞、大德年间(1295年-1307年)，这正是元杂剧的鼎盛时期。
　　王实甫的父亲，曾“赠通议大夫、礼部尚书、太原郡侯。”母亲阿噜浑氏，“赠太原郡夫人”。王实甫不仅出身官宦名门之家，而且他自己也是做过官的。先以县官入仕，因治县有声，后提升为陕西行台监察御史。但总因“与台臣议不合，40岁即弃官不复仕。”王实甫40岁就当了省级领导干部，本来前途无量，却弃官不仕。回到大都后，他一头扎进关汉卿的“玉京书会”，出入于歌台舞榭之中，厮混于勾栏瓦舍之间，开始了他的戏剧创作生涯。
　　王实甫所作杂剧中名目可考的有14种。今存有：《崔莺莺待月西厢记》《吕蒙正风雪破窑记》《四大王歌舞丽春堂》《韩采云丝竹芙蓉亭》《苏小卿月夜贩茶船》，还有少量散曲流传：有小令1首，套曲3种(其中有一残套)，散见于《中原音韵》、《雍熙乐府》、《北宫词纪》和《九宫大成》等书中。
　　《西厢记》是王实甫在元稹的传奇《莺莺传》基础上创作，共五本二十一折。《西厢记》可谓是家喻户晓的一部剧作，在元代就被誉为:“新杂剧，旧传奇，《西厢记》天下夺魁。”她诞生七百年来，被全国多个剧种演唱至今，久演不衰。《西厢记》有鲜明、深刻的反封建的主题。张生和崔莺莺的恋爱故事，已经不再停留在“才子佳人”的模式上，也没有把“夫贵妻荣”作为婚姻的理想。他们否定了封建社会传统的联姻方式，始终追求真挚的感情，爱情已被置于功名利禄之上。《西厢记》结尾处，在中国文学史上第一次正面地表达了“愿普天下有情的都成了眷属”的美好愿望，表达了反对封建礼教、封建婚姻制度、封建等级制度的进步主张，鼓舞了青年男女为争取爱情自由、婚姻自主而抗争。
　　《西厢记》之所以能成为元杂剧的“压卷”之作，不仅在于其表现了反对封建礼教和封建婚姻制度的进步思想，而且它在戏剧冲突、结构安排、人物塑造等方面，都取得了很高的艺术成就。
　　“碧云天，黄花地，西风紧，北雁南飞。晓来谁染霜林醉?总是离人泪„„”谁不为这华彩的词章而拍案叫绝?可见，王实甫的《西厢记》不仅是一部戏剧，它也是中国文学史上的一部不朽名著。更多地域文化方面的知识，尽在传统文化网。',
　　'',1,'','');
insert into properson (prpid,prid, ppname, ppcontent,ppimg,ppstatus,ppyl1,ppyl2)
values (1002,1001, '梅兰芳','　梅兰芳，祖籍江苏泰州，生于北京的一个梨园世家。梅兰芳是中国的文化，艺术的象征，他的表演艺术很早就蜚声海内外。他交游广泛，阅历丰富，尤其对京剧艺术的改良和创新有着杰出的贡献。梅兰芳对京剧旦角的唱腔、念白、舞蹈、音乐、服装、化妆等各方面都有所创造发展，形成了自己的艺术风格，世称“梅派”。',
　　'',1,'','');

select pp.* from properson pp,province p where p.prid=pp.prid and p.prid=1001 and ppstatus=1 and prstatus=1;
select pp.* from properson pp,province p where p.prid=pp.prid and pp.prpid=1002 and ppstatus=1 and prstatus=1;
 

--创建省份中的风景表
create table proscenery(
	prsid int primary key,
	prid int, --省份的id
	prsname varchar2(100),  --风景的名称
	prsimg varchar2(500),   --风景图片
	prscontent varchar2(4000), -- 风景的介绍
	prsstatus  int,  --风景状态 0表示不存在  1表示存在
	prsyl1 varchar2(500),
	prsyl2 varchar2(500)
	
)

create sequence seq_proscenery_prsid start with 1001 increment by 1;
select * from proscenery;

insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '中国帝王的象征——故宫','','拉拉拉',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '八达岭长城','','aa',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '万园之园--圆明园','','bb',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '皇家园林博物馆--颐和园','','cc',1,'','');

insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '故宫','','dd',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '香山公园','','ee',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '王府井大街','','ff',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '天安门','','gg',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '国家大剧院','','hh',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '八达岭长城','','ii',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '圆明园','','jj',1,'','');
insert into proscenery (prsid,prid, prsname,prsimg,prscontent,prsstatus,prsyl1,prsyl2)
values (seq_proscenery_prsid.nextval,1001, '颐和园','','kk',1,'','');

commit;

