package com.isd.service.common.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.common.CategoryDAO;
import com.isd.entity.common.Category;
import com.isd.service.common.CategoryService;
import com.isd.util.C;


public class CategoryServiceImpl implements CategoryService{
	@Autowired
	private CategoryDAO categoryDAO;
	
	String key = "categoryid_hash";

	
	public List<Category> list(){
		List<Category> list = categoryDAO.findAll();
		return list;
	}
	
	public HashMap<Integer, Category>hash(){
		HashMap<Integer, Category> hash = new HashMap<Integer, Category>();
		
		Object obj = C.getFromCache(key);
		if(obj!=null){
			hash = (HashMap<Integer, Category>)obj;
		}else{
			List<Category> list = categoryDAO.findAll();
			
			for(int i=0; i<list.size(); i++){
				Category cate = list.get(i);
				hash.put(cate.getId(), cate);
			}
			C.saveToCache(key, hash);
		}
		return hash;
	}
	
	public Category findById(Integer id){
		return categoryDAO.findById(id);
	}
}
