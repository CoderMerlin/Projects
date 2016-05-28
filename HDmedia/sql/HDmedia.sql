--1.注册  （用户、管理员)
--======================================================================================
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

--增删改查

create sequence seq_yonghuzc_yhzcid start with 1001 increment by 1;
insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'Mi丶','573059382@qq.com','a',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'','');

insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'黄迈1','573059381@qq.com','a',to_date('2015-12-30 11:15:44','yyyy-MM-dd hh:mi:ss'),1,'','');
insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'haha','573059381@qq.com','123456',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'','');
drop table yonghuzc;
drop sequence seq_yonghuzc_yhzcid;
update yonghuzc set yhemail='573059383@qq.com' where yhzcid=1002;
select * from yonghuzc;
select yhzcid,yhname,yhpwd from yonghuzc where yhname='Mi丶' and yhpwd='a';
insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'qq','a@qq.com','a',to_date('2016-01-11 19:23:46','yyyy-mm-dd hh24:mi:ss'),1,'','');
insert into yonghuzc values(seq_yonghuzc_yhzcid.nextval,'zz','z','a',to_date('2016-01-11 19:38:47','yyyy-mm-dd hh24:mi:ss'),1,'','');

commit;  --提交
--用户
select * from tiezi tz,yonghu yh where tz.yhid=yh.yhid and ltid=1021;
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

select * from yonghu where yhname='zs123' and yhpwd='3DA8155EE7F6A2FB';

select * from yonghuzc yhzc,yonghu yh  where yhzc.yhzcid=yh.yhzcid and  (yhzc.yhname='zs123' and yhzc.yhpwd='3DA8155EE7F6A2FB')

select yhzcid,yhname,yhpwd from yonghuzc where yhname='qwe' and yhpwd='32385B50C8127036'
create sequence seq_yonghu_yhid start with 1001 increment by 1;
insert into yonghu values(seq_yonghu_yhid.nextval,1001,'奥巴马','男',30,1588580556,4654654646131,5813125213,to_date('1964-12-30','yyyy-MM-dd'),'','','','','','','','',to_date('2015-12-30 11:15:44','yyyy-mm-dd hh24:mi:ss'),'',1,'','');

select * from yonghu;
drop table yonghu;
drop sequence seq_yonghu_yhid;

=======
create sequence seq_yonghu_yhid start with 1001 increment by 1;
insert into yonghu values(seq_yonghu_yhid.nextval,1002,'haha','男',18,'1320740104','','','','','','','','','',0,0,'','',1,'','');
select * from yonghu
select yhid from (select distinct yhzsname,yhid  from yonghu  where yhstatus=1) where yhzsname='haha'
select * from (select a.*,rownum rn from (select yh.*,yhname,yhemail,yhpwd,yhzctime from yonghuzc yhzc,yonghu yh where yhzc.yhzcid=yh.yhzcid  order by yhzc.yhzcid desc) a where rownum<=10) b where rn>0

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
   glyl1   varchar2(1000),        --预留字段1
   glyl2   varchar2(1000)      --预留字段2
);


select * from (select a.*,rownum rn from (select * from GuanLi order by glid) a where 10>=rownum)b where rn>0;

select count(1) from Guanli

--增删改查
create sequence seq_guanli_glid start with 1001 increment by 1;
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
drop sequence seq_guanli_glid
drop table guanli;

