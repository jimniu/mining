package com.isd.dao.common.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.common.ProvinceDAO;
import com.isd.entity.common.Province;


public class ProvinceDAOImpl extends GenericDAOImpl<Province, Integer> implements ProvinceDAO {
	public ProvinceDAOImpl() {
		super(Province.class);
	}
	
	//根据省份的名称获取对象
	public Province findByName(String name){
		String hql = "from Province where name='"+name+"'";
		List<Province> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//返回省份列表
	public List<Province>provinceList(){
		String hql = "from Province order by id asc";
		return this.findByHql(hql);
	}
}
