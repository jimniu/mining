package com.isd.service.weibo;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.weibo.WbColumn;
import com.isd.entity.weibo.WbResult;
import com.isd.entity.weibo.WbUser;

public interface WbResultService {
	//根据用户ID，内容类型以及时间范围返回数据量
	public int contentCountByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days);
	//根据用户ID，内容类型以及时间范围返回数据量
	public int mediaCountByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days);	
	//按照哈希方式，列出栏目下的内容总数
	public HashMap<Integer, Integer> contentCountByUserTypeDateHash(Integer userid, Integer type, int day);
	public Integer contentCountByColumnDate(Integer columnid, int days);	
	public Integer mediaCountByColumnDate(Integer columnid, int days);
	//根据栏目显示条数
	public List columnreport(Integer userid, int type, int days);	
	//按照用户ID、内容类型以及时间范围，显示每天的总数
	public List dailyContentCountByUserTypeRelationDate(Integer userid, Integer type, Integer relation, int days);
	//根据columnid、时间范围，显示每一天的总数
	public List dailyContentCountByColumnDate(Integer columnid, int days);
	//列出给定栏目每天的评论数和转发数
	public List dailyCommentForwardSumByColumnDate(Integer columnid, int days);
	//列出给定栏目每天的意见领袖数
	public HashMap<String, Integer> dailyVipByColumnDate(Integer columnid, int days);
	//列出给定栏目按照粉丝数量统计的转发数和评论数
	public List commentForwardSumByColumnFansrange(Integer columnid, int days);
	//列出给定栏目在指定时间范围内按照设备的统计数量
	public List contentCountByColumnDevice(Integer columnid, int days);
	//根据用户、时间以及自有关系，按天统计微博和博文的数量。用在集成报表的总览
	public List mediaContentCountByUserTypeRelation(Integer userid, Integer type, Integer relation, Integer days);	
	//根据用户ID、内容类型以及抓取时间范围提取数据
	public List contentListByUserTypeTimeRange(Integer userid, Integer type, String from, String to);	
	//根据用户、内容类型以及时间范围统计微博信息
	public List mediaSumByNetworkTypeRelationDate(Integer userid, Integer type, Integer relation, int days);
	//根据用户、内容类型以及时间范围统计博文信息
	public List contentSumByNetworkTypeRelationDate(Integer userid, Integer type, Integer relation, int days);	
	//根据用户、类型以及时间，返回微博用户
	public List mediaListByNetworkTypeRelationDate(Integer userid, Integer type, Integer relation, int days,  int offset, int pagesize);
	//根据用户ID、内容类型、关系、时间范围返回内容
	public List contentListByNetworkTypeRelationDate(Integer userid, Integer type, Integer relation, int days, int offset, int pagesize);
	//根据用户ID、内容类型、关系、时间范围返回最热内容
	public List topContentListByUserTypeRelationDate(Integer userid, Integer type, Integer relation, String from, String to, int offset, int pagesize);
	//根据栏目和排序，返回微博内容
	public List contentListByColumnDateOrder(Integer userid, Integer type, Integer columnid, int days, String keyword, String order, Integer offset, Integer pagesize);
	//根据栏目ID以及时间范围，按照省份列出微博用户数
	public List provinceMediaCountByColumnDate(Integer columnid, int days);	
	//根据栏目ID以及时间范围，按照省份列出微博文章数
	public List provinceContentCountByColumnDate(Integer columnid, int days);	
	



	//列出符合查询条件的微博
	public List<WbResult> contentListByColumnWbid(Integer taskid, Long wbid, Integer offset, Integer pagesize);


	

	
	//列出当前任务下所有符合匹配条件的结果
	public List<WbResult> userListBySearch(WbColumn column, WbUser weibo, int days, Integer offset, Integer pagesize);
	//列出当前任务下匹配到的用户数
	public Integer userCountBySearch(WbColumn column, WbUser weibo, int days);

	
	//按照微博用户统计符合栏目关键词的微博数量
	public int feedCountByColumnWbid(Integer taskid, Long wbid);
	
	//删除微博内容
	public void feeddelete(Long id);
	
	//根据传进来的一组数据删除结果
	public void deleteByColumnAndWbids(Integer taskid, String group);
	
	
	
}
