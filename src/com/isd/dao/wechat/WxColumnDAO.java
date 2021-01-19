package com.isd.dao.wechat;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.wechat.WxColumn;


public interface WxColumnDAO extends GenericDAO<WxColumn, Integer>{
	public String findKeywordsByUser(Integer userid);
	public void resetColumnContentCountByUserTypeToday(Integer userid, Integer type); 
	//根据用户ID搜索微信关键词设置
	public List<WxColumn> listByNetworkType(Integer userid, Integer type);
	public List<WxColumn> listByNetworkTypeRelation(Integer networkid, Integer type, Integer relation);
	//根据搜索时间，提取搜索时间最早的设置
	public WxColumn findOneByFetchTime();
	//根据上次搜索时间，提取最早搜索的设置
	public WxColumn findOneByStime();
	//启用/停用任务
	public void changeStatus(Integer userid, Integer status);
}