update guanli set glemail='573059383@qq.com' where glid=1001;
select * from guanli;
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
select * from wenzhangtype where status=1 order by wztypeid
create sequence seq_wenzhangtype_wztypeid start with 1000001 increment by 1;
insert into wenzhangtype values(seq_wenzhangtype_wztypeid.nextval,'古代绘画',1,'','');
select * from  wenzhangtype;
drop sequence seq_wenzhangtype_wztypeid;
drop table wenzhangtype;
);
alter table wenzhangtype rename column  wztype to wzname
select wztypeid,wzname,status from wenzhangtype where status=1
create sequence seq_wenzhangtype_wztypeid start with 1000001 increment by 1;
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
   wztitle varchar2(50) not null,  --文章标题
   wztext varchar2(500) not null,   --文章内容
   wzpicture  clob,     --文章图片  
   wzdianjinnum int,  --文章点击数
   wzweight int,  --文章权重
   wzstatus int,      --1表示存在，0表示不存在	
   wzyl1  varchar2(100),        --预留字段1
   wzy12  varchar2(100)        --预留字段2
);
create sequence seq_wenzhang_wzid start with 1000001 increment by 1;
insert into wenzhang values(seq_wenzhang_wzid.nextval,10023,1000001,'山水','大峰','',0,1,1,'','');
delete wenzhang where wzid=1000001;
select * from wenzhang;
drop table wenzhang;
create sequence seq_wenzhang_wzid start with 1000001 increment by 1;
insert into wenzhang values(seq_wenzhang_wzid.nextval,10023,1000001,'山水','大峰','',0,1,1,'','');
delete wenzhang where wzid=1000001;
select * from wenzhang where wztypeid=(select wztypeid from wenzhangtype where wzname='美食') and wzweight=1 order by wzid
select * from wenzhang where wztypeid=(select wztypeid from wenzhangtype where wzname='美食') and wzid between 1000006 and 10000013  order by wzid ;
--对文章评论表  
create table wzyhpinglun(
   wzplid int primary key,  --评论id
   yhzcid int 
        constraints FK_yonghu_yhid references yonghuzc(yhzcid),     --用户id    外键
   wzpltext  varchar2(500) not null,  --评论内容
   wzpltime  date,     --评论时间
   wzid int 
		constraints FK_wenzhang_wid references wenzhang(wzid),   --文章id
   wzpltime  date,     --评论时间
   wid int 
		constraints FK_wenzhang_wid references wenzhang(wid),   --文章id
   wzid int 
		constraints FK_wenzhang_wid references wenzhang(wzid),   --文章id
   status int, --状态       -- 0 表示删除   1表示存在
   wzpl1   varchar2(100),        --预留字段1
   wzpl2  varchar2(100)        --预留字段2
);
create sequence seq_wzyhpinglun_wzplid start with 1000000001 increment by 1;
insert into wzyhpinglun values(seq_wzyhpinglun_wzplid.nextval,10001,'888',to_date('2015-10-22 22:22:22' ,'yyyy-mm-dd hh24:mi:ss'),1000075,1,'','');
drop table wzyhpinglun;
delete wzyhpinglun where status=0;
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
   drop sequence seq_tiezi_tid;
   select * from tiezi where tid=7;
   select * from luntan;
   delete luntan where ltid=1004; 
   select * from tiezi;
   delete tiezi where tid=4;
   select * from tiezi where status=1 order by tzclick
