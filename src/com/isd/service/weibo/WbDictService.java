package com.isd.service.weibo;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.weibo.WbDict;




public interface WbDictService {
	//根据字典类型和名称，返回对象
	public Integer findIdByName(String type, String name);
	//根据ID返回对象
	public WbDict findById(Integer id);
	//根据类型返回列表
	public List<WbDict> findByType(String type);
	public HashMap<Integer, String>hashList();
}
