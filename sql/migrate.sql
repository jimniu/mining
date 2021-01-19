insert into wb_task(id,userid,keyword,tag,gender,province,city,vtype,nickname,intro,level,levelop,fans,fansop,follow,followop,feed,feedop,ctime,utime)
        (select taskid,userid,keyword,tag,gender,province,city,vtype,nickname,intro,level,levelop,fans,fansop,follow,followop,feed,feedop,ctime,utime from weibo.wb_filter);      

update mining.wb_task as m,weibo.wb_task as w set m.name=w.name, m.description=w.intro where w.id=m.id;

TRUNCATE TABLE wx_column;
insert into mining.wx_column(userid,serno,type,title,keyword,ctime,utime)
                            (select uid,category,type,category,group_concat(keyword),ctime,utime from weibo.wx_setting where category<3 group by uid,category );
update wx_column set type=1 where serno>0;
update wx_column set ftime='2015-01-01',lastid=0;

truncate table mining.xw_column;					
insert into mining.xw_column (id,userid,serno,type,title,keyword,ctime,utime)(select id,uid,serno,0, title,keyword,ctime,utime from weibo.xw_column); 

/*�ֶ�����serno*/

update mining.xw_column set type=1 where serno>2;
update xw_column set stime='2015-01-01';

delete from bdp_user;
insert into mining.bdp_user (select * from weibo.bdp_user);
delete from bdp_admin;
insert into mining.bdp_admin (select * from weibo.bdp_admin);
insert into mining.bdp_payment (select * from weibo.bdp_payment);
insert into mining.bdp_tag (select * from weibo.bdp_tag);
insert into mining.bdp_usertag (select * from weibo.bdp_usertag);

delete from zmt_content;
insert into mining.zmt_content (select * from weibo.zmt_content);
delete from zmt_contenttag;
insert into mining.zmt_contenttag (select * from weibo.zmt_contenttag);
delete from zmt_tag;
insert into mining.zmt_tag(id,uid,value,ctime) (select id,uid,value,ctime from weibo.zmt_tag);

alter table xw_column add column ftime datetime DEFAULT NULL;
alter table xw_column add KEY(keyword(190),ftime);

alter table wx_result add column province varchar(16) after wxname;

set names utf8;
LOAD DATA LOCAL INFILE 'c:\\tmp\\mobile.csv'
INTO TABLE bdp_mobloc
FIELDS TERMINATED BY  ','
LINES TERMINATED BY '\r\n'
(mobile,province,city);

alter table wb_task drop column matched;
alter table wb_task add column daytotal int(10) after status;
alter table wb_task add column total int(10) after status;

alter table wx_column add column daytotal int(10) after status;
alter table wx_column add column total int(10) after status;

alter table xw_column add column daytotal int(10) after status;
alter table xw_column add column total int(10) after status;

alter table lt_column add column daytotal int(10) after status;
alter table lt_column add column total int(10) after status;

alter table wb_task modify column daytotal int(10) default 0;
alter table wb_task modify column total int(10) default 0;

alter table wx_column modify column daytotal int(10) default 0;
alter table wx_column modify column total int(10) default 0;

alter table xw_column modify column daytotal int(10) default 0;
alter table xw_column modify column total int(10) default 0;

alter table lt_column modify column daytotal int(10) default 0;
alter table lt_column modify column total int(10) default 0;

/////////////////
alter table wx_column change column daytotal today int(10) default 0;
alter table xw_column change column daytotal today int(10) default 0;
alter table lt_column change column daytotal today int(10) default 0;
alter table wb_task change column daytotal today int(10) default 0;

///////////////////
alter table wb_task add column ftime datetime;
alter table wb_task add key(status,ftime);

update wx_column set serno=4 where serno=2;
update wx_column set serno=3 where serno=1;
insert into wx_column(userid,serno,type,relation,lastid,status,ctime,stime) (select userid,1,type,relation,0,status,now(),'2005-01-01' from wx_column where serno=0);
insert into wx_column(userid,serno,type,relation,lastid,status,ctime,stime) (select userid,2,type,relation,0,status,now(),'2005-01-01' from wx_column where serno=0);



alter table wb_result add column industry int(5) after city;

////////////////////////
alter table xw_column drop column stime;
alter table xw_column drop index status;

alter table wx_column drop column stime;
alter table wx_column drop column lastid;
alter table wx_column drop index status;

alter table fm_column drop column ftime;

///////
update lt_column set serno=4 where serno=2;
update lt_column set serno=3 where serno=1;
insert into lt_column(userid,serno,type,relation,status,ctime) (select userid,1,type,relation,0,now() from lt_column where serno=0);
insert into lt_column(userid,serno,type,relation,status,ctime) (select userid,2,type,relation,0,now() from lt_column where serno=0);