create sequence seq_tiezi_tid start with 0001 increment by 1;
select * from (select a.*,rownum rn from (select ht.*,tz.tzname,yh.yhzsname,yh.yhphoto from huitie ht,yonghu yh,tiezi tz where ht.yhid=yh.yhid and ht.tid=tz.tid and tz.tid=7 and ht.htstatus=1) a where rownum<=7)b where rn>0;
insert into tiezi values(seq_tiezi_tid.nextval,(select ltid from luntan  where ltname='新手水吧' and ltstatus=1 and rownum <= 1),
( select yhid from yonghu  where yhstatus=1 and yhzsname='haha' and rownum <= 1),'生活百事','都结束了',
to_date('2015-10-10  11:15:44','yyyy-mm-dd hh24:mi:ss'),0,'帖子内容','',0,1,'','')
insert into tiezi values(seq_tiezi_tid.nextval,1001,1008,'ggg','qweqeq',to_date('2015-10-10  11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'qweqewq','',0,1,'','');
insert into tiezi values(seq_tiezi_tid.nextval,1021,1008,'ggg','qweqeq',to_date('2015-10-10  11:15:44','yyyy-mm-dd hh24:mi:ss'),1,'qweqewq','',0,1,'','');
select count(*) from huitie ht,yonghu yh,tiezi tz where ht.yhid=yh.yhid and ht.tid=tz.tid and tz.tid=7 and ht.htstatus=1
select * from yonghu;
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
insert into huitie values(seq_huitie_htid.nextval,1002,( select tid from (select distinct tzname,tid  from tiezi  where status=1) where tzname='“鸟的王国”——青海湖鸟岛'),
'但是客服尽快',to_date('2015-10-10 00:00:00','yyyy-mm-dd hh24:mi:ss'),0,1,'','')
create sequence seq_huitie_htid start with 0001 increment by 1;
select ht.*,count(ht.htid),yh.yhzsname,yh.yhphoto,tz.tzname from huitie ht,yonghu yh,tiezi tz where ht.yhid=yh.yhid and ht.tid=tz.tid and ht.htstatus=1 order by ht.httime desc"
"select ht.*,yh.yhzsname,tz.tzname from huitie ht,yonghu yh,tiezi tz  where ht.htid=1002 and ht.yhid=yh.yhid and ht.tid=tz.tid"
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
--首页图片表  
create table index_img(
   index_imgid int  primary key,  --首页图片id
   yhid  int
          constraints FK_yonghu_yhid references yonghu(yhid),    --用户id (放用户图片)  外键
   img_position int,    --广告位置   
   img_begin date, --广告开始时间 
   img_end  date, --广告结束时间
   img_weight int,   --权重
   img_addr  varchar2(50),  --广告地址
   img_numb  int,    --广告点击数
   indexyl1   varchar2(100),        --预留字段1
   indexyl2  varchar2(100)        --预留字段2
)


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
insert into author values(seq_author_author_id.nextval,'朱小丽','女','1880','湖南','','',2,'酒',1,'','')
drop table author
delete  form author where author_id=21

);
select * from author 
select author_id from author where author_name='李白' and status=1
create sequence seq_author_author_id start with 0001 increment by 1;
update author set status=0 where author_id in(3,21)
insert into author values(seq_author_author_id.nextval,'海伦凯勒','女','1880','湖南','','',2,'酒',1,'','')
drop table author
drop sequence seq_author_author_id;
delete  form author where author_id=21

--作品表
create table works(
       works_id int primary key, --作品编号
	   author_id int
                         constraints FK_author_id references author(author_id),
       works_name varchar2(40) not null, --作品名称
       works_time date,--作品的发表时间
       works_img  varchar2(3000),                     --作品图片
       works_click int,                 --点击量
	   works_weight int,                 --作品权重
      
       works_type varchar2(100),    --作品类型  1 表示古诗词作品  2表示文言文作品 3表示诸子百家  4表示书画风采 5表示地域名族  6表示中华美食  等
	   status int,	--0表示不存在 	1表示存在
       works_details varchar2(3000),       --详细信息
       works_yl1 varchar2(100),   
       works_yl2 varchar2(100)
);
select * from works;
insert into works values(seq_works_works_id.nextval,2,'舌尖上的中国','','',1000,1,'古代书画',1,66666,'','');
insert into works values(seq_works_works_id.nextval,2,'舌尖上的中国','','',1000,1,'古代书法',1,66666,'','');
insert into works values(seq_works_works_id.nextval,2,'舌尖上的中国',default,'',1000,1,'','美食',1,'','');
drop table works;
drop sequence seq_works_works_id;
create sequence seq_works_works_id start with 0001 increment by 1
drop sequence seq_works_works_id
select w.*,a.author_name from works w,author a where w.works_id=1 and w.author_id=a.author_id and w.status=1
select * from works where status=1;
select * from (select w.*,a.* from works w, author a,where (works_type='文化资讯' and  status=1 and w.author_id=a.author_id) order by works_weight desc) where rownum<=4 order by rownum asc
drop table works;
create sequence seq_works_works_id start with 0001 increment by 1;

select * from works

select * from works;
create sequence seq_works_works_id start with 0001 increment by 1
select w.*,a.author_name from works w,author a where w.works_id=1 and w.author_id=a.author_id and w.status=1
select * from works where status=1;



-----作品评选表

--饮食表


--总表：饮食表
create table dietary(
      dtid int primary key,--Id
	  photo varchar(3000),--图片
	  dttype int ,--饮食类型		 1.表示中华菜系  2表示美图共赏 3表示美食典故 4名茶共赏 5品茶思人生 6茶语茶话 7酒的历史 8名酒赏析 9把酒话英雄
      dtname varchar2(40) not null ,--名称
	  dttitle varchar2(100) not null ,--标题
      dttext clob,--内容
      status  int ,--0表示不存在  1表示存在
      dt22  varchar2(500),--预留字段
      dt33  varchar2(500)--预留字段
)
insert into dietary values(seq_dietary_dtid.nextval,null,1,'鲁菜','鲁菜',null,1,'','');

drop table dietary
drop sequence seq_dietary_dtid
create sequence seq_dietary_dtid start with 0001 increment by 1;
select * from picshow
select * from dietary
select * from dietary where dttype=8 order by dtid
select * from yonghu
select ht.*,yh.yhzsname,yh.yhphoto,tz.tzname from huitie ht,yonghu yh,tiezi tz where ht.yhid=yh.yhid and ht.tid=tz.tid and ht.htstatus=1 order by ht.httime desc
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
	ppcontent varchar2(500),  --人物的介绍
	ppyl1 varchar2(500),  --预留字段
	ppyl2 varchar2(500)
)
create sequence seq_properson_ppid start with 1001 increment by 1;


--创建省份中的风景表
create table proscenery(
	prsid int primary key,
	prid int, --省份的id
	prsname varchar2(100),  --风景的名称
	prscontent varchar2(500), -- 风景的介绍
	prsyl1 varchar2(500),
	prsyl2 varchar2(500)
)


