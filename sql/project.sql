/*create database mining;
grant all privileges on mining.* to weibo@'localhost' identified by 'ishowdata';
*/

/*管理员表*/
DROP TABLE  IF EXISTS  bdp_admin;
CREATE TABLE IF NOT EXISTS bdp_admin (
  id 			int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  name 			varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  password 		varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '密码',
  full_name 	varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '全名',
  phone 		varchar(32) NOT NULL COMMENT '手机',
  email 		varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '邮箱',
  type 			int(1) NOT NULL COMMENT '用户类型',
  scope	 		varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '用户权限',
  temp 			varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '临时信息',
  status 		int(1) DEFAULT '0' COMMENT '审核状态,1是通过',
  ctime 		datetime NOT NULL COMMENT '创建时间',
  ipaddress 	varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'IP地址',
  utime 		datetime DEFAULT NULL COMMENT '最后登录时间',
  PRIMARY KEY (id),
  KEY(name)
);

--
-- 转存表中的数据 `bdp_admin`
--

INSERT INTO `bdp_admin` (`id`, `name`, `password`, `full_name`, `phone`, `email`, `type`, `scope`, `temp`, `status`, `ctime`, `ipaddress`, `utime`) VALUES
(1, 'admin', 'admin', 'adminstrator', '13716890531', '12334@sina.com', 0, 'range', NULL, 1, '2015-06-11 00:00:00', '127.0.0.1', '2015-06-12 19:20:18');


/*体验用户表*/
DROP TABLE  IF EXISTS  bdp_tuser;
CREATE TABLE IF NOT EXISTS bdp_tuser (
  id 			int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  name 			varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '用户名',
  company 		varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '公司',
  mobile 		varchar(32) NOT NULL COMMENT '手机',
  tellphone 	varchar(16)CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '固定电话',
  email 		varchar(64) CHARACTER SET utf8mb4 NOT NULL COMMENT '邮箱',
  ctime 		datetime NOT NULL COMMENT '创建时间',
  ipaddress 	varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT 'IP地址',
  PRIMARY KEY (id),
  KEY(name)
);

/*账户*/
DROP TABLE IF EXISTS bdp_network;
CREATE TABLE IF NOT EXISTS bdp_network (
  id 			int(10) NOT NULL AUTO_INCREMENT,      /*客户ID*/
  name 		    varchar(128),                         /*企业名称*/
  userid 		int(10),                              /*账户的管理者ID*/
  partnerid   	int(11) DEFAULT '1',                  /*OEM伙伴id*/
  company     	varchar(128),                         /*企业名称*/
  area 			varchar(128),                         /*行业*/
  contact 		varchar(16),                          /*联系人*/
  phone 	    varchar(16),                          /*公司电话*/
  priority    	int(2) DEFAULT 0,					  /*用户优先级*/
  setting		varchar(2048),						  /*账户的设置内容*/
  expire 		datetime,                             /*失效时间*/
  type 			int(1) NOT NULL DEFAULT 0,            /*用户账户类型, 0 - 标准用户， 1 - 高级用户*/
  status 		int(1) DEFAULT 0,                     /* 状态：0-非活动，1-活动*/
  ctime 		datetime,                             /*创建时间*/
  utime 		datetime,                             /*最后登录时间*/
  PRIMARY KEY (id),
  UNIQUE KEY(name),
  KEY(ctime),
  KEY(userid)
);

/*用户表*/
DROP TABLE  IF EXISTS bdp_user;
CREATE TABLE IF NOT EXISTS bdp_user (
  id				int(10) NOT NULL AUTO_INCREMENT,
  name 			  	varchar(64),                        /*帐号*/
  password 			varchar(64) NOT NULL,              /*密码*/
  networkid			int(10) NOT NULL,                  /*所属账户ID*/
  email 		  	varchar(128),                      /*邮箱*/
  mobile 		  	varchar(16),                       /*手机*/
  ipaddr 		  	varchar(15),                       /*Ip地址*/
  expire 		  	datetime,                           /*失效时间*/
  type 			  	int(1) DEFAULT 0,                  /*用户类型, 0 - 访客，1 - 主人， 2 - 管理员*/
  status 		  	int(1) DEFAULT 0,                  /* 状态：0-审核中，1-通过，2-拒绝*/
  ctime 		  	datetime,                           /*创建时间*/
  utime 		  	datetime,                           /*最后登录时间*/
  PRIMARY KEY (id),
  KEY (utime,ctime),
  KEY (ctime),
  UNIQUE KEY(name)
);

INSERT INTO bdp_user (id, name, password, networkid, email, mobile, expire, type, status, `ctime`, `ipaddr`, `utime`) VALUES
(0, 'admin', 'admin', 1, '12334@sina.com', '13716890531', '2020-12-31', 1, 1, '2015-06-11 00:00:00', '127.0.0.1', '2015-06-12 19:20:18');

