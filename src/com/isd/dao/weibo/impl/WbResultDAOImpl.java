package com.isd.dao.weibo.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.entity.weibo.WbColumn;
import com.isd.entity.weibo.WbResult;
import com.isd.entity.weibo.WbUser;
import com.isd.util.C;


public class WbResultDAOImpl extends GenericDAOImpl<WbResult, Long> implements WbResultDAO {
	public WbResultDAOImpl() {
		super(WbResult.class);
	}
	
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String date = C.getDate(days);

		String hql = "select count(r.mid) from WbResult r where r.networkid="+networkid+" and r.type="+type+" and ptime>='"+date+"'";
		if(relation>0){
			hql += " and relation="+relation;
		}
		Integer count = this.getCountByHql(hql);
		return count;
	}
	//根据用户ID，内容类型以及时间范围返回数据量
	public int mediaCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String date = C.getDate(days);

		String hql = "select count(distinct r.wbid) from WbResult r where r.networkid="+networkid+" and r.type="+type+" and ptime>='"+date+"'";
		if(relation>0){
			hql += " and relation="+relation;
		}		
		Integer count = this.getCountByHql(hql);
		return count;
	}
	//按照微博用户统计符合栏目关键词的微博数量
	public int contentCountByColumnWbid(Integer columnid, Long wbid){
		String hql = "select count(*) from WbResult where columnid="+columnid+" and wbid="+wbid;
		return this.getCountByHql(hql);
	}	
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer networkid, Integer type, String from, String to){
		String createFrom = from.split(" ")[0]+" 00:00:00";
		String hql = "from WbResult r, WbUser u where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+createFrom+"' and r.ptime<='"+to+"' and r.ctime>='"+from+"' and r.ctime<='"+to+"' and u.wbid=r.wbid order by r.ctime asc";
		return this.findByHql(hql);
	}	
	//根据用户ID、内容类型、关系、时间范围返回内容	
	public List contentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		String hql = "from WbResult r, WbUser u where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+C.getDate(days)+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		hql += " and u.wbid=r.wbid ";
		hql += " order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}	
	//根据栏目和用户id，返回微博内容
	public List<WbResult> contentListByColumnWbid(Integer columnid, Long wbid, Integer offset, Integer pagesize){
		String hql = "from WbResult r where r.columnid="+columnid+" and r.wbid="+wbid+" order by r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}	
	//根据用户、类型以及时间，返回微博用户
	public List mediaListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days,  int offset, int pagesize){
		String hql = "select u, count(r.wbid) as total from WbResult r, WbUser u where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+C.getDate(days)+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		hql += " and u.wbid=r.wbid";
		hql += " group by r.wbid order by total desc";
		return this.findPageByHql(hql, offset, pagesize);
	}	
	
	//根据栏目显示条数
	public List columnreport(Integer networkid, int type, int days){
		String hql = "select c.id, c.title, count(*), count(distinct wbid), c.relation as total from WbColumn c, WbResult r where r.networkid=%NETWORKID% and r.type=%TYPE% and r.ptime>='%TIME%' and r.columnid=c.id and c.status=1 group by c.id";
		hql = hql.replaceAll("%TYPE%", String.valueOf(type));
		hql = hql.replaceAll("%NETWORKID%", networkid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));		
		return this.findByHql(hql);
	}
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(id), count(distinct wbid) from robot.wb_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
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
	//根据columnid、时间范围，显示每一天的总数
	public List dailyContentCountByColumnDate(Integer columnid, int days){
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(id) from robot.wb_result where columnid=%COLUMNID% and ptime>='%TIME%' group by date order by date asc";
		hql = hql.replaceAll("%COLUMNID%", columnid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));	
		return this.findBySql(hql);			
	}

	//列出给定栏目每天的评论数和转发数
	public List dailyCommentForwardSumByColumnDate(Integer columnid, int days){
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, sum(forward), sum(comment) from robot.wb_result where columnid=%COLUMNID% and ptime>='%TIME%' group by date order by date asc";
		hql = hql.replaceAll("%COLUMNID%", columnid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(hql);
	}
	//列出给定栏目每天的意见领袖数
	public List dailyVipByColumnDate(Integer columnid, int days){
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(*) from robot.wb_result where columnid=%COLUMNID% and ptime>='%TIME%' and fansrange>=100000 group by date order by date asc";
		hql = hql.replaceAll("%COLUMNID%", columnid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(hql);
	}


	//列出给定栏目按照粉丝数量统计的转发数和评论数
	public List commentForwardSumByColumnFansrange(Integer columnid, int days){
		String hql = "select fansrange as fans, sum(forward), sum(comment) from robot.wb_result where columnid=%COLUMNID% and ptime>='%TIME%' group by fansrange order by fansrange asc";
		hql = hql.replaceAll("%COLUMNID%", columnid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(hql);
	}

	//列出给定栏目在指定时间范围内按照设备的统计数量
	public List contentCountByColumnDevice(Integer columnid, int days){
		String hql = "select client, count(*) total from robot.wb_result where columnid=%COLUMNID% and ptime>='%TIME%' group by client order by total desc";
		hql = hql.replaceAll("%COLUMNID%", columnid.toString());
		hql = hql.replaceAll("%TIME%", C.getDate(days));
		return this.findBySql(hql);
	}
	//根据用户、时间以及自有关系，按天统计微博和博文的数量。用在集成报表的总览
	public List dailyMediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days){
		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, count(distinct wbid), count(mid) from robot.wb_result where networkid=%NETWORKID% and type=%TYPE% and ptime>='%TIME%' ";
		
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
	
	//根据用户、内容类型以及时间范围统计微博信息
	public List mediaSumByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String sql = "select count(u.wbid), sum(fans), sum(feed) from (select wbid from robot.wb_result where networkid="+networkid+" and type="+type;
		if(relation>0) {
			sql += " and relation="+relation;
		}
		sql += " and ptime>='"+C.getDate(days)+"' group by wbid) as r, robot.wb_user u where u.wbid=r.wbid";
		return this.findBySql(sql);
	}
	
	//根据用户、内容类型以及时间范围统计博文信息
	public List contentSumByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		String sql = "select count(mid), sum(forward), sum(comment), sum(praise) from WbResult r where r.networkid="+networkid+" and r.type="+type;
		if(relation>0) {
			sql += " and relation="+relation;
		}

		sql += " and r.ptime>='"+C.getDate(days)+"'";
		return this.findByHql(sql);
	}

	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		String hql = "select r.content, r.url, r.ptime, r.forward, u.nickname, r.ctime, r.columnid from WbResult r, WbUser u where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+from+"' and r.ptime<='"+to+"'";
		if(relation>0){
			hql += " and r.relation="+relation;
		}
		hql += " and u.wbid=r.wbid group by r.mid order by r.forward desc, r.ptime desc";
		return this.findPageByHql(hql, offset, pagesize);		
	}
	//根据栏目和排序，返回微博内容
	public List contentListByColumnDateOrder(Integer networkid, Integer type, Integer columnid, int days, String keyword, String order, Integer offset, Integer pagesize){
		String date = C.getDate(days);
		
		String hql = "from WbResult r, WbUser u where r.networkid="+networkid+" and r.type="+type+" and r.ptime>='"+date+"'";
		if(columnid>0){
			hql += " and r.columnid="+columnid+" ";
		}
		hql += " and r.wbid=u.wbid ";
		
		if(keyword!=null&&keyword.trim().length()>0){
			hql += " and r.content like '%"+keyword+"%' ";
		}
		
		if(order==null || order.equals("ptime")){
			hql += "order by r.ptime desc";
		}else if(order.equals("ctime")){
			hql += "order by r.ctime desc";
		}else if(order.equals("forward")){
			hql += "order by r.forward desc";
		}else if(order.equals("comment")){
			hql += "order by r.comment desc";
		}else if(order.equals("praise")){
			hql += "order by r.praise desc";
		}
		return this.findPageByHql(hql, offset, pagesize);
	}	
	public Integer contentCountByColumnDate(Integer columnid, int days){
		String date = C.getDate(days);
		
		String hql = "select count(r.mid) from WbResult r where r.columnid="+columnid +" and r.ptime>='"+date+"'";
		Integer count = this.getCountByHql(hql);
		return count;
	}
	//判断当天是否有搜索结果
	public Integer contentCountByDate(int days){
		String date = C.getDate(days);
		
		String hql = "select count(r.mid) from WbResult r where r.ptime>='"+date+"'";
		Integer count = this.getCountByHql(hql);
		return count;
	}
	
	public Integer mediaCountByColumnDate(Integer columnid, int days){
		String date = C.getDate(days);
		
		String hql = "select count(distinct r.wbid) from WbResult r where r.columnid="+columnid +" and r.ptime>='"+date+"'";
		Integer count = this.getCountByHql(hql);
		return count;
	}	
	
	

	//判断当前任务下是否存在该微博用户
	public List<WbResult> findByColumnAndWbid(Integer columnid, Long wbid){
		String hql = "from WbResult r where r.columnid="+columnid+" and r.wbid="+wbid;
		return this.findByHql(hql);
	}
	
	//判断当前任务下是否存在该微博
	public List<WbResult> findByColumnAndMid(Integer columnid, Long mid){
		String hql = "from WbResult r where r.columnid="+columnid+" and r.mid="+mid;
		return this.findByHql(hql);
	}
	
	//根据栏目ID以及时间范围，按照省份列出微博用户数
	public List provinceMediaCountByColumnDate(Integer columnid, int days){
		String date = C.getDate(days);
		String hql = "select province, count(distinct wbid) as total from WbResult where columnid="+columnid+" and ptime>='"+date+"' and province>0 group by province order by total desc";
		List list = this.findByHql(hql);
		return list;
	}
	//根据栏目ID以及时间范围，按照省份列出微博用户数
	public List provinceContentCountByColumnDate(Integer columnid, int days){
		String date = C.getDate(days);
		String hql = "select province, count(mid) as total from WbResult where columnid="+columnid+" and ptime>='"+date+"' and province>0 group by province order by total desc";
		List list = this.findByHql(hql);
		return list;		
	}
	


	//按照任务ID，得到总的粉丝的数量
	public Long fansCountByColumn(Integer columnid){
		String hql = "select sum(fans) from (select distinct r.wbid, u.fans from robot.wb_result r, robot.wb_user u where r.columnid="+columnid+" and u.wbid=r.wbid ) as t";
		Long count = this.getCountLongBySql(hql);
		return count;
	}
	
	//按照任务ID，得到总的微博的数量
	public Long feedCountByColumn(Integer columnid){
		String hql = "select sum(feed) from (select distinct r.wbid, u.feed from robot.wb_result r, robot.wb_user u  where r.columnid="+columnid+" and u.wbid=r.wbid ) as t";
		Long count = this.getCountLongBySql(hql);
		return count;	
	}
	//按照任务ID，得到包含关键词的文章数量
	public Integer hitCountByColumn(Integer columnid){
		String hql = "select count(mid) from robot.wb_result r where r.columnid="+columnid;
		Integer count = this.getCountBySql(hql);
		return count;
	}
	
	
	//根据任务ID以及wbid删除结果
	public void clearByColumnAndWbid(Integer columnid, String wbid){
		String hql = "delete from WbResult where columnid="+columnid+" and wbid="+wbid;
		this.executeByHql(hql);
	}
	
	//根据任务ID删除结果
	public void clearByColumn(Integer columnid){
		String hql = "delete from WbResult where columnid="+columnid;
		this.executeByHql(hql);
	}
	

	//按天汇总微博的数量
	public List daily(Integer days){
		String date = C.getDate(days);

		String sql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, 'wb', networkid, columnid, 0, type, count(distinct wbid), count(distinct mid) from robot.wb_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}
	
	//按月汇总微博的数量
	public List monthly(Integer days){
		String date = C.getMonthStart(days);

		String sql = "select DATE_FORMAT(ptime,'%Y-%m') as date, 'wb', networkid, columnid, 1, type, count(distinct wbid), count(distinct mid) from robot.wb_result where ptime>='"+date+"' group by columnid, date";
		return this.findBySql(sql);

	}	
	
	//根据省份显示用户的数量
	public List provincial(int days){
		String date = C.getDate(days);
		
		String hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, networkid, 'wb', type, -1, province, count(distinct wbid) as total from WbResult where ptime>='"+date+"' and type=0 group by networkid, province";
		List list = this.findByHql(hql);
		
		hql = "select DATE_FORMAT(ptime,'%Y-%m-%d') as date, networkid, 'wb', type, columnid, province, count(distinct wbid) as total from WbResult where ptime>='"+date+"' and type>0 group by networkid, province,columnid";
		List lst = this.findByHql(hql);
		
		list.addAll(lst);
		return list;
	}	
	
	
	public List userListBySearch(WbColumn column, WbUser weibo, int days, Integer offset, Integer pagesize){
		String date = C.getDate(days);
		
		String hql = "select r.columnid,r.wbid,u.nickname,r.province,r.city,u.fans,u.feed,r.mid,count(r.mid) as total from robot.wb_result r, robot.wb_user u where r.columnid="+column.getId()+" and r.ptime>='"+date+"' ";
		String part = this.genSql(column, weibo);
		
		if(part!=null){
			hql += part;
		}
		
		hql += " and r.wbid=u.wbid group by r.wbid ";

		if(column.getSearchword()==null){
			column.setSearchword("ptime");
		}

		if(column.getSearchword().equals("ctime")){
			hql += "order by max(r.ctime) desc";
		}else if(column.getSearchword().equals("total")){
			hql += "order by total desc";
		}else if(column.getSearchword().equals("ptime")){
			hql += "order by max(r.ptime) desc";
		}else if(column.getSearchword().equals("fans")){
			hql += "order by r.fans desc";
		}else if(column.getSearchword().equals("feed")){
			hql += "order by r.feed desc";
		}
		return (List<WbResult>)this.findPageBySql(hql, offset, pagesize);
	}
	
	//列出当前任务下的用户数
	public Integer userCountBySearch(WbColumn column, WbUser weibo, int days){
		String date = C.getDate(days);
		String hql = "select count(distinct r.wbid) from WbResult r where r.columnid="+column.getId()+" and r.ptime>='"+date+"'";
		String part = this.genSql(column, weibo);
		
		if(part!=null){
			hql += part;
		}
		Integer count = this.getCountByHql(hql);
		return count;
	}
	
	private String genSql(WbColumn column, WbUser weibo){
		String result = " ";
		if(weibo==null){
			return null;
		}
		
		if(weibo.getProvince()!=null&&weibo.getProvince()>0){
			result += "and r.province="+weibo.getProvince()+" ";
		}
		if(weibo.getCity()!=null&&weibo.getCity()>0){
			result += "and r.city="+weibo.getCity()+" ";
		}		
		if(weibo.getVtype()!=null&&weibo.getVtype().length()>0&&!weibo.getVtype().equals("A")){
			if(weibo.getVtype().equals("V")){
				result += "and (r.vtype='P' or r.vtype='C') ";
			}else{
				result += "and r.vtype='"+weibo.getVtype()+"' ";
			}
		}
		if(weibo.getGender()!=null&&weibo.getGender().length()>0&&!weibo.getGender().equals("A")){
			result += "and r.gender='"+weibo.getGender()+"' ";
		}
		if(weibo.getLevel()!=null&&weibo.getLevel()>0&&!weibo.getLevel().equals("0")){
			result += "and r.level>="+weibo.getLevel()+" ";
		}
		
		return result;
	}	
	

	

}
