package com.isd.dao.weibo;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.weibo.WbDict;

public interface WbDictDAO extends GenericDAO<WbDict, Integer> {
	//根据字典的类型以及名称查询对象
	public WbDict findByName(String type, String name);
	//根据类型返回列表
	public List<WbDict> findByType(String type);
}
