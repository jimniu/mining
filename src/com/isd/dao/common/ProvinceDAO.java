package com.isd.dao.common;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.common.Province;

public interface ProvinceDAO extends GenericDAO<Province, Integer> {
	//根据省份的名称获取对象
	public Province findByName(String name);
	//返回省份列表
	public List<Province>provinceList();
}
