package com.isd.dao.weibo;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.weibo.WbColumn;
import com.isd.entity.weibo.WbResult;
import com.isd.entity.weibo.WbUser;



public interface WbResultDAO extends GenericDAO<WbResult, Long>{
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days);
	//根据用户ID，内容类型以及时间范围返回数据量
	public int mediaCountByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days);	
	//根据栏目和时间，返回微博总数
	public Integer contentCountByColumnDate(Integer columnid, int days);
	//判断当天是否有搜索结果
	public Integer contentCountByDate(int days);
	public Integer mediaCountByColumnDate(Integer columnid, int days);	
	//按照微博用户统计符合栏目关键词的微博数量
	public int contentCountByColumnWbid(Integer taskid, Long wbid);	
	//根据用户、类型以及时间，返回微博内容
	public List contentListByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days, int offset, int pagesize);	
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer userid, Integer type, String from, String to);	
	//根据用户、类型以及时间，返回微博用户
	public List mediaListByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days,  int offset, int pagesize);
	//根据栏目和排序，返回微博内容
	public List contentListByColumnDateOrder(Integer userid, Integer type, Integer columnid, int days, String keyword,  String order, Integer offset, Integer pagesize);	
	//根据任务和微博id，返回微博内容
	public List<WbResult> contentListByColumnWbid(Integer taskid, Long wbid, Integer offset, Integer pagesize);
	//根据栏目ID以及时间范围，按照省份列出微博用户数
	public List provinceMediaCountByColumnDate(Integer columnid, int days);
	//根据栏目ID以及时间范围，按照省份列出微博文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days);
	
	//根据栏目显示条数
	public List columnreport(Integer userid, int type, int days);	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days);	
	//根据columnid、时间范围，显示每一天的总数
	public List dailyContentCountByColumnDate(Integer columnid, int days);
	//列出给定栏目每天的评论数和转发数
	public List dailyCommentForwardSumByColumnDate(Integer columnid, int days);
	//列出给定栏目每天的意见领袖数
	public List dailyVipByColumnDate(Integer columnid, int days);
	//列出给定栏目按照粉丝数量统计的转发数和评论数
	public List commentForwardSumByColumnFansrange(Integer columnid, int days);
	//列出给定栏目在指定时间范围内按照设备的统计数量
	public List contentCountByColumnDevice(Integer columnid, int days);
	//根据用户、时间以及自有关系，按天统计微博和博文的数量。用在集成报表的总览
	public List dailyMediaContentCountByUserTypeRelation(Integer userid, Integer type, Integer relation, Integer days);
	
	
	//根据用户、内容类型以及时间范围统计微博信息
	public List mediaSumByNetworkTypeRelationDate(Integer userid, Integer type, Integer relation, int date);	
	//根据用户、内容类型以及时间范围统计博文信息
	public List contentSumByNetworkTypeRelationDate(Integer userid, Integer type, Integer relation, int date);	
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer userid, Integer type, Integer relation, String from, String to, int offset, int pagesize);	
	
	
	
	
	
	
	//判断当前任务下是否存在该微博用户
	public List<WbResult> findByColumnAndWbid(Integer taskid, Long wbid);
	//判断当前任务下是否存在该微博
	public List<WbResult> findByColumnAndMid(Integer taskid, Long feedid);






	

	//按照任务ID，得到总的粉丝的数量
	public Long fansCountByColumn(Integer taskid);
	//按照任务ID，得到总的微博的数量
	public Long feedCountByColumn(Integer taskid);
	//按照任务ID，得到包含关键词的文章数量
	public Integer hitCountByColumn(Integer taskid);
	

	public List userListBySearch(WbColumn column, WbUser weibo, int days, Integer offset, Integer pagesize);
	//列出当前任务下的用户数
	public Integer userCountBySearch(WbColumn column, WbUser weibo, int days);

	

	
	//按天汇总微博的数量
	public List daily(Integer days);	
	//按月汇总微博的数量
	public List monthly(Integer days);
	//根据省份显示用户的数量
	public List provincial(int days);
	

	//根据任务ID删除结果
	public void clearByColumn(Integer taskid);
	//根据任务ID以及wbid删除结果
	public void clearByColumnAndWbid(Integer taskid, String wbid);
	
		
}
