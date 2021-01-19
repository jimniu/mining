package com.isd.service.mining;

import java.util.List;


public interface DaystatService {

	//按用户和模块显示按月的统计
	public List userMonthly(Integer userid, String module, Integer type, int days);

	//按栏目和模块显示按月的统计
	public List columnMonthly(Integer columnid, String module, int days);
	
	//按用户和模块显示按日的统计
	public List userDaily(Integer userid, String module, Integer type, int days);

	//按栏目和模块显示按日的统计
	public List columnDaily(Integer columnid, String module, int days);
	
	//按照用户和模块统计总数
	public List userTotal(Integer userid, String module, Integer type, int days);
	
	//按栏目和模块统计总数
	public List columnTotal(Integer columnid, String module, int days);
	

	//统计汇总
	public void summarize(Integer days);	
}
