package com.isd.dao.common.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.common.CategoryDAO;
import com.isd.entity.common.Category;


public class CategoryDAOImpl extends GenericDAOImpl<Category, Integer> implements CategoryDAO {
	public CategoryDAOImpl() {
		super(Category.class);
	}
	

}
