package com.isd.dao.weibo.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.weibo.WbColumnDAO;
import com.isd.entity.weibo.WbColumn;


public class WbColumnDAOImpl extends GenericDAOImpl<WbColumn, Integer> implements WbColumnDAO {
	public WbColumnDAOImpl() {
		super(WbColumn.class);
	}
	
	//将当日的内容统计清零
	public void resetColumnContentCountByUserTypeToday(Integer networkid, Integer type){
		String hql = "update WbColumn set feedtoday=0 where networkid="+networkid+" and type="+type;
		this.executeByHql(hql);
	}
	
	//列出用户的任务
	public List<WbColumn> listByUser(Integer networkid, Integer type){
		String hql = "from WbColumn where networkid="+networkid+" and type="+type+" order by relation asc, ctime desc";
		return this.findByHql(hql);
	}
	
	public String findKeywordsByUser(Integer networkid){
		String sql = "select group_concat(searchword) from mining.wb_column where networkid="+networkid+" and type=0 and status=1";
		List result = this.findBySql(sql);
		if(result.size()>0&&result.get(0)!=null){
			return result.get(0).toString();
		}else{
			return "";
		}		
	}
	
	//根据迫切程度返回任务,从过去向现在搜索，离现在时间越远，越急迫
	public WbColumn findByFetchTime(){
		String hql = "from WbColumn where status>=1 order by status desc, ftime asc";
		List<WbColumn> list = this.findPageByHql(hql, 0, 1);
		return list.size()>0?list.get(0):null;
	}
	
	public WbColumn findByLastid(){
		String hql = "from WbColumn where status=1 order by lastid asc, stime asc";
		List<WbColumn> list = this.findPageByHql(hql, 0, 1);
		return list.size()>0?list.get(0):null;
	}	
	
	//启用/停用任务
	public void changeStatus(Integer networkid, Integer status){
		String hql = "update WbColumn set status="+status+" where networkid="+networkid+" and searchword!=''";
		this.executeByHql(hql);
	}
	
}
