package com.isd.dao.tianya.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.tianya.TyPostDAO;
import com.isd.entity.tianya.TyPost;

public class TyPostDAOImpl extends GenericDAOImpl<TyPost, Integer> implements  TyPostDAO {
	
	public TyPostDAOImpl() {
		super(TyPost.class);
	}

}
