package com.isd.service.wechat;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.wechat.WxResult;



public interface WxResultService {
	//根据用户ID，内容类型以及时间范围返回内容量
	public int contentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days);
	//按照哈希方式，列出栏目下的内容总数
	public HashMap<Integer, Integer> contentCountByUserTypeRelationDateHash(Integer networkid, Integer type, Integer relation, int days);
	public WxResult findById(Integer id);
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
	//根据用户、内容类型以及起止时间返回数据
	public List<WxResult> contentListByUserTypeTimeRange(Integer networkid, Integer type, Integer dd, String from, String to);
	//返回指定时间指定关系的微信数量
	public int mediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, Integer days);	
	//根据用户、内容类型、时间，返回微信列表
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation,int days, int offset, int length);	
	//根据用户ID，内容类型、排序以及时间范围返回内容
	public List<WxResult> contentListByNetworkTypeDateOrder(Integer networkid, Integer type, String order, int days, int offset, int pagesize);
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize);		
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer columnid, Integer type, Integer relation, String from, String to, int offset, int pagesize);
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topReadListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize);	
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid);
	//根据栏目ID，排序以及时间范围返回内容
	public List<WxResult> contentListByColumnDateOrder(Integer columnid, String order, int days, int offset, int pagesize);
	//根据栏目ID、时间范围返回数据量
	public int contentCountByColumnDate(Integer columnid, int days);	
	//根据公众号返回内容列表
	public List<WxResult> contentListByMedia(Integer wxid, int offset, int pagesize);	
	//根据栏目ID以及时间范围，按照省份列出微博文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days);	
	
	


	

	




	
	
	
	//判断文章是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Long contentid);
	//判断公众号是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Integer wxid);	
	
	


	
	//根据搜索条件返回内容
	public List listByFilter(Integer networkid, WxResult result, String from, String to, int offset, int pagesize);
	//根据搜索条件显示文章数
	public int countByFilter(Integer networkid, WxResult result, String from, String to);	
	//删除当前记录
	public boolean delete(Integer networkid, Integer id);

}

