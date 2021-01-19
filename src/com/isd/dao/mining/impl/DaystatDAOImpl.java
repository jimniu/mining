package com.isd.dao.mining.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.DaystatDAO;
import com.isd.entity.mining.Daystat;


public class DaystatDAOImpl extends GenericDAOImpl<Daystat, Integer> implements DaystatDAO {
	
	public DaystatDAOImpl() {
		super(Daystat.class);
	}
	
	//按用户和模块显示按月的统计
	public List userMonthly(Integer networkid, String module, Integer type, String date){
		String hql = "select date, sum(mediatotal), sum(pagetotal) from Daystat where networkid=" + networkid + " and module='"+module+"'" + " and type=" + type + " and date>='"+date+"' and category=1 group by date order by date asc";
		return this.findByHql(hql);
	}

	//按栏目和模块显示按月的统计
	public List columnMonthly(Integer columnid, String module, String date){
		String hql = "select date, sum(mediatotal), sum(pagetotal) from Daystat where columnid=" + columnid + " and module='"+module+"'" + " and date>='"+date+"' and category=1 group by date order by date asc";
		return this.findByHql(hql);
	}
	
	//按用户和模块显示按日的统计
	public List userDaily(Integer networkid, String module, Integer type, String date){
		String hql = "select date, sum(mediatotal), sum(pagetotal) from Daystat where networkid=" + networkid + " and module='"+module+"'" + " and type=" + type + " and date>='"+date+"' and category=0 group by date order by date asc";
		return this.findByHql(hql);
	}

	//按栏目和模块显示按日的统计
	public List columnDaily(Integer columnid, String module, String date){
		String hql = "select date, sum(mediatotal), sum(pagetotal) from Daystat where columnid=" + columnid + " and module='"+module+"'" + " and date>='"+date+"' and category=0 group by date order by date asc";
		return this.findByHql(hql);
	}	
	
	//按照用户和模块统计总数
	public List userTotal(Integer networkid, String module, Integer type, String date){
		String hql = "select sum(mediatotal), sum(pagetotal) from Daystat where networkid=" + networkid + " and module='"+module+"'" + " and type=" + type + " and date>='"+date+"' and category=0";
		return this.findByHql(hql);
	}
	
	//按栏目和模块统计总数
	public List columnTotal(Integer columnid, String module, String date){
		String hql = "select sum(mediatotal), sum(pagetotal) from Daystat where columnid=" + columnid + " and module='"+module+"'" + " and date>='"+date+"' and category=0";
		return this.findByHql(hql);		
	}
	
	//返回记录
	public List<Daystat> findByDate(Integer columnid, String module, Integer category, String date){
		String hql = "from Daystat where columnid=" + columnid + " and module='"+module+"'" + " and category="+category+" and date='"+date+"'";
		return this.findByHql(hql);
	}
	
}
