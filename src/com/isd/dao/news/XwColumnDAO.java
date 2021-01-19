package com.isd.dao.news;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.news.XwColumn;


public interface XwColumnDAO extends GenericDAO<XwColumn, Integer>{
	public String findKeywordsByUser(Integer userid);
	//将当日的内容统计清零
	public void resetColumnContentCountByUserTypeToday(Integer userid, Integer type);
	public XwColumn findOneByFetchTime();
	public XwColumn findOneBySearchTime();
	public List<XwColumn> listByUser(Integer uid, Integer type);
	public List<XwColumn> listByNetworkTrackRelation(Integer uid, Integer type, Integer relation);
	//启用/停用任务
	public void changeStatus(Integer userid, Integer status);
}