DROP TABLE IF EXISTS bdp_summary;
/*统计表*/
create table bdp_summary
(
  id			int(10) not null auto_increment,
  networkid		int(10),						/*所属用户*/
  module		char(8),						/*所属模块，wb-微博等*/
  columnid		int(10),						/*所属模块的栏目ID*/
  category		varchar(16),					/*统计分组，如按月-month，按地域-location，按总数-total，按当日统计-today*/
  type			varchar(16),					/*内容类型，比如是文章还是站点，是公众号还是微信内容等。可为空*/
  name			varchar(64),					/*名称，如按照地域时的北京、按照月份时的2015年10月，可为空*/
  value			int(10),						/*统计数值*/
  utime			datetime,						/*数据更新时间*/
  PRIMARY KEY (id),
  KEY(networkid,module,columnid,category,type,name),
  KEY(networkid,module,columnid,category,name)
); 


DROP TABLE IF EXISTS bdp_geostat;
/*按地理位置统计表*/
create table bdp_geostat
(
  id			int(10) not null auto_increment,
  networkid		int(10),						/*所属用户*/
  module		char(8),						/*所属模块，wb-微博等*/  
  columnid		int(10),						/*所属模块的栏目ID，*/
  type			int(1) DEFAULT 0,				/*栏目类型: 0 - 微博监控， 1 - 事件监控， 2-负面监控*/
  date			char(10),						/*日期*/  
  province		int(10),						/*省份*/
  total			int(10),						/*统计数值*/
  utime			datetime,						/*数据更新时间*/
  PRIMARY KEY (id),
  KEY(date,networkid,type,province,columnid,module),
  KEY(columnid,type,province,module)
); 

DROP TABLE IF EXISTS bdp_daystat;
/*统计表*/
create table bdp_daystat
(
  id			int(10) not null auto_increment,
  networkid		int(10),						/*所属用户*/
  module		char(8),						/*所属模块，wb-微博等*/
  columnid		int(10),						/*所属模块的栏目ID*/
  category		int(1) DEFAULT 0,				/*统计分组，0-按天 1-按月*/				  
  type			int(1) DEFAULT 0, 				/*栏目类型: 0 - 微博监控， 1 - 事件监控， 2-负面监控*/
  date			char(10),						/*日期*/
  mediatotal	int(10),						/*媒体的数量*/
  pagetotal		int(10),						/*内容的数量*/
  utime			datetime,						/*数据更新时间*/
  PRIMARY KEY (id),
  KEY(columnid,module,date,category),
  KEY(networkid,module,type,date,category)  
); 
  
DROP TABLE IF EXISTS bdp_mobloc;
/*项目表*/
create table bdp_mobloc
(
  id			int(10) not null auto_increment,
  mobile		int(11),					/*手机号的前几位*/
  province		varchar(16),				/*所属省份*/
  city			varchar(32),				/*所属城市*/
  ctime			datetime,					/*入库时间*/
  PRIMARY KEY (id),
  KEY(mobile)  
);  

/*根据合作伙伴的域名显示个性化的log等信息*/
DROP TABLE IF EXISTS bdp_partner;
/*项目表*/
create table bdp_partner
(
  id			int(10) not null auto_increment,
  name			varchar(32),				/*合作伙伴的名称*/
  domain		varchar(64),				/*网站的域名*/
  logo			varchar(128),				/*log的地址*/
  phone			varchar(32),				/*联系电话*/
  title			varchar(128),				/*站点名称*/
  banner		varchar(256),				/*站点名称图片的地址*/
  homebanner  varchar(256),					/*首页站点名称图片的地址*/
  footer		text,						/*页脚*/
  status		int(1),						/*状态*/
  utime			datetime,					/*更新时间*/
  PRIMARY KEY (id),
  KEY(domain,status)  
);  

insert into bdp_partner(name,domain,title,status,utime) values('ishowdata', 'dig88.cn','dig88', 1, now());


/*分类表*/
DROP TABLE IF EXISTS bdp_category;
create table bdp_category
(
	id 			int(10) NOT NULL AUTO_INCREMENT,
	name		varchar(64) NOT NULL,			/*分类名称*/
	level		int(3),							/*层级*/
	parentid	int(10),						/*上一级ID*/
	rootid		int(10),						/*根节点ID*/
  PRIMARY KEY(id),
  UNIQUE KEY(rootid,level,name)
);

DROP TABLE IF EXISTS wb_column;
/*项目表*/
create table wb_column
(
  id			int(10) not null auto_increment,
  title			varchar(64),				/*任务名称*/
  relation		int(1),						/*关系：0 - 未知关系，1-自有，2-竞品*/ 
  networkid		int(10),					/*项目所属用户*/
  fetchword		varchar(2048),				/*微博抓取过滤条件*/
  searchword	varchar(2048),				/*微博内容过滤条件*/
  modified 		int(5),						/*关键词修改次数*/
  feedtotal		int(10) default 0,			/*微博总量*/
  feedtoday		int(10) default 0,			/*当日微博总量*/
  usertotal		int(10) default 0,			/*微博用户总量*/
  usertoday		int(10) default 0,			/*当日微博用户总量*/  
  fansum		bigint(10) unsigned default 0,	/*粉丝总数*/
  feedsum		bigint(10) unsigned default 0,	/*符合条件的用户的文章总数量*/
  hitsum		int(10) default 0,			/*包含关键词的文章数量*/
  task			int(1),						/*0 - 关键词搜索, 1 - 微博用户内容抓取*/
  type			int(1) DEFAULT 0 			/*栏目类型: 0 - 微博监控， 1 - 事件监控， 2-负面监控*/,
  status		int(1) DEFAULT 1			/*状态：1-活动，0-失效*/,  
  ctime			datetime,					/*创建时间*/
  utime			datetime,					/*修改时间*/
  mtime			datetime,					/*关键词修改时间*/
  ftime			datetime,					/*最后一次抓取内容的时间*/
  PRIMARY KEY(id),
  KEY(relation),
  KEY(networkid,type,relation),
  KEY(networkid,ctime),
  KEY(status,ftime)
);

