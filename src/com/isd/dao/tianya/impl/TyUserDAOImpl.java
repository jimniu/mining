package com.isd.dao.tianya.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.tianya.TyUserDAO;
import com.isd.entity.tianya.TyUser;

public class TyUserDAOImpl extends GenericDAOImpl<TyUser, Integer> implements  TyUserDAO {
	
	public TyUserDAOImpl() {
		super(TyUser.class);
	}

}
