package com.isd.dao.bbs.impl;

import java.util.Date;
import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.bbs.LtResultDAO;
import com.isd.entity.bbs.LtResult;
import com.isd.util.C;


public class LtResultDAOImpl extends GenericDAOImpl<LtResult, Integer> implements LtResultDAO {
	public LtResultDAOImpl() {
		super(LtResult.class);
	}
	
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String date = C.getDate(days);
		String hql = "select count(*) from LtResult where networkid="+networkid+" and type="+type;
		if(relation>0){
			hql += " and relation="+relation;
		}		
		hql += " and ptime>='"+date+"'";
		return this.getCountByHql(hql);	
	}
	//根据用户ID，内容类型以及时间范围返回论坛量
	public int mediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select count(distinct siteid) from LtResult where networkid="+networkid+" and type="+type;
		if(relation>0){
			hql += " and relation="+relation;
		}
		hql += " and ptime>='"+C.getDate(days)+"'";
		return this.getCountByHql(hql);
	}	
	//根据栏目显示条数
	public List columnreport(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select c.id, c.title, count(*) as total, count(distinct siteid), c.relation from LtColumn c, LtResult r where r.networkid=%NETWORKID% and r.type=%TYPE%";
		if(relation>0){
			hql += " and r.relation=%RELATION%";
		}		
		hql += " and r.ptime>='%TIME%' and r.columnid=c.id and c.status=1 group by c.id";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));		
		return this.findByHql(hql);	
	}	
	
	public List sitereport(Integer networkid, Integer columnid, int days, int count){
		String sql = "select sitename, count(*) total, siteid from robot.lt_result where networkid=%networkid% and columnid=%COLUMNID% and ptime>='%TIME%' group by siteid order by total desc limit 0,%COUNT%";
		sql = sql.replaceAll("%networkid%", networkid.toString());
		sql = sql.replaceAll("%COLUMNID%", columnid.toString());
		sql = sql.replaceAll("%TIME%", C.getDate(days));
		sql = sql.replaceAll("%COUNT%", String.valueOf(count));
		
		return this.findBySql(sql);
	}	
	//显示数据存量
	public List stockreport(Integer networkid, Integer type, Integer relation, int days){
		String sql;
		if(days<=90){
			sql = "select DATE_FORMAT(ptime,'%Y年%m月%d日') as date, columnid, count(*) as total from robot.lt_result where networkid=%NETWORKID% and type=%TYPE%";
		}else if(days>1000){
			sql = "select concat(DATE_FORMAT(ptime,'%Y年'), quarter(ptime),'季度') as date, columnid, count(*) as total from robot.lt_result where networkid=%NETWORKID% and type=%TYPE%"; 
		}else{
			sql = "select DATE_FORMAT(ptime,'%Y年%m月') as date, columnid, count(*) as total from robot.lt_result where networkid=%NETWORKID% and type=%TYPE%";
		}
		if(relation>0){
			sql += " and relation=%RELATION% ";
		}
		
		sql += " and ptime>='%TIME%' group by date,columnid order by date";
		sql = sql.replaceAll("%NETWORKID%", networkid.toString());
		sql = sql.replaceAll("%RELATION%", relation.toString());
		sql = sql.replaceAll("%TYPE%", type.toString());
		sql = sql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(sql);		
	}	
	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(id), count(distinct siteid) from robot.lt_result where networkid=%networkid% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			hql += " and relation=%RELATION% ";
		}
		hql += " group by date order by date asc";
		
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%networkid%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		return this.findBySql(hql);		
	}	
	//根据用户、时间以及自有关系，按天统计媒体和文章的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days){
		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(distinct siteid), count(pageid) from robot.lt_result where networkid=%networkid% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			sql +=" and relation=%RELATION% ";
		}
		sql += " group by date order by date asc";
		sql = sql.replaceAll("%TYPE%", String.valueOf(type));
		sql = sql.replaceAll("%networkid%", networkid.toString());
		sql = sql.replaceAll("%RELATION%", String.valueOf(relation));
		sql = sql.replaceAll("%TIME%", C.getDate(days));		
		return this.findBySql(sql);	
	}
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer uid, Integer type, String from, String to){
		String publicFrom = from.split(" ")[0]+" 00:00:00";
		String hql = "from LtResult r where networkid="+uid+" and type="+type+" and ptime>='"+publicFrom+"' and ptime<='"+to+"' and ctime>='"+from+"' and ctime<='"+to+"' order by r.ctime asc";
		return this.findByHql(hql);		
	}	
	//根据用户ID，内容类型以及时间范围返回论坛列表
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		String hql = "select sitename, siteurl, count(siteid) as total from LtResult r where r.networkid="+networkid+" and type="+type;
		if(relation>0){
			hql += " and relation="+relation;
		}
		hql += " and ptime>='"+C.getDate(days)+"' group by siteid order by total desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}	
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		String date = C.getDate(days);
		String hql = "from LtResult r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+date+"'";
		if(relation>0){
			hql += " and relation="+relation;
		}
		
		hql += " order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);	
	}	
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		String hql = "select r.title, r.url, r.ptime, count(distinct r.pageid) as total, r.sitename, r.pageid, r.ctime, r.columnid from robot.lt_result r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		
		hql += " group by r.title order by total desc, r.ptime desc";
		return this.findPageBySql(hql, offset, pagesize);	
	}
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid){
		String hql = "select r.title, r.url, r.ptime, r.sitename, r.pageid from robot.lt_result r, robot.lt_result b where b.pageid="+contentid+" and r.title=b.title and r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		
		hql += "group by r.pageid order by r.ptime desc";
		return this.findBySql(hql);
	}	
	
	
	
	
	
	
	//根据用户ID，汇总当天的内容总数，用于首页
	public List columnContentCountByUserTypeToday(Integer networkid, Integer type){
		String sql = "select columnid, count(id) total from robot.lt_result where networkid="+networkid+" and type="+type+" and ptime>='"+C.getDateStart(new Date())+"' group by columnid";
		return this.findBySql(sql);
	}


	
	//根据用户，返回符合关键词的内容
	public List contentListByUserTypeDate(Integer networkid, Integer type, int offset, int length){
		String hql = "from LtResult r where networkid="+networkid+" and type="+type+" order by r.ptime desc";
		return this.findPageByHql(hql, offset, length);
	}
	

	//根据栏目返回内容
	public List contentListByColumn(Integer columnid, int offset, int pagesize){
		String hql = "from LtResult r where r.columnid="+columnid+" order by r.ptime desc, ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	//根据用户，返回公众号
	public List openidListByUserType(Integer networkid, Integer type, int offset, int length){
		String hql = "select w, r, max(r.ptime) as mptime from LtResult r, LtSite w where r.networkid="+networkid+" and r.type="+type+" and r.siteid=w.id group by r.siteid, r.type order by r.ptime desc";
		return this.findPageByHql(hql, offset, length);		
	}

	
	public List openidListByUserTypeRelation(Integer networkid, Integer type, Integer relation, int offset, int length){
		String hql = "select w, r, max(r.ptime) as mptime from LtResult r, LtSite w, LtPage c where r.networkid="+networkid+" and r.type="+type+" and r.relation="+relation+" and r.siteid=w.id and r.pageid=c.id group by r.siteid,r.type order by r.ptime desc";
		return this.findPageByHql(hql, offset, length);		
	}	
	
	
	//今天内容的更新数量
	public int contentCountByColumnToday(Integer columnid){
		String hql = "select count(*) from LtResult where columnid="+columnid+" and ptime>='"+C.dateToString(new Date(), "yyyy-MM-dd 00:00:00")+"'";
		return this.getCountByHql(hql);
	}
	//根据用户ID，返回文章数量
	public int contentCountByColumn(Integer columnid){
		String hql = "select count(*) from LtResult where columnid="+columnid;
		return this.getCountByHql(hql);
	}
	//今天微信帐号的更新数量
	public int openidCountByUserTypeToday(Integer networkid, Integer type){
		String hql = "select count(distinct siteid) from LtResult where networkid="+networkid+" and type="+type+" and ptime>='"+C.dateToString(new Date(), "yyyy-MM-dd 00:00:00")+"'";
		return this.getCountByHql(hql);
	}
	public int openidCountByUserType(Integer networkid, Integer type){
		String hql = "select count(distinct siteid) from LtResult where networkid="+networkid+" and type="+type;
		return this.getCountByHql(hql);
	}
	
	
	//根据用户ID，结果类型，返回数量
	public int openidCountByUserTypeRelation(Integer networkid, Integer type, Integer relation){
		String hql = "select count(distinct siteid) from LtResult where networkid="+networkid+" and type="+type+" and relation="+relation;
		return this.getCountByHql(hql);
	}		
	
	//判断文章是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Long pageid){
		String hql = "from LtResult where columnid="+columnid+" and pageid="+pageid;
		List list = this.findByHql(hql);
		return list.size()>0;	}
	
	//判断公众号是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Integer siteid){
		String hql = "from LtResult where columnid="+columnid+" and siteid="+siteid;
		return this.findByHql(hql).size()>0;		
	}
	




	
	//根据搜索条件返回内容
	public List listByFilter(Integer uid, LtResult result, String from, String to, int offset, int pagesize){
		String hql = "select r.pageid,r.title, r.sitename, r.url, r.ptime, r.id from LtResult r where r.networkid="+uid+" ";
		hql += this.genSql(result, from, to);
		hql += "order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	//根据搜索条件显示文章数
	public int countByFilter(Integer uid, LtResult result, String from, String to){
		String hql = "select count(r.pageid) from LtResult r where r.networkid="+uid+" ";
		hql += this.genSql(result, from, to);
		return this.getCountByHql(hql);
	}

	private String genSql(LtResult result, String from, String to){
		String code = "";
		if(result==null)
			return code;
		
		if(result.getColumnid()!=null){
			code += "and r.columnid="+result.getColumnid()+" ";
		}
		
		if(result.getTitle()!=null&&result.getTitle().length()>0){
			code += "and r.title like '%"+result.getTitle()+"%' ";
		}
		
		if(result.getSitename()!=null&&result.getSitename().length()>0){
			code += "and r.sitename like '%"+result.getSitename()+"%' ";
		}
		
		if(from!=null&&from.length()>0){
			code += "and r.ptime >='"+from+" 00:00:00' ";
		}

		if(to!=null&&to.length()>0){
			code += "and r.ptime <='"+to+" 23:59:59' ";
		}		
		return code;
	}

	//按天汇总
	public List daily(Integer days){
		String date = C.getDate(days);

		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, 'lt', networkid, columnid, 0, type, count(distinct siteid), count(pageid) from robot.lt_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}
	
	//按月汇总
	public List monthly(Integer days){
		String date = C.getMonthStart(days);

		String sql = "select DATE_FORMAT(ptime,'%Y-%m') as date, 'lt', networkid, columnid, 1, type, count(distinct siteid), count(pageid) from robot.lt_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}		
}
