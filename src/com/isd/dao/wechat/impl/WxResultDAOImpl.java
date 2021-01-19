package com.isd.dao.wechat.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.wechat.WxResultDAO;
import com.isd.entity.wechat.WxResult;
import com.isd.util.C;


public class WxResultDAOImpl extends GenericDAOImpl<WxResult, Integer> implements WxResultDAO {
	public WxResultDAOImpl() {
		super(WxResult.class);
	}
	
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String date = C.getDate(days);
		String hql = "select count(*) from WxResult r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+date+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		return this.getCountByHql(hql);
	}
	//根据栏目显示条数
	public List columnreport(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select c.id, c.title, count(*) as total, count(distinct wxid), c.relation from WxColumn c, WxResult r where r.networkid=%NETWORKID% and r.type=%TYPE%";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		hql += " and r.ptime>='%TIME%' and r.columnid=c.id and c.status=1 group by c.id";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));		
		return this.findByHql(hql);		
	}	

	//根据自有标签以及竞品标签显示排名靠前的媒体，以自有标签的媒体排名为准
	public List sitereport(Integer networkid, Integer columnid, int days, int count){
		String sql = "select wxname, count(*) total, wxid from robot.wx_result where networkid=%NETWORKID% and columnid=%COLUMNID% and ptime>='%TIME%' group by wxid order by total desc limit 0,%COUNT%";
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
			sql = "select DATE_FORMAT(ptime,'%Y年%m月%d日') as date, columnid, count(*) as total from robot.wx_result where networkid=%NETWORKID% and type=%TYPE%";
		}else if(days>1000){
			sql = "select concat(DATE_FORMAT(ptime,'%Y年'), quarter(ptime),'季度') as date, columnid, count(*) as total from robot.wx_result where networkid=%NETWORKID% and type=%TYPE%";
		}else{
			sql = "select DATE_FORMAT(ptime,'%Y年%m月') as date, columnid, count(*) as total from robot.wx_result where networkid=%NETWORKID% and type=%TYPE%";
		}
		if(relation>0){
			sql += " and relation="+relation;
		}
		sql += " and ptime>='%TIME%' group by date,columnid order by date";
		sql = sql.replaceAll("%NETWORKID%", networkid.toString());
		sql = sql.replaceAll("%TYPE%", type.toString());
		sql = sql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(sql);		
	}	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(id),count(distinct wxid) from robot.wx_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
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
	//根据用户、时间以及自有关系，按天统计媒体和文章的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days){
		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(distinct wxid), count(contentid) from robot.wx_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
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
	//根据用户、内容类型以及起止时间返回数据
	public List<WxResult> contentListByUserTypeTimeRange(Integer networkid, Integer type, String from, String to){
		String publicFrom = from.split(" ")[0]+" 00:00:00";
		String hql = "from WxResult r where r.networkid="+networkid+" and r.type="+type+" and ptime>='"+publicFrom+"' and ptime<='"+to+"' and ctime>='"+from+"' and ctime<='"+to+"' order by r.ctime asc";
		return this.findByHql(hql);		
	}
	//返回指定时间指定关系的微信数量
	public int mediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, Integer days){
		String hql = "select count(distinct wxid) from WxResult where networkid="+networkid+" and type="+type+" and ptime>='"+C.getDate(days)+"'";
		if(relation>0){
			hql += " and relation="+relation;
		}
		return this.getCountByHql(hql);		
	}	
	//根据用户、内容类型、时间，返回微信列表
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int length){
		String hql = "select w, count(r.wxid) as total from WxResult r, WxWeixin w where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+C.getDate(days)+"' ";
		if(relation>0){
			hql += " and relation="+relation;
		}
		hql += " and r.wxid=w.id group by r.wxid order by total desc";
		return this.findPageByHql(hql, offset, length);		
	}	
	//根据用户ID，内容类型、排序以及时间范围返回内容
	public List<WxResult> contentListByNetworkTypeDateOrder(Integer networkid, Integer type, String order, int days, int offset, int pagesize){
		String date = C.getDate(days);
		String hql = "from WxResult r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+date+"' order by r."+order+" desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		String date = C.getDate(days);
		String hql = "from WxResult r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+date+"'";
		if(relation>0){
			hql += " and relation="+relation;
		}
		
		hql += " order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);				
	}
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer columnid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		String hql = "select r.title, r.url, r.ptime, count(distinct r.contentid) as total, r.wxname, r.contentid, r.ctime, r.columnid, r.id from robot.wx_result r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		if(columnid!=null&&columnid>0){
			hql += " and r.columnid="+columnid;
		}
		hql += " group by r.title order by total desc, r.ptime desc";
		return this.findPageBySql(hql, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topReadListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		String hql = "select r.title, r.url, r.ptime, r.readcount, r.wxname, r.contentid, r.ctime, r.columnid, r.id from robot.wx_result r where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		
		hql += " group by r.contentid order by readcount desc, r.ptime desc";
		return this.findPageBySql(hql, offset, pagesize);
	}	
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid){
		String hql = "select r.title, r.url, r.ptime, r.wxname from robot.wx_result r, robot.wx_result b where b.contentid="+contentid+" and r.title=b.title and r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		
		hql += "group by r.contentid order by r.ptime desc";
		return this.findBySql(hql);
	}
	//根据栏目ID，排序以及时间范围返回内容
	public List<WxResult> contentListByColumnDateOrder(Integer columnid, String order, int days, int offset, int pagesize){
		String date = C.getDate(days);
		String hql = "from WxResult r where r.columnid="+columnid+" and r.ptime>='"+date+"' order by r."+order+" desc";
		return this.findPageByHql(hql, offset, pagesize);
	}	
	//根据栏目ID、时间范围返回数据量
	public int contentCountByColumnDate(Integer columnid, int days){
		String date = C.getDate(days);
		String hql = "select count(*) from WxResult r where r.columnid="+columnid+" and r.ptime>='"+date+"'";
		return this.getCountByHql(hql);
	}	
	
	//判断当天是否有搜索结果
	public Integer contentCountByDate(int days){
		String date = C.getDate(days);
		String hql = "select count(*) from WxResult r where r.ptime>='"+date+"'";
		return this.getCountByHql(hql);
	}	
	//根据公众号返回内容列表
	public List<WxResult> contentListByMedia(Integer wxid, int offset, int pagesize){
		String hql = "from WxResult r where r.wxid="+wxid+" order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);			
	}	
	//根据栏目ID以及时间范围，按照省份列出微博文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days){
		String date = C.getDate(days);
		String hql = "select province, count(contentid) as total from WxResult where columnid="+columnid+" and ptime>='"+date+"' and province>0 group by province order by total desc";
		List list = this.findByHql(hql);
		return list;		
	}
	
	
	
	
	
	

	
	
	
	

	
	
	
	//判断文章是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Long contentid){
		String hql = "from WxResult where columnid="+columnid+" and contentid="+contentid;
		List list = this.findByHql(hql);
		return list.size()>0;	
	}
	//判断公众号是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Integer wxid){
		String hql = "from WxResult where columnid="+columnid+" and wxid="+wxid;
		return this.findByHql(hql).size()>0;		
	}

	
	

	//根据搜索条件返回内容
	public List listByFilter(Integer uid, WxResult result, String from, String to, int offset, int pagesize){
		String hql = "select r.contentid,r.title, r.wxname, r.url, r.ptime, r.id, r.wxid from WxResult r where r.networkid="+uid+" ";
		hql += this.genSql(result, from, to);
		hql += "order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	//根据搜索条件显示文章数
	public int countByFilter(Integer uid, WxResult result, String from, String to){
		String hql = "select count(distinct r.contentid) from WxResult r where r.networkid="+uid+" ";
		hql += this.genSql(result, from, to);
		return this.getCountByHql(hql);
	}
	
	//根据省份显示用户的数量
	public List provincial(int days){
		String date = C.getDate(days);
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, networkid, 'wx', type, -1, province, count(*) as total from WxResult where ptime>='"+date+"' and type=0 group by networkid, province";
		List list = this.findByHql(hql);
		
		hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, networkid, 'wx', type, columnid, province, count(*) as total from WxResult where ptime>='"+date+"' and type>0 group by networkid, province, columnid";
		List lst = this.findByHql(hql);
		
		list.addAll(lst);
		return list;
	}	
	
	private String genSql(WxResult result, String from, String to){
		String code = "";
		if(result==null)
			return code;
		
		if(result.getColumnid()!=null){
			code += "and r.columnid="+result.getColumnid()+" ";
		}
		
		if(result.getTitle()!=null&&result.getTitle().length()>0){
			code += "and r.title like '%"+result.getTitle()+"%' ";
		}
		
		if(result.getWxname()!=null&&result.getWxname().length()>0){
			code += "and r.wxname like '%"+result.getWxname()+"%' ";
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

		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, 'wx', networkid, columnid, 0, type, count(distinct wxid), count(contentid) from robot.wx_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}
	
	//按月汇总
	public List monthly(Integer days){
		String date = C.getMonthStart(days);

		String sql = "select DATE_FORMAT(ptime,'%Y-%m') as date, 'wx', networkid, columnid, 1, type, count(distinct wxid), count(contentid) from robot.wx_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}	
}
