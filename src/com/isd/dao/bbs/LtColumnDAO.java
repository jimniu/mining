package com.isd.dao.bbs;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.bbs.LtColumn;


public interface LtColumnDAO extends GenericDAO<LtColumn, Integer>{
	public String findKeywordsByNetwork(Integer userid);
	//将当日的内容统计清零
	public void resetColumnContentCountByNetworkTypeToday(Integer userid, Integer type);
	//根据用户ID以及类型搜索微信关键词设置
	public List<LtColumn> listByNetwork(Integer userid, int type);
	public List<LtColumn> listByNetworkTypeRelation(Integer userid, Integer type, Integer relation);
	//根据搜索时间，提取搜索时间最早的设置
	public LtColumn findOneByFetchTime();
	//根据上次搜索时间，提取最早搜索的设置
	public LtColumn findOneByStime();
	//启用/停用任务
	public void changeStatus(Integer userid, Integer status);
}