update mining.wb_task set status=0 where userid in(select id from robot.wb_user where status=2);
update mining.wx_column set status=0 where userid in(select id from robot.wb_user where status=2);
update mining.xw_column set status=0 where userid in(select id from robot.wb_user where status=2);
update mining.lt_column set status=0 where userid in(select id from robot.wb_user where status=2);


alter table wb_task drop column description;
alter table wb_task add column relation int(1) after name;
alter table wb_task change total weibototal int(10) default 0;
alter table wb_task change today weibotoday int(10) default 0;
alter table wb_task add column fanstotal int(10)  default 0 after weibotoday;
alter table wb_task add column feedtotal int(10)  default 0 after fanstotal;
alter table wb_task add column hittotal int(10)  default 0 after feedtotal;

alter table wb_task modify fanstotal		bigint(10) unsigned default 0;
alter table wb_task modify feedtotal		bigint(10) unsigned default 0;

////////////////////////////////////////
alter table wb_task rename to wb_column;
insert into robot.bdp_province(id,name) values(99,'全国');

insert into bdp_user(id,partnerid,name,password,company,area,contact,email,mobile,phone,weixin,barcode,headimage,type,priority,startpoint,expire,status,ipaddr,ctime,utime)
select id,partnerid,name,password,company,area,contact,email,cellphone,mobile,weixin,barcode,headimage,type,priority,startpoint,expire,status,ipaddr,ctime,utime from user;

alter table bdp_user rename to user;
insert into bdp_network(id,name,userid,partnerid,company,area,contact,phone,weixin,barcode,headimage,priority,startpoint,expire,type,status,ctime,utime)
select id,name,id,partnerid,company,area,contact,phone,weixin,barcode,headimage,priority,startpoint,expire,type,status,ctime,utime from user where status!=0;

insert into bdp_user(id,name,password,networkid,email,mobile,ipaddr,expire,type,status,ctime,utime)
select id,name,password,id,email,mobile,ipaddr,expire,type,status,ctime,utime from user where status!=0;

update bdp_network set status=0 where status=2;
update bdp_user set type=type+1;
update bdp_user set type=0 WHERE type=2;

alter table bdp_recommend add column type          int(1) after shareno;
alter table bdp_recommend add KEY(userid, id, type);

alter table bdp_network add column capping    int(2) after phone;
update bdp_network set capping=3;

alter table wb_column change column name  title varchar(64);

alter table bdp_network drop column capping;
alter table bdp_network drop column weixin;
alter table bdp_network drop column barcode;
alter table bdp_network drop column headimage;
alter table bdp_network drop column startpoint;
alter table bdp_network drop column scope;
alter table bdp_network add column setting varchar(2048) after priority;


alter table lt_column change column coreword fetchword varchar(2048);
alter table lt_column change column keyword  searchword varchar(2048);
alter table lt_column add column modified int(5) after searchword;
alter table lt_column add column mtime datetime after utime;
alter table lt_column change column userid networkid int(10);

alter table xw_column change column coreword fetchword varchar(2048);
alter table xw_column change column keyword  searchword varchar(2048);
alter table xw_column add column modified int(5) after searchword;
alter table xw_column add column mtime datetime after utime;
alter table xw_column change column userid networkid int(10);

alter table wx_column change column coreword fetchword varchar(2048);
alter table wx_column change column keyword  searchword varchar(2048);
alter table wx_column add column modified int(5) after searchword;
alter table wx_column add column mtime datetime after utime;
alter table wx_column change column userid networkid int(10);

alter table wb_column change column coreword fetchword varchar(2048);
alter table wb_column change column keyword  searchword varchar(2048);
alter table wb_column add column modified int(5) after searchword;
alter table wb_column add column mtime datetime after utime;
alter table wb_column change column userid networkid int(10);

alter table lt_result change column userid networkid int(10);
alter table wx_result change column userid networkid int(10);
alter table xw_result change column userid networkid int(10);
alter table wb_result change column userid networkid int(10);

alter table bdp_summary change column userid networkid int(10);
alter table bdp_geostat change column userid networkid int(10);
alter table bdp_daystat change column userid networkid int(10);
alter table wb_customer change column userid networkid int(10);
alter table wx_relation change column userid networkid int(10);
alter table sj_column change column userid networkid int(10);
alter table fm_column change column userid networkid int(10);
alter table bdp_favorite change column userid networkid int(10);
alter table bdp_recommend change column userid networkid int(10);
alter table bdp_opinion change column userid networkid int(10);
alter table fm_column change column userid networkid int(10);
alter table fm_column change column userid networkid int(10);

alter table sj_column add column modified int(5) after keyword;