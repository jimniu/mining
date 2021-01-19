package com.isd.dao.news.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.news.XwPatternDAO;
import com.isd.entity.news.XwPattern;


public class XwPatternDAOImpl extends GenericDAOImpl<XwPattern, Integer> implements XwPatternDAO {
	public XwPatternDAOImpl() {
		super(XwPattern.class);
	}
	
	public List<XwPattern> list(Integer siteid){
		String hql = "from XwPattern where siteid="+siteid+" order by serno asc";
		return this.findByHql(hql);
	}
}
