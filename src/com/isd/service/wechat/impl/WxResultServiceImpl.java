package com.isd.service.wechat.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.wechat.WxColumnDAO;
import com.isd.dao.wechat.WxResultDAO;
import com.isd.entity.wechat.WxResult;
import com.isd.service.wechat.WxResultService;

public class WxResultServiceImpl implements WxResultService{
	@Autowired
	private WxResultDAO resultDAO;
	@Autowired
	private WxColumnDAO columnDAO;
	
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.contentCountByNetworkTypeRelationDate(networkid, type, relation, days);
	}
	//按照哈希方式，列出栏目下的内容总数
	public HashMap<Integer, Integer> contentCountByUserTypeRelationDateHash(Integer networkid, Integer type, Integer relation, int days){
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
	//根据栏目显示条数
	public List columnreport(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.columnreport(networkid, type, relation, days);
	}

	public WxResult findById(Integer id){
		return resultDAO.findById(id);
	}

	//根据自有标签以及竞品标签显示排名靠前的媒体，以自有标签的媒体排名为准
	public List sitereport(Integer networkid, Integer columnid, int days, int count){
		return resultDAO.sitereport(networkid, columnid, days, count);
	}
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.dailyContentCountByUserTypeRelationDate(networkid, type, relation, days);
	}	
	//根据用户、时间以及自有关系，按天统计媒体和文章的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days){
		return resultDAO.mediaContentCountByUserTypeRelation(networkid, type, relation, days);
	}
	//根据用户、内容类型以及起止时间返回数据
	public List<WxResult> contentListByUserTypeTimeRange(Integer networkid, Integer type, Integer dd, String from, String to){
		HashMap<Long, Integer> hash = new HashMap<Long, Integer>();
		
		List list = resultDAO.contentListByUserTypeTimeRange(networkid, type, from, to);
		
		if(dd == 1){
			//做去重处理
			List<WxResult> result = new ArrayList<WxResult>();
			for(int i=0; i<list.size(); i++){
				WxResult item = (WxResult)list.get(i);
				if(hash.get(item.getContentid())==null){
					result.add(item);
					hash.put(item.getContentid(), 1);
				}
			}
			return result;
		}else{
			return list;
		}

	}		
	//返回指定时间指定关系的微信数量
	public int mediaCountByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, Integer days){
		return resultDAO.mediaCountByNetworkTypeRelationDate(networkid, type, relation, days);
	}
	//根据用户、内容类型、时间，返回微信列表
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int length){
		return resultDAO.mediaListByNetworkTypeRelationDate(networkid, type, relation, days, offset, length);
	}	
	//根据用户ID，内容类型、排序以及时间范围返回内容
	public List<WxResult> contentListByNetworkTypeDateOrder(Integer networkid, Integer type, String order, int days, int offset, int pagesize){
		return resultDAO.contentListByNetworkTypeDateOrder(networkid, type, order, days, offset, pagesize);
	}		
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		return resultDAO.contentListByNetworkTypeRelationDate(networkid, type, relation, days, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid,Integer columnid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		return resultDAO.topContentListByUserTypeRelationDate(networkid, columnid, type, relation, from, to, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topReadListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		return resultDAO.topReadListByUserTypeRelationDate(networkid, type, relation, from, to, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围，返回与跟定记录相同的内容
	public List sameTitleListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, Integer contentid){
		return resultDAO.sameTitleListByUserTypeRelationDate(networkid, type, relation, from, to, contentid);
	}
	//根据栏目ID，排序以及时间范围返回内容
	public List<WxResult> contentListByColumnDateOrder(Integer columnid, String order, int days, int offset, int pagesize){
		return resultDAO.contentListByColumnDateOrder(columnid, order, days, offset, pagesize);
	}
	//根据栏目ID、时间范围返回数据量
	public int contentCountByColumnDate(Integer columnid, int days){
		return resultDAO.contentCountByColumnDate(columnid, days);
	}	
	//根据公众号返回内容列表
	public List<WxResult> contentListByMedia(Integer wxid, int offset, int pagesize){
		return resultDAO.contentListByMedia(wxid, offset, pagesize);
	}	
	//根据栏目ID以及时间范围，按照省份列出微博文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days){
		return resultDAO.provinceContentCountByColumnDate(columnid, days);
	}

	

	
	

	


	//显示数据存量
	public List stockreport(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.stockreport(networkid, type, relation, days);
	}
	//根据搜索条件返回内容
	public List listByFilter(Integer uid, WxResult result, String from, String to, int offset, int pagesize){
		return resultDAO.listByFilter(uid, result, from, to, offset, pagesize);
	}
	//根据搜索条件显示文章数
	public int countByFilter(Integer uid, WxResult result, String from, String to){
		return resultDAO.countByFilter(uid, result, from, to);
	}

	//判断文章是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Long contentid){
		return resultDAO.exists(columnid, contentid);
	}
	//判断公众号是否已经被存入到搜索结果表中
	public boolean exists(Integer columnid, Integer wxid){
		return resultDAO.exists(columnid, wxid);
	}
	
	//删除当前记录
	public boolean delete(Integer networkid, Integer id){
		WxResult result = resultDAO.findById(id);
		if(result.getNetworkid().equals(networkid)){
			resultDAO.delete(result);
			return true;
		}else{
			return false;
		}
	}
}
