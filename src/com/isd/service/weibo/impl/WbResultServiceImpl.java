package com.isd.service.weibo.impl;

import java.util.HashMap;
import java.util.List;

import org.apache.activemq.broker.region.virtual.FilteredDestination;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.weibo.WbColumnDAO;
import com.isd.dao.weibo.WbResultDAO;
import com.isd.entity.weibo.WbColumn;
import com.isd.entity.weibo.WbResult;
import com.isd.entity.weibo.WbUser;
import com.isd.service.weibo.WbResultService;


public class WbResultServiceImpl implements WbResultService{
	@Autowired
	private WbResultDAO resultDAO;
	@Autowired
	private WbColumnDAO columnDAO;
	
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.contentCountByUserTypeRelationDate(networkid, type, relation, days);
	}
	//根据用户ID，内容类型以及时间范围返回数据量
	public int mediaCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.mediaCountByUserTypeRelationDate(networkid, type, relation, days);
	}
	//按照哈希方式，列出栏目下的内容总数
	public HashMap<Integer, Integer> contentCountByUserTypeDateHash(Integer networkid, Integer type, int days){
		HashMap<Integer, Integer>hashmap = new HashMap<Integer, Integer>();
		List list = resultDAO.columnreport(networkid, type, days);
		
		for(int i=0; i<list.size(); i++){
			Object[] array = (Object[])list.get(i);
			Integer columnid = (Integer)array[0];
			Integer count	 = ((Long)array[2]).intValue();
			hashmap.put(columnid, count);
		}
		return hashmap;
	}
	
	//根据栏目显示条数
	public List columnreport(Integer networkid, int type, int days){
		return resultDAO.columnreport(networkid, type, days);
	}	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.dailyContentCountByUserTypeRelationDate(networkid, type, relation, days);
	}	
	//根据columnid、时间范围，显示每一天的总数
	public List dailyContentCountByColumnDate(Integer columnid, int days){
		return resultDAO.dailyContentCountByColumnDate(columnid, days);
	}

	//列出给定栏目每天的评论数和转发数
	public List dailyCommentForwardSumByColumnDate(Integer columnid, int days){
		return resultDAO.dailyCommentForwardSumByColumnDate(columnid, days);
	}

	//列出给定栏目每天的意见领袖数
	public HashMap<String, Integer> dailyVipByColumnDate(Integer columnid, int days){
		HashMap<String, Integer> hash = new HashMap<String, Integer>();
		List list = resultDAO.dailyVipByColumnDate(columnid, days);
		for(int i=0; i<list.size(); i++){
			Object[] item = (Object[])list.get(i);
			String date = (String)item[0];
			Integer count = Integer.parseInt(item[1].toString());
			hash.put(date, count);
		}
		return hash;
	}
	//列出给定栏目按照粉丝数量统计的转发数和评论数
	public List commentForwardSumByColumnFansrange(Integer columnid, int days){
		return resultDAO.commentForwardSumByColumnFansrange(columnid, days);
	}

	//列出给定栏目在指定时间范围内按照设备的统计数量
	public List contentCountByColumnDevice(Integer columnid, int days){
		return resultDAO.contentCountByColumnDevice(columnid, days);
	}

	//根据用户、时间以及自有关系，按天统计微博和博文的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer networkid, Integer type, Integer relation, Integer days){
		return resultDAO.dailyMediaContentCountByUserTypeRelation(networkid, type, relation, days);
	}
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer networkid, Integer type, String from, String to){
		return resultDAO.contentListByUserTypeTimeRange(networkid, type, from, to);
	}	
	//根据用户、内容类型以及时间范围统计微博信息
	public List mediaSumByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.mediaSumByNetworkTypeRelationDate(networkid, type, relation, days);
	}	
	//根据用户、内容类型以及时间范围统计博文信息
	public List contentSumByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days){
		return resultDAO.contentSumByNetworkTypeRelationDate(networkid, type, relation, days);
	}	
	//根据用户、类型以及时间，返回微博用户
	public List mediaListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days,  int offset, int pagesize){
		return resultDAO.mediaListByUserTypeRelationDate(networkid, type, relation, days, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByNetworkTypeRelationDate(Integer networkid, Integer type, Integer relation, int days, int offset, int pagesize){
		return resultDAO.contentListByUserTypeRelationDate(networkid, type, relation, days, offset, pagesize);
	}
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer networkid, Integer type, Integer relation, String from, String to, int offset, int pagesize){
		return resultDAO.topContentListByUserTypeRelationDate(networkid, type, relation, from, to, offset, pagesize);
	}
	public List contentListByColumnDateOrder(Integer networkid, Integer type, Integer columnid, int days, String keyword, String order, Integer offset, Integer pagesize){
		return resultDAO.contentListByColumnDateOrder(networkid, type, columnid, days, keyword, order, offset, pagesize);
	}	
	public Integer contentCountByColumnDate(Integer columnid, int days){
		return resultDAO.contentCountByColumnDate(columnid, days);
	}	
	public Integer mediaCountByColumnDate(Integer columnid, int days){
		return resultDAO.mediaCountByColumnDate(columnid, days);
	}
	//根据栏目ID以及时间范围，按照省份列出微博用户数
	public List provinceMediaCountByColumnDate(Integer columnid, int days){
		return resultDAO.provinceMediaCountByColumnDate(columnid, days);
	}
	//根据栏目ID以及时间范围，按照省份列出微博文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days){
		return resultDAO.provinceContentCountByColumnDate(columnid, days);
	}

	
	//列出符合查询条件的微博
	public List<WbResult> contentListByColumnWbid(Integer taskid, Long wbid,Integer offset, Integer pagesize){
		List<WbResult> list = resultDAO.contentListByColumnWbid(taskid, wbid, offset, pagesize);
		return list;
	}	

	
	//列出当前任务下所有符合匹配条件的结果
	public List userListBySearch(WbColumn column, WbUser user, int days, Integer offset, Integer pagesize){
		return resultDAO.userListBySearch(column, user, days, offset, pagesize);
	}
	//列出当前任务下匹配到的用户数
	public Integer userCountBySearch(WbColumn column,  WbUser user, int days){
		return resultDAO.userCountBySearch(column, user, days);
	}
	

	//按照微博用户统计符合栏目关键词的微博数量
	public int feedCountByColumnWbid(Integer taskid, Long wbid){
		return resultDAO.contentCountByColumnWbid(taskid, wbid);
	}
	
	//删除微博内容
	public void feeddelete(Long id){
		resultDAO.delete(id);
	}	
	
	//根据传进来的一组数据删除结果
	public void deleteByColumnAndWbids(Integer taskid, String group){
		String[]array = group.split(",");
		for(int i=0; i< array.length; i++){
			String wbid = array[i];
			resultDAO.clearByColumnAndWbid(taskid, wbid);
		}
	}
	

}
