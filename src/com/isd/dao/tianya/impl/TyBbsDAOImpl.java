package com.isd.dao.tianya.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.tianya.TyBbsDAO;
import com.isd.entity.tianya.TyBbs;

public class TyBbsDAOImpl extends GenericDAOImpl<TyBbs, Integer> implements  TyBbsDAO {
	
	public TyBbsDAOImpl() {
		super(TyBbs.class);
	}

}
