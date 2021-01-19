package com.isd.dao.mining.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.MoblocDAO;
import com.isd.entity.mining.Mobloc;


public class MoblocDAOImpl extends GenericDAOImpl<Mobloc, Integer> implements MoblocDAO {
	
	public MoblocDAOImpl() {
		super(Mobloc.class);
	}
}
