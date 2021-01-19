package com.isd.dao.mining;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.Daystat;

public interface DaystatDAO extends GenericDAO<Daystat, Integer>{
	//按用户和模块显示按月的统计
	public List userMonthly(Integer userid, String module, Integer type, String date);

	//按栏目和模块显示按月的统计
	public List columnMonthly(Integer columnid, String module, String date);
	
	//按用户和模块显示按日的统计
	public List userDaily(Integer userid, String module, Integer type, String date);

	//按栏目和模块显示按日的统计
	public List columnDaily(Integer columnid, String module, String date);
	
	//按照用户和模块统计总数
	public List userTotal(Integer userid, String module, Integer type, String date);
	
	//按栏目和模块统计总数
	public List columnTotal(Integer columnid, String module, String date);	
	
	//返回记录
	public List<Daystat> findByDate(Integer columnid, String module, Integer category, String date);
}
