package com.isd.service.common;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.common.Province;




public interface ProvinceService {
	//根据名称，返回对象
	public Province findByName(String name);
	//根据ID返回对象
	public Province findById(Integer id);
	//省份列表
	public List<Province> provinceList();
	//省份的哈希
	public HashMap<Integer, Province> provinceHash();
}
