package com.isd.service.common;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.common.City;




public interface CityService {
	//根据名称，返回对象
	public City findByName(Integer provid, String name);
	//根据ID返回对象
	public City findById(Integer id);
	//城市列表
	public List<City> citylist(Integer provid);	
	public HashMap<Integer, City> cityHash();
}