DROP TABLE IF EXISTS wb_customer;
/*客户表*/
create table wb_customer
(
  id			bigint(10) unsigned not null auto_increment,
  networkid		int(10) not null,				/*用户ID*/
  columnid		int(10) not null,				/*任务id*/
  wbid			bigint(10) unsigned not null,	/*用户的ID*/
  nickname		varchar(32),					/* 昵称 */
  fans			int(10),						/*粉丝数 */
  feed			int(10),						/*微博数 */  
  ctime			datetime,						/*加入到客户群时间*/
  PRIMARY KEY(id),
  UNIQUE KEY(columnid,wbid),
  KEY(columnid,ctime,fans,feed),
  KEY(columnid,fans,feed),
  KEY(columnid,feed,fans)
);

-- --------------------------------------------------------

--
-- 表的结构 `wb_content`
--
DROP TABLE IF EXISTS zmt_content;
CREATE TABLE IF NOT EXISTS zmt_content (
  id 			int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  uid 			int(11) NOT NULL COMMENT '用户ID',
  title 		varchar(256)  CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标题',
  address	 	varchar(2048)  CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '地址URL',
  content 		mediumtext  CHARACTER SET utf8mb4 COMMENT '内容',
  media 		varchar(128)  CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '媒体',
  method		varchar(32)  CHARACTER SET utf8mb4 COMMENT '内容抓取的手段。包括：slide,pattern,analysis',
  comment 		mediumtext CHARACTER SET utf8mb4  DEFAULT NULL COMMENT '点评',
  tag			varchar(256) CHARACTER SET utf8mb4  COMMENT '标签的ID列表',
  ctime 		datetime DEFAULT NULL COMMENT '创建时间',
  utime 		datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (id),
  KEY(address(190)),
  KEY(uid,address(190)),
  KEY(uid,ctime),
  KEY(uid,title(190),ctime)
);

-- --------------------------------------------------------

--
-- 表的结构 `zmt_tag`
--
DROP TABLE IF EXISTS zmt_tag;
CREATE TABLE IF NOT EXISTS zmt_tag (
  id 			int(10) NOT NULL AUTO_INCREMENT,
  uid 			int(10) DEFAULT '0',
  value 		varchar(128) CHARACTER SET utf8mb4  DEFAULT NULL,
  ctime 		datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY(uid,ctime),
  KEY(value)
);

/*抓取内容与关键词的关系*/
DROP TABLE IF EXISTS zmt_contenttag;
CREATE TABLE IF NOT EXISTS zmt_contenttag (
  id 			int(10) NOT NULL AUTO_INCREMENT,
  uid 			int(10) DEFAULT '0',
  contentid		int(10) COMMENT '内容ID',
  tagid			int(10) COMMENT '标签ID',
  ctime 		datetime DEFAULT NULL,
  PRIMARY KEY (id),
  KEY(uid,tagid,contentid),
  KEY(contentid)
);


/*监控栏目的定义*/
DROP TABLE IF EXISTS xw_column;
CREATE TABLE IF NOT EXISTS xw_column (
  id 			int(10) NOT NULL AUTO_INCREMENT,
  networkid		int(10) DEFAULT 0 COMMENT '用户ID',
  serno			int(2) DEFAULT 0 COMMENT '序号',
  type			int(1) DEFAULT 0 COMMENT '栏目类型: 0 - 行业监控， 1 - 事件监控， 2-负面监控',
  relation		int(1) DEFAULT 0 COMMENT '关系：0 - 未知关系，1-自有，2-竞品',  
  title			varchar(64) COMMENT '栏目名称',
  fetchword		varchar(2048) COMMENT '内容抓取词',
  searchword	varchar(2048) COMMENT '结果过滤关键词',
  modified		int(10) default 0 COMMENT '有效修改次数',
  total			int(10) default 0 COMMENT '总量',
  today			int(10) default 0 COMMENT '当日总量',  
  status		int(1) DEFAULT 1 COMMENT '状态：1-活动，0-失效',  
  ctime 		datetime DEFAULT NULL COMMENT '创建时间',
  utime			datetime DEFAULT NULL COMMENT '更新时间',
  mtime			datetime DEFAULT NULL COMMENT '关键词修改时间',
  ftime			datetime DEFAULT NULL COMMENT '抓取时间',
  PRIMARY KEY (id),
  KEY(status,ftime),
  KEY(networkid, type, relation),
  KEY(networkid, type, status, serno),
  KEY(networkid, type, serno)      
);


