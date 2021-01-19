package com.isd.dao.weibo.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.weibo.WbUserDAO;
import com.isd.entity.weibo.WbUser;


public class WbUserDAOImpl extends GenericDAOImpl<WbUser, Integer> implements WbUserDAO {
	public WbUserDAOImpl() {
		super(WbUser.class);
	}
	
	//获得已有用户的最大的ID号
	public Integer getMaxId(){
		String hql = "select max(id) from WbResult";
		return this.getCountByHql(hql);
	}
	
	public WbUser findByWbid(Long wbid){
		String hql = "from WbUser where wbid="+wbid;
		List<WbUser> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
}
