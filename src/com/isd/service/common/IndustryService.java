package com.isd.service.common;

import java.util.List;

import com.isd.entity.common.Industry;




public interface IndustryService {
	//根据父级的ID以及名称获取对象
	public Integer findIdByName(String name, Integer parentid);

	public Industry findById(Integer id);
	//根据父级的ID获取对象
	public List<Industry> industryList(Integer parentid);
	
}
