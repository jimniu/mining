package com.isd.service.common;

import java.util.HashMap;
import java.util.List;

import com.isd.entity.common.Category;





public interface CategoryService {
	//列表
	public List<Category> list();
	public HashMap<Integer, Category>hash();
	public Category findById(Integer id);
}
