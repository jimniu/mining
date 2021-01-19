package com.isd.dao.common.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.common.IndustryDAO;
import com.isd.entity.common.Industry;


public class IndustryDAOImpl extends GenericDAOImpl<Industry, Integer> implements IndustryDAO {
	public IndustryDAOImpl() {
		super(Industry.class);
	}
	//根据父级的ID以及名称获取对象
	public Industry findByName(String name, Integer parentid){
		String hql = "from Industry where parentid="+parentid+" and name='"+name+"'";
		List<Industry> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//根据父级的ID获取对象	
	public List<Industry> industryList(Integer parentid){
		String hql = "from Industry where parentid="+parentid+" order by id asc";
		return this.findByHql(hql);	
	}
}
