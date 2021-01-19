package com.isd.dao.mining.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.GeostatDAO;
import com.isd.entity.mining.Geostat;


public class GeostatDAOImpl extends GenericDAOImpl<Geostat, Integer> implements GeostatDAO {
	
	public GeostatDAOImpl() {
		super(Geostat.class);
	}
	
	//按用户统计
	public List findByUser(Integer networkid, Integer type){
		String hql = "select province, sum(total) as total from Geostat where networkid=" + networkid + " and type=" + type +" group by province order by total desc";
		return this.findByHql(hql);
	}

	//按栏目统计
	public List findByColumn(Integer columnid, Integer type){
		String hql = "select province, sum(total) as total from Geostat where columnid=" + columnid +" and type=" + type + " group by province order by total desc";
		return this.findByHql(hql);
	}
	
	//返回记录
	public List<Geostat> findByColumnProvince(String date, Integer networkid, Integer columnid, String module, Integer type, Integer province){
		String hql = "from Geostat where date='"+date+"' and networkid="+networkid + " and type=" + type + " and province=" + province + " and columnid=" + columnid + " and module='"+module+"'";
		return this.findByHql(hql);
	}	
}
