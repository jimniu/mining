package com.isd.dao.weibo.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.weibo.WbDictDAO;
import com.isd.entity.weibo.WbDict;


public class WbDictDAOImpl extends GenericDAOImpl<WbDict, Integer> implements WbDictDAO {
	public WbDictDAOImpl() {
		super(WbDict.class);
	}
	
	//根据字典的类型以及名称查询对象
	public WbDict findByName(String type, String name){
		String hql = "from WbDict where type='"+type+"' and name='"+name+"'";
		List<WbDict> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//根据类型返回列表
	public List<WbDict> findByType(String type){
		String hql = "from WbDict where type='"+type+"' order by name asc";
		return this.findByHql(hql);
	}
}