/*数据关键词设置*/
DROP TABLE IF EXISTS wx_column;
CREATE TABLE IF NOT EXISTS wx_column (
  id 			int(10) NOT NULL AUTO_INCREMENT,
  networkid 	int(10) DEFAULT 0 COMMENT '用户ID',
  serno			int(2) DEFAULT 0 COMMENT '序号',
  type			int(1) DEFAULT 0 COMMENT '栏目类型: 0 - 微信监控， 1 - 事件监控，2 - 负面监控',
  relation		int(1) DEFAULT 0 COMMENT '关系：0 - 未知关系，1-自有，2-竞品',
  title			varchar(64) COMMENT '栏目名称',
  fetchword		varchar(2048) COMMENT '内容抓取词',
  searchword	varchar(2048) COMMENT '结果过滤关键词',
  modified		int(10) default 0 COMMENT '关键词有效修改次数',
  total			int(10) default 0 COMMENT '总量',
  today			int(10) default 0 COMMENT '当日总量', 
  status		int(1) DEFAULT 1 COMMENT '状态：1-活动，0-失效',
  ctime 		datetime DEFAULT NULL COMMENT '创建时间',
  utime			datetime DEFAULT NULL COMMENT '编辑时间',
  mtime			datetime DEFAULT NULL COMMENT '关键词修改时间',
  ftime			datetime DEFAULT NULL COMMENT '抓取时间',
  PRIMARY KEY (id),
  UNIQUE KEY(networkid, serno),
  KEY(networkid,type,relation),
  KEY(networkid,type,status,serno),
  KEY(status,ftime)
);

/*公众号的关系*/
DROP TABLE IF EXISTS wx_relation;
CREATE TABLE IF NOT EXISTS wx_relation (
  id 		int(10) NOT NULL AUTO_INCREMENT,
  networkid 	int(10) DEFAULT 0 COMMENT '用户ID',
  wxid		int(10) COMMENT '微信ID',	
  type		int(1) DEFAULT 0 COMMENT '关系：1-自有，2-竞品',
  ctime 	datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (id),
  KEY(networkid,type,wxid,ctime),
  KEY(ctime)
);

/*公告*/
DROP TABLE IF EXISTS bdp_message;
CREATE TABLE IF NOT EXISTS `bdp_message` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL COMMENT '管理员ID',
  `title` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标题',
  `content` varchar(256) CHARACTER SET utf8mb4 NOT NULL COMMENT '内容',
  `type` int(1) NOT NULL COMMENT '0全部1用户',
  `scope` varchar(1024) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '范围',
  `weight` int(11) NOT NULL DEFAULT '0' COMMENT '权重',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '发布状态，1发布',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
);

/*常见问题*/
CREATE TABLE IF NOT EXISTS `bdp_problem` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL COMMENT '管理员ID',
  `title` varchar(64) CHARACTER SET utf8mb4 DEFAULT NULL COMMENT '标题',
  `content` varchar(256) CHARACTER SET utf8mb4 NOT NULL COMMENT '内容',
  `weight` int(11) NOT NULL DEFAULT '0' COMMENT '权重',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '发布状态，1发布',
  `ctime` datetime NOT NULL COMMENT '创建时间',
  `utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`)
);


/*数据关键词设置*/
DROP TABLE IF EXISTS lt_column;
CREATE TABLE IF NOT EXISTS lt_column (
  id 			int(10) NOT NULL AUTO_INCREMENT,
  networkid		int(10) DEFAULT 0 COMMENT '用户ID',
  serno			int(2) DEFAULT 0 COMMENT '序号',
  type			int(1) DEFAULT 0 COMMENT '栏目类型: 0 - 论坛监控， 1 - 事件监控，2-负面监控',
  relation		int(1) DEFAULT 0 COMMENT '关系：0 - 未知关系，1-自有，2-竞品',  
  title			varchar(64) COMMENT '栏目名称',
  fetchword		varchar(2048) COMMENT '内容抓取词',
  searchword	varchar(2048) COMMENT '结果过滤关键词', 
  modified		int(5) COMMENT '关键词的修改次数，一定间隔时间内修改关键词不算次数',
  total			int(10) default 0 COMMENT '总量',
  today			int(10) default 0 COMMENT '当日总量',
  status		int(1) DEFAULT 1 COMMENT '状态：1-活动，0-失效',  
  ctime 		datetime DEFAULT NULL COMMENT '创建时间',
  utime			datetime DEFAULT NULL COMMENT '更新时间',
  mtime			datetime DEFAULT NULL COMMENT '上一次视为修改次数的关键词修改时间，一定间隔时间内修改关键词不算次数',
  ftime			datetime DEFAULT NULL COMMENT '抓取时间',
  PRIMARY KEY (id),
  KEY(networkid,type,relation),
  KEY(networkid,type,status,serno),
  KEY(status,ftime)
);



