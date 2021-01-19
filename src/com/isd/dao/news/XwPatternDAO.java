package com.isd.dao.news;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.news.XwPattern;


public interface XwPatternDAO extends GenericDAO<XwPattern, Integer>{
	public List<XwPattern> list(Integer siteid);
}
