package com.isd.service.weibo.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.weibo.WbDictDAO;
import com.isd.entity.weibo.WbDict;
import com.isd.service.weibo.WbDictService;


public class WbDictServiceImpl implements WbDictService{
	@Autowired
	private WbDictDAO dictDAO;
	
	//根据字典类型和名称，返回对象
	public Integer findIdByName(String type, String name){
		WbDict dict = dictDAO.findByName(type, name);
		if(dict==null){
			dict = new WbDict();
			dict.setType(type);
			dict.setName(name);
			Integer id = dictDAO.save(dict);
			dict.setId(id);
		}		
		return dict.getId();
	}
	
	//根据ID返回对象
	public WbDict findById(Integer id){
		return dictDAO.findById(id);
	}
	
	//根据类型返回列表
	public List<WbDict> findByType(String type){
		return dictDAO.findByType(type);
	}
	
	public HashMap<Integer, String>hashList(){
		HashMap<Integer, String> hash = new HashMap<Integer, String>();
		
		List<WbDict> list = dictDAO.findAll();
		for(int i=0; i<list.size(); i++){
			WbDict dict = list.get(i);
			hash.put(dict.getId(), dict.getName());
		}
		return hash;
	}
}
