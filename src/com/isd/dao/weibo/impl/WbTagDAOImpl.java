package com.isd.dao.weibo.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.weibo.WbTagDAO;
import com.isd.entity.weibo.WbTag;


public class WbTagDAOImpl extends GenericDAOImpl<WbTag, Integer> implements WbTagDAO {
	public WbTagDAOImpl() {
		super(WbTag.class);
	}
	
	//根据key和value返回标签
	public WbTag findByName(String name){
		String hql = "from WbTag where name='"+name+"'";
		List<WbTag> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	//根据Key返回列表
	public List<WbTag>findByKey(String key){
		String hql = "from WbTag where keyword='"+key+"' order by ctime";
		return this.findByHql(hql);
	}
}
