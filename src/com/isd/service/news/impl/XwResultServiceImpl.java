package com.isd.service.news.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.news.XwColumnDAO;
import com.isd.dao.news.XwResultDAO;
import com.isd.entity.news.XwResult;
import com.isd.service.news.XwResultService;

public class XwResultServiceImpl implements XwResultService{
	@Autowired
	private XwResultDAO resultDAO;
	@Autowired
	private XwColumnDAO columnDAO;
	
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.contentCountByNetworkTypeRelationDate(networkid, type, relation, days);
	}
	//按照哈希方式，列出栏目下的内容总数
	public HashMap<Integer, Integer> contentCountByNetworkTypeRelationDateHash(Integer networkid, Integer type, Integer relation, int days){
		HashMap<Integer, Integer>hashmap = new HashMap<Integer, Integer>();
		List list = resultDAO.columnreport(networkid, type, relation, days);
		
		for(int i=0; i<list.size(); i++){
			Object[] array = (Object[])list.get(i);
			Integer columnid = (Integer)array[0];
			Integer count	 = (Integer)array[2];
			hashmap.put(columnid, count);
		}
		return hashmap;
	}
	//按照用户、内容类型、时间、转发或原发返回媒体数量
	public List originCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, Integer days){
		return resultDAO.originCountByUserTypeRelationDate(networkid, type, relation, days);
	}
	//根据用户、内容类型、时间得到媒体数量
	public int mediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.mediaCountByNetworkTypeDate(networkid, type, relation, days);
	}		
	//根据栏目显示条数
	public List columnreport(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.columnreport(networkid, type, relation, days);
	}	
	//根据自有标签以及竞品标签显示排名靠前的媒体，以自有标签的媒体排名为准
	public List sitereport(Integer networkid, Integer columnid, int days, int count){
		return resultDAO.sitereport(networkid, columnid, days, count);
	}
	//显示数据存量
	public List stockreport(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.stockreport(networkid, type, relation, days);
	}	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.dailyContentCountByUserTypeRelationDate(networkid, type, relation, days);
	}	
	//根据用户ID、内容类型以及时间范围，显示每个媒体分类的统计数
	public List categoryContentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.categoryContentCountByNetworkTypeRelationDate(networkid, type, relation, days);
	}
	//根据用户ID、内容类型、自有或竞品、转发还是原发以及时间范围，显示每个媒体分类的统计数
	public List categoryContentCountByUserTypeRelationOriginDate(Integer networkid, Integer type, Integer relation, Integer origin, int days){
		return resultDAO.categoryContentCountByUserTypeRelationOriginDate(networkid, type, relation, origin, days);
	}
	//根据用户ID、内容类型以及时间范围，显示每个媒体分类的媒体统计数
	public List categoryMediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.categoryMediaCountByNetworkTypeRelationDate(networkid, type, relation, days);
	}
	//根据用户ID、内容类型、自由或竞品以及时间范围，显示每个栏目的统计数
	public List columnContentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.columnContentCountByNetworkTypeRelationDate(networkid, type, relation, days);
	}	
	//根据用户、时间以及自有关系，按天统计媒体和文章的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days){
		return resultDAO.mediaContentCountByUserTypeRelation(networkid, type, relation, days);
	}	
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer networkid, Integer type, Integer dd, String from, String to){
		HashMap<Long, Integer> hash = new HashMap<Long, Integer>();
		
		List list = resultDAO.contentListByUserTypeTimeRange(networkid, type, from, to);
		
		if(dd == 1){
			//做去重处理
			List<XwResult> result = new ArrayList<XwResult>();
			for(int i=0; i<list.size(); i++){
				XwResult item = (XwResult)list.get(i);
				if(hash.get(item.getPageid())==null){
					result.add(item);
					hash.put(item.getPageid(), 1);
				}
			}
			return result;
		}else{
			return list;
		}
	}
	//根据用户ID，内容类型以及时间范围返回内容列表
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		return resultDAO.contentListByNetworkTypeRelationDate(networkid, type, relation, days, offset, pagesize);
	}
	//根据用户ID，内容类型以及时间范围返回媒体列表
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		return resultDAO.mediaListByNetworkTypeRelationDate(networkid, type, relation, days, offset, pagesize);
	}	
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByUserTypeRelationDate(Integer uid, Integer type, Integer relation, int days, int offset, int pagesize){
		return resultDAO.contentListByUserTypeRelationDate(uid, type, relation, days, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer columnid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		return resultDAO.topContentListByUserTypeRelationDate(networkid, columnid, type, relation, from, to, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid){
		return resultDAO.sameTitleListByUserTypeRelationDate(networkid, type, relation, from, to, contentid);
	}
	//栏目的文章数
	public int contentCountByColumnDate(Integer columnid, int days){
		return resultDAO.contentCountByColumnDate(columnid, days);
	}	
	//根据栏目搜索内容
	public List<XwResult> contentListByColumnDate(Integer uid, Integer columnid, int days, int offset, int pagesize){
		return resultDAO.contentListByColumnDate(uid, columnid, days, offset, pagesize);
	}
	//根据搜索条件返回内容
	public List listByFilter(Integer uid, XwResult result, String from, String to, int offset, int pagesize){		
		return resultDAO.listByFilter(uid, result, from, to, offset, pagesize);
	}	
	//根据搜索条件显示文章数
	public int countByFilter(Integer uid, XwResult result, String from, String to){
		return resultDAO.countByFilter(uid, result, from, to);
	}	
	public void delete(XwResult report){
		resultDAO.delete(report);
	}
	
	public XwResult findById(Integer id){
		return resultDAO.findById(id);
	}	
	//删除当前记录
	public boolean delete(Integer networkid, Integer id){
		XwResult result = resultDAO.findById(id);
		if(result.getNetworkid().equals(networkid)){
			resultDAO.delete(result);
			return true;
		}else{
			return false;
		}
	}
	//根据栏目ID以及时间范围，按照省份列出新闻文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days){
		return resultDAO.provinceContentCountByColumnDate(columnid, days);
	}
}
