package com.isd.dao.weibo;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.weibo.WbColumn;



public interface WbColumnDAO extends GenericDAO<WbColumn, Integer>{
	//列出用户的任务
	public List<WbColumn> listByUser(Integer userid, Integer type);
	public String findKeywordsByUser(Integer userid);
	//根据迫切程度返回任务
	public WbColumn findByFetchTime();
	public WbColumn findByLastid();
	//启用/停用任务
	public void changeStatus(Integer userid, Integer status);
	
	public void resetColumnContentCountByUserTypeToday(Integer userid, Integer type); 
}