/*事件定义*/
DROP TABLE IF EXISTS sj_column;
CREATE TABLE IF NOT EXISTS sj_column (
  id 		int(10) NOT NULL AUTO_INCREMENT,
  networkid 	int(10) DEFAULT 0 COMMENT '用户ID',
  title		varchar(64) COMMENT '事件名称',
  occurred	varchar(10) COMMENT '事件时间',
  province	varchar(16) COMMENT '事件省份',
  city		varchar(32) COMMENT '事件城市',
  role		varchar(1024) COMMENT '事件中的人物',
  intro		mediumtext COMMENT '事件描述',
  keyword	varchar(2048) COMMENT '关键词',
  modified	int(5) COMMENT '关键词的修改次数，一定间隔时间内修改关键词不算次数',  
  serno		int(3) COMMENT '序号', 
  xwid		int(10) COMMENT '新闻栏目ID',
  wbid		int(10) COMMENT '微博栏目ID',
  wxid		int(10) COMMENT '微信栏目ID',
  ltid		int(10) COMMENT '论坛栏目ID',
  status	char(8) COMMENT '状态: 0 - 失效，1-有效，y-黄色预警，r-红色预警，o-橙色预警',   
  ctime 	datetime DEFAULT NULL COMMENT '创建时间',
  utime		datetime DEFAULT NULL COMMENT '编辑时间',
  stime		datetime DEFAULT NULL COMMENT '汇总时间',
  PRIMARY KEY (id),
  KEY(networkid,serno,ctime),
  KEY(wxid),
  KEY(wbid),
  KEY(xwid),
  KEY(ltid),
  KEY(stime)
);

/*负面的定义*/
DROP TABLE IF EXISTS fm_column;
CREATE TABLE IF NOT EXISTS fm_column (
  id 		int(10) NOT NULL AUTO_INCREMENT,
  networkid 	int(10) DEFAULT 0 COMMENT '用户ID',
  title		varchar(64) COMMENT '负面名称',  
  keyword	varchar(2048) COMMENT '关键词',   
  xwid		int(10) COMMENT '新闻栏目ID',
  wbid		int(10) COMMENT '微博栏目ID',
  wxid		int(10) COMMENT '微信栏目ID',
  ltid		int(10) COMMENT '论坛栏目ID',
  status	int(1) DEFAULT 1 COMMENT '状态：1-活动，0-失效',  
  ctime 	datetime DEFAULT NULL COMMENT '创建时间',
  utime		datetime DEFAULT NULL COMMENT '编辑时间',
  stime		datetime DEFAULT NULL COMMENT '汇总时间',
  PRIMARY KEY (id),
  KEY(wxid),
  KEY(wbid),
  KEY(xwid),
  KEY(ltid),  
  KEY(networkid,status,ctime) 
);

/*推荐信息*/
DROP TABLE IF EXISTS bdp_favorite;
CREATE TABLE IF NOT EXISTS bdp_favorite (
  id 		int(10) NOT NULL AUTO_INCREMENT,
  networkid	int(10) DEFAULT 0,              /*用户ID*/
  module    varchar(8),                     /*模块*/
  siteid	int(10),                        /*媒体ID*/
  sitename	varchar(256),                   /*媒体名称*/
  siteimg	varchar(256),					/*媒体图片*/
  siteurl	varchar(1024),                  /*文章的URL*/
  pageid	int(10),                        /*文章页面的id*/
  title		varchar(512),                   /*文章标题*/
  url		varchar(1024),                  /*文章链接*/
  comment	varchar(512),					/*评论*/
  readno    int(8),                         /*阅读次数*/
  chosen	int(1),							/*是否被选*/
  ptime		datetime DEFAULT NULL,         	/*发布时间*/
  ctime		datetime DEFAULT NULL,         	/*纳入查询结果的时间*/
  PRIMARY KEY (id),
  KEY(networkid,module,ptime,ctime),
  KEY(networkid,ptime,ctime),
  UNIQUE KEY(networkid,module,pageid)
);

/*自定义推荐*/
DROP TABLE IF EXISTS bdp_recommend;
CREATE TABLE IF NOT EXISTS bdp_recommend (
  id 			int(10) NOT NULL AUTO_INCREMENT,
  networkid		int(10) DEFAULT 0,              /*用户ID*/
  title	        varchar(256),                   /*标题*/
  imgurl		varchar(1024),                  /*压题图片*/
  clkurl        varchar(1024),                  /*图片链接*/
  summary       varchar(2048),                  /*摘要*/
  idlist        varchar(2048),                  /*推荐内容的ID列表*/
  reward        int(1),                         /*是否打赏*/
  readno        int(8),                         /*阅读次数*/
  praise        int(8),                         /*点赞数*/
  shareno       int(8),                         /*分享次数*/
  type          int(1),                         /*类型：0 - 公开，1 - 私有*/
  status        int(1),                         /*0 - 待审核，1 - 已审核或无需审核*/
  ctime			datetime DEFAULT NULL,         	/*创建时间*/
  utime			datetime DEFAULT NULL,         	/*更新时间*/
  PRIMARY KEY (id),
  KEY(status,ctime),
  KEY(networkid, id, type),
  KEY(networkid,ctime,utime)
);





