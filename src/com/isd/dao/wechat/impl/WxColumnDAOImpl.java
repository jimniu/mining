package com.isd.dao.wechat.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.wechat.WxColumnDAO;
import com.isd.entity.wechat.WxColumn;


public class WxColumnDAOImpl extends GenericDAOImpl<WxColumn, Integer> implements WxColumnDAO {
	public WxColumnDAOImpl() {
		super(WxColumn.class);
	}
	
	public String findKeywordsByUser(Integer networkid){
		String sql = "select group_concat(searchword) from mining.wx_column where networkid="+networkid+" and type=0 and status=1";
		List result = this.findBySql(sql);
		if(result.size()>0&&result.get(0)!=null){
			return result.get(0).toString();
		}else{
			return "";
		}		
	}
	
	//将当日的内容统计清零
	public void resetColumnContentCountByUserTypeToday(Integer networkid, Integer type){
		String hql = "update WxColumn set today=0 where networkid="+networkid+" and type="+type;
		this.executeByHql(hql);
	}
	
	//根据用户ID搜索微信关键词设置
	public List<WxColumn> listByNetworkType(Integer networkid, Integer type){
		String hql = "from WxColumn where networkid="+networkid+" and type="+type+" order by serno asc";
		return this.findByHql(hql);
	}	
	public List<WxColumn> listByNetworkTypeRelation(Integer networkid, Integer type, Integer relation){
		String hql = "from WxColumn where networkid="+networkid+" and type="+type;
		if(relation>0) {
			hql += " and relation="+relation;
		}
		hql += " and status=1 order by serno asc";
		return this.findByHql(hql);
	}
	
	//根据搜索时间，提取搜索时间最早的设置
	public WxColumn findOneByFetchTime(){
		String hql = "from WxColumn where status>=1 and fetchword!= '' order by status desc, ftime asc";
		List<WxColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//根据上次搜索时间，提取最早搜索的设置
	public WxColumn findOneByStime(){
		String hql = "from WxColumn where status=1 order by stime asc";
		List<WxColumn> list = this.findPageByHql(hql, 0, 1);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}		
	}
	
	//启用/停用任务
	public void changeStatus(Integer networkid, Integer status){
		String hql = "update WxColumn set status="+status+" where networkid="+networkid+" and searchword!=''";
		this.executeByHql(hql);
	}	
}
