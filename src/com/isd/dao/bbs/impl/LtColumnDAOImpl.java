package com.isd.dao.bbs.impl;

import java.util.Date;
import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.bbs.LtColumnDAO;
import com.isd.entity.bbs.LtColumn;
import com.isd.util.C;


public class LtColumnDAOImpl extends GenericDAOImpl<LtColumn, Integer> implements LtColumnDAO {
	public LtColumnDAOImpl() {
		super(LtColumn.class);
	}
	
	public String findKeywordsByNetwork(Integer networkid){
		String sql = "select group_concat(searchword) from mining.lt_column where networkid="+networkid+" and type=0 and status=1";
		List result = this.findBySql(sql);
		if(result.size()>0&&result.get(0)!=null){
			return result.get(0).toString();
		}else{
			return "";
		}		
	}
	
	//将当日的内容统计清零
	public void resetColumnContentCountByNetworkTypeToday(Integer networkid, Integer type){
		String hql = "update LtColumn set today=0 where networkid="+networkid+" and type="+type;
		this.executeByHql(hql);
	}
	
	//根据用户ID，汇总当天的内容总数，用于首页
	public List columnContentCountByNetworkTypeToday(Integer networkid, Integer type){
		String sql = "select columnid, count(id) total from robot.lt_result where networkid="+networkid+" and type="+type+" and ptime>='"+C.getDateStart(new Date())+"' group by columnid";
		return this.findBySql(sql);
	}
	
	//根据用户ID以及类型搜索微信关键词设置
	public List<LtColumn> listByNetwork(Integer networkid, int type){
		String hql = "from LtColumn where networkid="+networkid+" and type="+type+" order by serno asc";
		return this.findByHql(hql);
	}
	public List<LtColumn> listByNetworkTypeRelation(Integer networkid, Integer type, Integer relation){
		String hql = "from LtColumn where networkid="+networkid+" and type="+type;
		if(relation>0) {
			hql += " and relation="+relation;
		}
		hql += " and status=1 order by serno asc";
		return this.findByHql(hql);
	}	
	
	//根据搜索时间，提取搜索时间最早的设置
	public LtColumn findOneByFetchTime(){
		String hql = "from LtColumn where status>=1 order by status desc, ftime asc";
		List<LtColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//根据上次搜索时间，提取最早搜索的设置
	public LtColumn findOneByStime(){
		String hql = "from LtColumn where status=1 order by stime asc";
		List<LtColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}		
	}
	
	//启用/停用任务
	public void changeStatus(Integer networkid, Integer status){
		String hql = "update LtColumn set status="+status+" where networkid="+networkid+" and searchword!=''";
		this.executeByHql(hql);
	}	
}
