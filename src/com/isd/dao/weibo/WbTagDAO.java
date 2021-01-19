package com.isd.dao.weibo;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.weibo.WbTag;



public interface WbTagDAO extends GenericDAO<WbTag, Integer>{
	//根据key和value返回标签
	public WbTag findByName(String name);
	//根据Key返回列表
	public List<WbTag>findByKey(String key);
}