DROP TABLE IF EXISTS xw_result;
CREATE TABLE IF NOT EXISTS xw_result (
  id 			int(10) NOT NULL AUTO_INCREMENT,
  networkid 	int(10) DEFAULT 0 COMMENT '用户ID',
  siteid		int(10) COMMENT '站点ID',
  sitename		varchar(256) COMMENT '站点名称',
  categoryid	int(10) COMMENT '站点所属分类ID',
  provinceid	int(10) COMMENT '站点所属地域',
  columnid 		int(10) DEFAULT 0 COMMENT '栏目ID',
  kwid			int(10) COMMENT '关键词的ID',
  origin		int(2) COMMENT '是否转发：0-未知，1-原创，2-转发',
  serno			int(2) DEFAULT 0 COMMENT '序号',
  type			int(1) DEFAULT 0 COMMENT '栏目类型: 0 - 行业监控， 1 - 事件监控， 2-负面监控',
  relation		int(1) DEFAULT 0 COMMENT '关系：0 - 未知关系，1-自有，2-竞品',
  pageid		bigint(10) unsigned COMMENT '页面的ID',
  title			varchar(256) COMMENT '匹配页面的标题',
  url			varchar(512) COMMENT '匹配文章的URL',
  ptime			datetime NOT NULL COMMENT '发布时间',
  ctime			datetime DEFAULT NULL COMMENT '纳入查询结果的时间',
  PRIMARY KEY (id,ptime),
  KEY(pageid),
  KEY(kwid,ptime),
  KEY(siteid,provinceid),
  KEY(columnid, pageid),
  KEY(columnid,ptime),
  KEY(ptime,columnid),
  KEY(networkid,type,ptime,relation,origin),
  KEY(networkid,type,serno,ptime,siteid),
  KEY(networkid,type,ptime,columnid),
  KEY(type,networkid,provinceid,columnid),
  KEY(networkid,columnid,ptime),
  KEY(columnid,type),
  KEY(columnid,kwid)
)PARTITION BY RANGE (TO_DAYS(ptime))
(
PARTITION P20150101 VALUES LESS THAN (TO_DAYS('2015-01-01')),
PARTITION P20160101 VALUES LESS THAN (TO_DAYS('2016-01-01')),
PARTITION P20160401 VALUES LESS THAN (TO_DAYS('2016-04-01')),
PARTITION P20160701 VALUES LESS THAN (TO_DAYS('2016-07-01')),
PARTITION P20161001 VALUES LESS THAN (TO_DAYS('2016-10-01')),
PARTITION P20170101 VALUES LESS THAN (TO_DAYS('2017-01-01')),
PARTITION P20170401 VALUES LESS THAN (TO_DAYS('2017-04-01')),
PARTITION P20170701 VALUES LESS THAN (TO_DAYS('2017-07-01')),
PARTITION P20171001 VALUES LESS THAN (TO_DAYS('2017-10-01')),
PARTITION P20180101 VALUES LESS THAN (TO_DAYS('2018-01-01')),
PARTITION P20180401 VALUES LESS THAN (TO_DAYS('2018-04-01')),
PARTITION P20180701 VALUES LESS THAN (TO_DAYS('2018-07-01')),
PARTITION P20181001 VALUES LESS THAN (TO_DAYS('2018-10-01')),
PARTITION P20190101 VALUES LESS THAN (TO_DAYS('2019-01-01')),
PARTITION P20190401 VALUES LESS THAN (TO_DAYS('2019-04-01')),
PARTITION P20190701 VALUES LESS THAN (TO_DAYS('2019-07-01')),
PARTITION P20191001 VALUES LESS THAN (TO_DAYS('2019-10-01'))
);



