package com.isd.service.bbs.impl;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.bbs.LtColumnDAO;
import com.isd.dao.bbs.LtResultDAO;
import com.isd.entity.bbs.LtColumn;
import com.isd.entity.bbs.LtResult;
import com.isd.service.bbs.LtResultService;

public class LtResultServiceImpl implements LtResultService{
	@Autowired
	private LtResultDAO resultDAO;
	@Autowired
	private LtColumnDAO columnDAO;
	
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
	//根据用户ID，内容类型以及时间范围返回论坛量
	public int mediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.mediaCountByNetworkTypeRelationDate(networkid, type, relation, days);
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
			List<LtResult> result = new ArrayList<LtResult>();
			for(int i=0; i<list.size(); i++){
				LtResult item = (LtResult)list.get(i);
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
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int length){
		return resultDAO.contentListByNetworkTypeRelationDate(networkid, type, relation, days, offset, length);
	}
	//根据用户ID，内容类型以及时间范围返回论坛列表
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int length){
		return resultDAO.mediaListByNetworkTypeRelationDate(networkid, type, relation, days, offset, length);
	}
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		return resultDAO.contentListByNetworkTypeRelationDate(networkid, type, relation, days, offset, pagesize);		
	}
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		return resultDAO.topContentListByUserTypeRelationDate(networkid, type, relation, from, to, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid){
		return resultDAO.sameTitleListByUserTypeRelationDate(networkid, type, relation, from, to, contentid);
	}
	
	
	
	
	
	
	//根据用户ID，汇总当天的内容总数，用于首页
	public void columnContentCountByUserTypeToday(Integer networkid, Integer type){
		columnDAO.resetColumnContentCountByNetworkTypeToday(networkid, type);
		List list = resultDAO.columnContentCountByUserTypeToday(networkid, type);
		for(int i=0; i<list.size(); i++){
			Object[] array = (Object[])list.get(i);
			Integer columnid = (Integer)(array[0]);
			Integer today = ((BigInteger)(array[1])).intValue();
			LtColumn column = columnDAO.findById(columnid);
			column.setToday(today);
			columnDAO.update(column);
		}
	}
	

	//根据栏目返回内容
	public List contentListByColumn(Integer columnid, int offset, int pagesize){
		return resultDAO.contentListByColumn(columnid, offset, pagesize);
	}
	//根据用户，返回符合关键词的内容
	public List contentListByUserType(Integer networkid, Integer type, int offset, int length){
		return resultDAO.contentListByUserTypeDate(networkid, type, offset, length);
	}


	//根据用户，返回公众号
	public List openidListByUserType(Integer networkid, Integer type, int offset, int length){
		return resultDAO.openidListByUserType(networkid, type, offset, length);
	}


	public List openidListByUserTypeRelation(Integer networkid, Integer type, Integer relation, int offset, int length){
		return resultDAO.openidListByUserTypeRelation(networkid, type, relation, offset, length);
	}
	
	//根据用户ID，返回文章数量
	public int contentCountByColumn(Integer columnid){
		return resultDAO.contentCountByColumn(columnid);
	}
	
	
	//今天微信帐号的更新数量
	public int openidCountByUserTypeToday(Integer networkid, Integer type){
		return resultDAO.openidCountByUserTypeToday(networkid, type);
	}	
	public int openidCountByUserType(Integer networkid, Integer type){
		return resultDAO.openidCountByUserType(networkid, type);
	}	
	
	public int openidCountByUserTypeRelation(Integer networkid, Integer type, Integer relation){
		return resultDAO.openidCountByUserTypeRelation(networkid, type, relation);
	}
	
	

	
	
	//根据搜索条件返回内容
	public List listByFilter(Integer networkid, LtResult result, String from, String to, int offset, int pagesize){
		return resultDAO.listByFilter(networkid, result, from, to, offset, pagesize);
	}
	//根据搜索条件显示文章数
	public int countByFilter(Integer networkid, LtResult result, String from, String to){
		return resultDAO.countByFilter(networkid, result, from, to);
	}
	
	//删除当前记录
	public boolean delete(Integer networkid, Integer id){
		LtResult result = resultDAO.findById(id);
		if(result.getNetworkid().equals(networkid)){
			resultDAO.delete(result);
			return true;
		}else{
			return false;
		}
	}		
}
