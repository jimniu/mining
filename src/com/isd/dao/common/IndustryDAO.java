package com.isd.dao.common;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.common.Industry;

public interface IndustryDAO extends GenericDAO<Industry, Integer> {
	//根据父级的ID以及名称获取对象
	public Industry findByName(String name, Integer parentid);
	//根据父级的ID获取对象
	public List<Industry> industryList(Integer parentid);
}
