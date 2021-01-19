package com.isd.dao.bbs;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.bbs.LtResult;


public interface LtResultDAO extends GenericDAO<LtResult, Integer>{
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days);	
	//根据用户ID，内容类型以及时间范围返回论坛量
	public int mediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days);
	//根据栏目显示条数
	public List columnreport(Integer networkid, Integer type, Integer relation, int days);	
	//根据自有标签以及竞品标签显示排名靠前的媒体，以自有标签的媒体排名为准
	public List sitereport(Integer networkid, Integer columnid, int days, int count);
	//显示数据存量
	public List stockreport(Integer networkid, Integer type, Integer relation, int days);	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days);
	//根据用户、时间以及自有关系，按天统计媒体和文章的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days);
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer uid, Integer type, String from, String to);	
	//根据用户ID，内容类型以及时间范围返回论坛列表
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize);	
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize);
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize);	
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid);	
	
	
	
	//根据用户ID，汇总当天的内容总数，用于首页
	public List columnContentCountByUserTypeToday(Integer networkid, Integer type);

	//根据栏目返回内容
	public List contentListByColumn(Integer columnid, int offset, int pagesize);
	//根据用户，返回符合关键词的内容
	public List contentListByUserTypeDate(Integer uid, Integer type, int offset, int length);	



	//根据用户，返回公众号
	public List openidListByUserType(Integer uid, Integer type, int offset, int length);

	public List openidListByUserTypeRelation(Integer uid, Integer type, Integer relation, int offset, int length);
	
	
	//返回当前栏目下的用户数量
	public int contentCountByColumn(Integer columnid);	
	//今天的更新数量
	public int contentCountByColumnToday(Integer columnid);	

	//今天微信帐号的更新数量
	public int openidCountByUserTypeToday(Integer networkid, Integer type);	
	public int openidCountByUserType(Integer networkid, Integer type);

	public int openidCountByUserTypeRelation(Integer uid, Integer type, Integer relation);	

	//判断文章是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Long contentid);
	//判断公众号是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Integer wxid);	
	

	
	
	
	//根据搜索条件返回内容
	public List listByFilter(Integer uid, LtResult result, String from, String to, int offset, int pagesize);
	//根据搜索条件显示文章数
	public int countByFilter(Integer uid, LtResult result, String from, String to);	

	//按天汇总
	public List daily(Integer days);
	//按月汇总
	public List monthly(Integer days);
}
