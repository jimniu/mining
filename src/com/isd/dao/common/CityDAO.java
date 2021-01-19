package com.isd.dao.common;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.common.City;

public interface CityDAO extends GenericDAO<City, Integer> {
	//根据市的名称获取对象
	public City findByName(String name, Integer provid);
	//城市列表
	public List<City> cityList();
	//城市列表
	public List<City> cityList(Integer provid);
}
