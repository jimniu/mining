package com.isd.dao.news.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.news.XwColumnDAO;
import com.isd.entity.news.XwColumn;


public class XwColumnDAOImpl extends GenericDAOImpl<XwColumn, Integer> implements XwColumnDAO {
	public XwColumnDAOImpl() {
		super(XwColumn.class);
	}
	
	public String findKeywordsByUser(Integer networkid){
		String sql = "select group_concat(searchword) from mining.xw_column where networkid="+networkid+" and type=0 and status=1";
		List result = this.findBySql(sql);
		if(result.size()>0&&result.get(0)!=null){
			return result.get(0).toString();
		}else{
			return "";
		}		
	}
	
	//将当日的内容统计清零
	public void resetColumnContentCountByUserTypeToday(Integer networkid, Integer type){
		String hql = "update XwColumn set today=0 where networkid="+networkid+" and type="+type;
		this.executeByHql(hql);
	}
	
	public XwColumn findOneByFetchTime(){
		String hql = "from XwColumn where status>=1 order by status desc, ftime asc";
		List<XwColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public XwColumn findOneBySearchTime(){
		String hql = "from XwColumn where status=1 order by stime asc";
		List<XwColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}		
	}
	
	public List<XwColumn> listByUser(Integer uid, Integer type){
		String hql = "from XwColumn where networkid="+uid+" and type="+type+" order by serno asc";
		return this.findByHql(hql);
	}
	public List<XwColumn> listByNetworkTrackRelation(Integer uid, Integer type, Integer relation){
		String hql = "from XwColumn where networkid="+uid+" and type="+type;
		if(relation>0) {
			hql += " and relation="+relation;
		}
		hql += " and status=1 order by serno asc";
		return this.findByHql(hql);
	}	
	//启用/停用任务
	public void changeStatus(Integer networkid, Integer status){
		String hql = "update XwColumn set status="+status+" where networkid="+networkid+" and searchword!=''";
		this.executeByHql(hql);
	}	
}
