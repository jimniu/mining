package com.isd.dao.mining.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.TUserDAO;
import com.isd.entity.mining.TUser;


public class TUserDAOImpl extends GenericDAOImpl<TUser, Integer> implements TUserDAO {
	
	public TUserDAOImpl() {
		super(TUser.class);
	}
	
}