DROP TABLE IF EXISTS wx_result;
CREATE TABLE IF NOT EXISTS wx_result (
  id 		int(10) NOT NULL AUTO_INCREMENT,
  networkid	int(10) DEFAULT 0 COMMENT '用户ID',
  wxid		int(10) COMMENT '微信ID',
  wxname	varchar(256) COMMENT '微信名称',
  logo		varchar(256) COMMENT '公众号的Logo',
  province	int(10) COMMENT '站点所属地域',
  columnid 	int(10) DEFAULT 0 COMMENT '栏目ID',
  kwid		int(10) COMMENT '关键词的ID',
  contentid	bigint(10) unsigned COMMENT '文章页面的ID',
  title		varchar(512) COMMENT '文章标题',
  url		varchar(512) COMMENT '文章链接',
  serno		int(2) DEFAULT 0 COMMENT '序号',
  type		int(10) DEFAULT 0 COMMENT '栏目类型: 0 - 微信监控， 1 - 事件监控，2 - 负面监控',
  relation	int(1) DEFAULT 0 COMMENT '关系：0 - 未知关系，1-自有，2-竞品',
  readcount	int(8) DEFAULT 0 COMMENT '阅读次数',
  likecount	int(8) DEFAULT 0 COMMENT '点赞的次数',
  ptime		datetime NOT NULL COMMENT '发布时间',
  ctime		datetime DEFAULT NULL COMMENT '纳入查询结果的时间',
  PRIMARY KEY (id,ptime),
  KEY(wxid,province),
  KEY(kwid,ptime),
  KEY(columnid,contentid),
  KEY(contentid),
  KEY(networkid,ptime,type,relation),
  KEY(networkid,type,ptime,columnid),
  KEY(networkid,type,relation,wxid,contentid,ptime),
  KEY(networkid,type,wxid,contentid,ptime),
  KEY(type,networkid,province,columnid),
  KEY(columnid,contentid,ptime),
  KEY(columnid,ptime),
  KEY(ptime,columnid),
  KEY(columnid,type),
  KEY(networkid,serno,ptime,wxid),
  KEY(columnid,wxid),
  KEY(columnid,kwid)
)PARTITION BY RANGE (TO_DAYS(ptime))
(
PARTITION P20150101 VALUES LESS THAN (TO_DAYS('2015-01-01')),
PARTITION P20160101 VALUES LESS THAN (TO_DAYS('2016-01-01')),
PARTITION P20160401 VALUES LESS THAN (TO_DAYS('2016-04-01')),
PARTITION P20160701 VALUES LESS THAN (TO_DAYS('2016-07-01')),
PARTITION P20161001 VALUES LESS THAN (TO_DAYS('2016-10-01')),
PARTITION P20170101 VALUES LESS THAN (TO_DAYS('2017-01-01')),
PARTITION P20170401 VALUES LESS THAN (TO_DAYS('2017-04-01')),
PARTITION P20170701 VALUES LESS THAN (TO_DAYS('2017-07-01')),
PARTITION P20171001 VALUES LESS THAN (TO_DAYS('2017-10-01')),
PARTITION P20180101 VALUES LESS THAN (TO_DAYS('2018-01-01')),
PARTITION P20180401 VALUES LESS THAN (TO_DAYS('2018-04-01')),
PARTITION P20180701 VALUES LESS THAN (TO_DAYS('2018-07-01')),
PARTITION P20181001 VALUES LESS THAN (TO_DAYS('2018-10-01')),
PARTITION P20190101 VALUES LESS THAN (TO_DAYS('2019-01-01')),
PARTITION P20190401 VALUES LESS THAN (TO_DAYS('2019-04-01')),
PARTITION P20190701 VALUES LESS THAN (TO_DAYS('2019-07-01')),
PARTITION P20191001 VALUES LESS THAN (TO_DAYS('2019-10-01'))
);


DROP TABLE IF EXISTS wb_result;
/*项目表*/
create table wb_result
(
  id			bigint(10) unsigned not null auto_increment,
  networkid		int(10),						/*搜索结果所属用户*/
  columnid		int(10),						/*任务id*/
  wbid			bigint(10) unsigned not null, 	/*微博用户的ID*/
  content		text,							/*微博内容 */
  mid			bigint(10) unsigned,			/*原始内容ID*/
  kwid			int(10),                		/*关键词的ID*/
  url			varchar(64),					/*链接*/
  forward		int(10) default 0,				/*转发次数*/
  comment		int(10) default 0,				/*评论次数*/
  praise		int(10) default 0,				/*点赞次数*/
  fansrange  	int(10),               			/*粉丝的区段*/
  client		varchar(32),					/*发布使用的设备类型，如皮皮时光机*/
  gender		char(1),						/*性别 M, F*/
  level			int(5),							/*会员级别 */
  province		int(10),						/*所属省份 */
  city			int(10),						/*会员所属城市*/
  vtype			char(1),						/*V字认证的状态：C-企业认证， P-个人认证，N-没有认证*/
  type			int(1) DEFAULT 0 				/*栏目类型: 0 - 微博监控， 1 - 事件监控， 2-负面监控*/,
  relation		int(1),							/*关系：0 - 未知关系，1-自有，2-竞品*/
  ptime			datetime,						/*微博发布的时间 */
  ctime			datetime,						/*结果搜索入库的时间*/
  PRIMARY KEY(id,ptime),
  KEY(wbid),
  KEY(networkid,type,ptime,columnid,wbid),
  KEY(columnid, kwid),
  KEY(networkid,type,ptime,relation,mid,columnid),
  KEY(networkid,type,ptime,relation,wbid),
  KEY(networkid,type,mid,columnid,ptime,ctime),
  KEY(columnid,wbid),
  KEY(columnid,mid),
  KEY(columnid,ptime,province),
  KEY(ptime,type,networkid,relation),
  KEY(ptime,type,networkid,province,columnid),
  KEY(columnid,mid,wbid,ptime),
  KEY(columnid,ptime,wbid),
  KEY(columnid,ptime,fansrange)
)PARTITION BY RANGE (TO_DAYS(ptime))
(
PARTITION P20150101 VALUES LESS THAN (TO_DAYS('2015-01-01')),
PARTITION P20160101 VALUES LESS THAN (TO_DAYS('2016-01-01')),
PARTITION P20160401 VALUES LESS THAN (TO_DAYS('2016-04-01')),
PARTITION P20160701 VALUES LESS THAN (TO_DAYS('2016-07-01')),
PARTITION P20161001 VALUES LESS THAN (TO_DAYS('2016-10-01')),
PARTITION P20170101 VALUES LESS THAN (TO_DAYS('2017-01-01')),
PARTITION P20170401 VALUES LESS THAN (TO_DAYS('2017-04-01')),
PARTITION P20170701 VALUES LESS THAN (TO_DAYS('2017-07-01')),
PARTITION P20171001 VALUES LESS THAN (TO_DAYS('2017-10-01')),
PARTITION P20180101 VALUES LESS THAN (TO_DAYS('2018-01-01')),
PARTITION P20180401 VALUES LESS THAN (TO_DAYS('2018-04-01')),
PARTITION P20180701 VALUES LESS THAN (TO_DAYS('2018-07-01')),
PARTITION P20181001 VALUES LESS THAN (TO_DAYS('2018-10-01')),
PARTITION P20190101 VALUES LESS THAN (TO_DAYS('2019-01-01')),
PARTITION P20190401 VALUES LESS THAN (TO_DAYS('2019-04-01')),
PARTITION P20190701 VALUES LESS THAN (TO_DAYS('2019-07-01')),
PARTITION P20191001 VALUES LESS THAN (TO_DAYS('2019-10-01'))
);

