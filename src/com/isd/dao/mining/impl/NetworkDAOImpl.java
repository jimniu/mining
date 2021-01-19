package com.isd.dao.mining.impl;


import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.NetworkDAO;
import com.isd.entity.mining.Network;


public class NetworkDAOImpl extends GenericDAOImpl<Network, Integer> implements NetworkDAO {

	public NetworkDAOImpl() {
		super(Network.class);
	}

	//列出账户
	public List<Network> list(int offset, int pagesize){
		String hql = "from Network order by id desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	
	public List<Network> list(Integer status, int offset, int pagesize){
		String hql = "from Network";
		if(status!=null&&status>=0){
			hql += " where status="+status;
		}
		hql += " order by id desc";
		return this.findPageByHql(hql, offset, pagesize);
	}

	//账户数
	public Integer total(){
		String hql = "select count(id) from Network";
		return this.getCountByHql(hql);
	}
	public Integer total(Integer status){
		String hql = "select count(id) from Network";
		if(status!=null&&status>=0){
			hql += " where status="+status;
		}
		return this.getCountByHql(hql);
	}	
}
