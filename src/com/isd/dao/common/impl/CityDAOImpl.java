package com.isd.dao.common.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.common.CityDAO;
import com.isd.entity.common.City;


public class CityDAOImpl extends GenericDAOImpl<City, Integer> implements CityDAO {
	public CityDAOImpl() {
		super(City.class);
	}
	
	//根据市的名称获取对象
	public City findByName(String name, Integer provid){
		String hql = "from City where provid="+provid+" and name='"+name+"'";
		List<City> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//城市列表
	public List<City> cityList(){
		String hql = "from City order by provid";
		return this.findByHql(hql);		
	}
	
	//城市列表
	public List<City> cityList(Integer provid){
		String hql = "from City where provid="+provid+" order by id asc";
		return this.findByHql(hql);	
	}
}
