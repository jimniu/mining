package com.isd.dao.news.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.news.XwResultDAO;
import com.isd.entity.news.XwResult;
import com.isd.util.C;


public class XwResultDAOImpl extends GenericDAOImpl<XwResult, Integer> implements XwResultDAO {
	public XwResultDAOImpl() {
		super(XwResult.class);
	}
	
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String date = C.getDate(days);
		String hql = "select count(*) from XwResult where networkid="+networkid+" and type="+type+" and ptime>='"+date+"'";
		if(relation>0){
			hql += " and relation="+relation;
		}
		return this.getCountByHql(hql);
	}
	//根据用户、内容类型、时间得到媒体数量
	public int mediaCountByNetworkTypeDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select count(distinct siteid) from XwResult where networkid="+networkid+" and type="+type;
		if(relation>0){
			hql += " and relation="+relation;
		}
		hql += " and ptime>='"+C.getDate(days)+"'";
		return this.getCountByHql(hql);		
	}	
	//按照用户、内容类型、时间、转发或原发返回媒体数量
	public List originCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, Integer days){
		String hql = "select origin, count(distinct siteid) from XwResult where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			hql += " and relation=%RELATION% ";
		}
		hql += " group by origin order by origin desc";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));		
		return this.findByHql(hql);
	}
	
	//根据栏目显示条数
	public List columnreport(Integer networkid, int type, Integer relation, int days){
		String hql = "select c.id, c.title, count(*) as total, count(distinct siteid), c.relation from XwColumn c, XwResult r where r.networkid=%NETWORKID% and r.type=%TYPE%";
		if(relation>0){
			hql += " and r.relation=%RELATION%";
		}
		hql += " and r.ptime>='%TIME%' and r.columnid=c.id and c.status=1 group by c.id";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		hql = hql.replaceAll("%RELATION%", relation.toString());
		return this.findByHql(hql);
	}	
	
	public List sitereport(Integer networkid, Integer columnid, int days, int count){
		String sql = "select sitename, count(*) total, siteid from robot.xw_result where networkid=%NETWORKID% and columnid=%COLUMNID% and ptime>='%TIME%' group by siteid order by total desc limit 0,%COUNT%";
		sql = sql.replaceAll("%NETWORKID%", networkid.toString());
		sql = sql.replaceAll("%COLUMNID%", columnid.toString());
		sql = sql.replaceAll("%TIME%", C.getDate(days));
		sql = sql.replaceAll("%COUNT%", String.valueOf(count));
		
		return this.findBySql(sql);
	}
	//显示数据存量
	public List stockreport(Integer networkid, Integer type, Integer relation, int days){
		String sql;
		if(days<=90){
			sql = "select DATE_FORMAT(ptime,'%Y年%m月%d日') as date, columnid, count(*) as total from robot.xw_result where networkid=%NETWORKID% and type=%TYPE%"; 
		}else if(days>1000){
			sql = "select concat(DATE_FORMAT(ptime,'%Y年'), quarter(ptime),'季度') as date, columnid, count(*) as total from robot.xw_result where networkid=%NETWORKID% and type=%TYPE%";
		}else{
			sql = "select DATE_FORMAT(ptime,'%Y年%m月') as date, columnid, count(*) as total from robot.xw_result where networkid=%NETWORKID% and type=%TYPE%"; 
		}
		
		if(relation>0) {
			sql += " and relation=%RELATION%";
		}
		sql += " and ptime>='%TIME%' group by date, columnid order by date";
		
		sql = sql.replaceAll("%NETWORKID%", networkid.toString());
		sql = sql.replaceAll("%RELATION%", relation.toString());
		sql = sql.replaceAll("%TYPE%", String.valueOf(type));
		sql = sql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(sql);
	}	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(id), count(distinct siteid) from robot.xw_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			hql += " and relation=%RELATION% ";
		}
		hql += " group by date order by date asc";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		return this.findBySql(hql);		
	}	
	//根据用户ID、内容类型、自由或竞品以及时间范围，显示每个媒体分类的统计数
	public List categoryContentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select categoryid, count(id) total from robot.xw_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			hql += " and relation=%RELATION% ";
		}
		hql += " group by categoryid order by total desc";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		return this.findBySql(hql);			
	}
	
	//根据用户ID、内容类型、自有或竞品、转发还是原发以及时间范围，显示每个媒体分类的统计数
	public List categoryContentCountByUserTypeRelationOriginDate(Integer networkid, Integer type, Integer relation, Integer origin, int days){
		String hql = "select categoryid, count(id) total from robot.xw_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			hql += " and relation=%RELATION% ";
		}
		if(origin>0){
			hql+= " and origin=%ORIGIN% ";
		}
		hql += " group by categoryid order by total desc";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%ORIGIN%", origin.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		return this.findBySql(hql);			
	}	
	
	//根据用户ID、内容类型以及时间范围，显示每个媒体分类的媒体统计数
	public List categoryMediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select DATE_FORMAT(ptime,'%FORMAT%') as date, categoryid, count(distinct siteid) total from robot.xw_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			hql += " and relation=%RELATION% ";
		}
		hql += " group by date, categoryid order by date desc, categoryid asc ";
		if(days<10){
			hql = hql.replaceAll("%FORMAT%", "%Y%m%d");
		}else if(days<100){
			hql = hql.replaceAll("%FORMAT%", "%Y第%u周");
		}else if(days>100){
			hql = hql.replaceAll("%FORMAT%", "%Y年%m月");
		}
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		return this.findBySql(hql);			
	}
	
	//根据用户ID、内容类型、自由或竞品以及时间范围，显示每个栏目的统计数
	public List columnContentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select columnid, count(id) total from robot.xw_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			hql += " and relation=%RELATION% ";
		}
		hql += " group by columnid order by total desc";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%RELATION%", relation.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		return this.findBySql(hql);	
	}
	
	//根据用户、时间以及自有关系，按天统计媒体和文章的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days){
		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(distinct siteid), count(pageid) from robot.xw_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		if(relation>0){
			sql +=" and relation=%RELATION% ";
		}
		sql += " group by date order by date asc";
		
		sql = sql.replaceAll("%TYPE%", String.valueOf(type));
		sql = sql.replaceAll("%NETWORKID%", networkid.toString());
		sql = sql.replaceAll("%RELATION%", String.valueOf(relation));
		sql = sql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(sql);	
	}	
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer networkid, Integer type, String from, String to){
		String publicFrom = from.split(" ")[0]+" 00:00:00";
		String hql = "from XwResult r where r.networkid="+networkid+" and type="+type+" and ptime>='"+publicFrom+"' and ptime<='"+to+"' and ctime>='"+from+"' and ctime<='"+to+"' order by r.ctime asc";
		return this.findByHql(hql);		
	}	
	//根据用户ID，内容类型以及时间范围返回内容列表
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		String hql = "from XwResult r where r.networkid="+networkid+" and type="+type;
		if(relation>0) {
			hql += " and relation="+relation;
		}
		hql += " and ptime>='"+C.getDate(days)+"' order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}	
	//根据用户、类型以及时间，返回媒体
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		String hql = "select s, count(siteid) as total from XwResult r, XwSite s where r.networkid="+networkid+" and r.type="+type;
		if(relation>0) {
			hql += " and r.relation="+relation;
		}
		hql += " and r.ptime>='"+C.getDate(days)+"' and r.siteid=s.id group by r.siteid order by total desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}	
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		String hql = "from XwResult r where r.networkid="+networkid+" and type="+type+" and ptime>='"+C.getDate(days)+"'";
		if(relation>0){
			hql +=" and relation="+relation;
		}
		hql += " order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}	
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer columnid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		String hql = "select r.title, r.url, r.ptime, count(distinct r.pageid) as total, r.sitename, r.pageid,r.ctime, r.columnid, r.id from robot.xw_result r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		if(columnid!=null&&columnid>0){
			hql += " and r.columnid="+columnid;
		}
		
		hql += " group by r.title order by total desc,r.ptime desc";
		return this.findPageBySql(hql, offset, pagesize);		
	}
	
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid){
		String hql = "select r.title, r.url, r.ptime, r.sitename, r.pageid, r.origin from robot.xw_result r, robot.xw_result b where b.pageid="+contentid+" and r.title=b.title and r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		
		hql += "group by r.pageid order by r.ptime desc";
		return this.findBySql(hql);
	}	
	public int contentCountByColumnDate(Integer columnid, int days){
		String hql = "select count(*) from XwResult r where r.columnid="+columnid+" and r.ptime>='"+C.getDate(days)+"'";
		return this.getCountByHql(hql);
	}	
	public int contentCountByDate(int days){
		String hql = "select count(*) from XwResult r where r.ptime>='"+C.getDate(days)+"'";
		return this.getCountByHql(hql);
	}	
	//根据栏目搜索内容
	public List<XwResult> contentListByColumnDate(Integer networkid, Integer columnid, int days, int offset, int pagesize){
		String hql = "from XwResult r where networkid="+networkid+" and columnid="+columnid+" and ptime>='"+C.getDate(days)+"' order by ptime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}	
	//根据搜索条件返回内容
	public List<XwResult> listByFilter(Integer uid, XwResult result, String from, String to, int offset, int pagesize){
		String hql = "select r.pageid, r.title, r.sitename, r.url, r.ptime, r.id from XwResult r where r.networkid="+uid+" and type=0 ";
		hql += this.genSql(result, from, to);
		hql += "order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}	
	//根据搜索条件显示文章数
	public int countByFilter(Integer uid, XwResult result, String from, String to){
		String hql = "select count(distinct r.pageid) from XwResult r where r.networkid="+uid+" and type=0 ";
		hql += this.genSql(result, from, to);
		return this.getCountByHql(hql);	
	}
	
	private String genSql(XwResult result, String from, String to){
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
	public boolean exist(Integer columnid, Long pageid){
		String hql = "from XwResult where columnid="+columnid+" and pageid="+pageid;
		return this.findPageByHql(hql, 0, 1).size()>0;
	}	
	//根据栏目ID以及时间范围，按照省份列出新闻文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days){
		String date = C.getDate(days);
		String hql = "select provinceid, count(pageid) as total from XwResult where columnid="+columnid+" and ptime>='"+date+"' and provinceid>0 group by provinceid order by total desc";
		List list = this.findByHql(hql);
		return list;		
	}	
	


	
	
	

	
	
	
	

	

	

	

	

	//按天汇总
	public List daily(Integer days){
		String date = C.getDate(days);

		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, 'xw', networkid, columnid, 0, type, count(distinct siteid), count(pageid) from robot.xw_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}
	
	//按月汇总
	public List monthly(Integer days){
		String date = C.getMonthStart(days);

		String sql = "select DATE_FORMAT(ptime,'%Y-%m') as date, 'xw', networkid, columnid, 1, type, count(distinct siteid), count(pageid) from robot.xw_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}		
	
	//按照省份汇总
	public List listByProvince(Integer columnid){
		String hql = "select provinceid, count(*) from XwResult where columnid="+columnid+" group by provinceid";
		return this.findByHql(hql);
	}
	
	//根据省份显示用户的数量
	public List provincial(int days){
		String date = C.getDate(days);
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, networkid, 'xw', type, -1, provinceid, count(*) as total from XwResult where ptime>='"+date+"' and type=0 group by networkid, provinceid";
		List list = this.findByHql(hql);
		
		hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, networkid, 'xw', type, columnid, provinceid, count(*) as total from XwResult where ptime>='"+date+"' and type>0 group by networkid, provinceid,columnid";
		List lst = this.findByHql(hql);
		
		list.addAll(lst);
		return list;
	}	
}
