package com.isd.service.mining;

import java.util.List;


public interface GeostatService {

	//按用户统计
	public List findByUser(Integer userid, Integer type);

	//按栏目统计
	public List findByColumn(Integer columnid, Integer type);

	//统计汇总
	public void summarize(int days);	
}