DROP TABLE IF EXISTS lt_result;
CREATE TABLE IF NOT EXISTS lt_result (
  id 		int(10) NOT NULL AUTO_INCREMENT,
  networkid	int(10) DEFAULT 0 COMMENT '用户ID',
  siteid	int(10) COMMENT '论坛ID',
  sitename	varchar(256) COMMENT '论坛名称',
  siteurl	varchar(1024) COMMENT '文章的URL',
  columnid 	int(10) DEFAULT 0 COMMENT '栏目ID',
  kwid		int(10) COMMENT '关键词的ID',
  pageid	bigint(10) unsigned COMMENT '文章页面的ID',
  title		varchar(512) COMMENT '文章标题',
  url		varchar(1024) COMMENT '文章链接',
  serno		int(2) DEFAULT 0 COMMENT '序号',
  type		int(1) COMMENT '栏目类型: 0 - 论坛监控， 1 - 事件监控，2-负面监控',
  relation	int(1) DEFAULT 0 COMMENT '关系：0 - 未知关系，1-自有，2-竞品',
  ptime		datetime COMMENT '发布时间',
  ctime		datetime COMMENT '纳入查询结果的时间',
  PRIMARY KEY (id,ptime),
  KEY(kwid,ptime),
  KEY(columnid,ptime),
  KEY(ptime,columnid),
  KEY(columnid,pageid),
  KEY(columnid,siteid),
  KEY(columnid,kwid),
  KEY(columnid,type),
  KEY(networkid,ptime,type,relation),
  KEY(networkid,type,ptime,columnid),
  KEY(networkid,type,siteid,pageid,ptime),
  KEY(networkid,type,relation,siteid,pageid,ptime),
  KEY(networkid,type,serno,ptime,siteid)
)PARTITION BY RANGE (TO_DAYS(ptime))
(
PARTITION P20150101 VALUES LESS THAN (TO_DAYS('2015-01-01')),
PARTITION P20160101 VALUES LESS THAN (TO_DAYS('2016-01-01')),
PARTITION P20160401 VALUES LESS THAN (TO_DAYS('2016-04-01')),
PARTITION P20160701 VALUES LESS THAN (TO_DAYS('2016-07-01')),
PARTITION P20161001 VALUES LESS THAN (TO_DAYS('2016-10-01')),
PARTITION P20170101 VALUES LESS THAN (TO_DAYS('2017-01-01')),
PARTITION P20170401 VALUES LESS THAN (TO_DAYS('2017-04-01')),
PARTITION P20170701 VALUES LESS THAN (TO_DAYS('2017-07-01')),
PARTITION P20171001 VALUES LESS THAN (TO_DAYS('2017-10-01')),
PARTITION P20180101 VALUES LESS THAN (TO_DAYS('2018-01-01')),
PARTITION P20180401 VALUES LESS THAN (TO_DAYS('2018-04-01')),
PARTITION P20180701 VALUES LESS THAN (TO_DAYS('2018-07-01')),
PARTITION P20181001 VALUES LESS THAN (TO_DAYS('2018-10-01')),
PARTITION P20190101 VALUES LESS THAN (TO_DAYS('2019-01-01')),
PARTITION P20190401 VALUES LESS THAN (TO_DAYS('2019-04-01')),
PARTITION P20190701 VALUES LESS THAN (TO_DAYS('2019-07-01')),
PARTITION P20191001 VALUES LESS THAN (TO_DAYS('2019-10-01'))
);

/*文章的正负面评价*/
DROP TABLE IF EXISTS bdp_opinion;
CREATE TABLE IF NOT EXISTS bdp_opinion (
  id 		int(10) NOT NULL AUTO_INCREMENT,
  networkid	int(10) DEFAULT 0,              /*用户ID*/
  module    varchar(8),                     /*模块*/
  pageid	bigint(10) unsigned,            /*文章原文的id*/
  type		int(1),							/*0 - 未知， 1 - 正面评价，2 - 负面评价*/
  ptime		datetime DEFAULT NULL,         	/*发布时间*/
  ctime		datetime DEFAULT NULL,         	/*纳入正负面表的时间*/
  PRIMARY KEY (id),
  KEY(networkid,module,pageid),
  KEY(networkid,module,ptime),
  UNIQUE KEY(networkid,module,pageid)
);
