package com.isd.dao.tianya.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.tianya.TyTopicDAO;
import com.isd.entity.tianya.TyTopic;

public class TyTopicDAOImpl extends GenericDAOImpl<TyTopic, Integer> implements  TyTopicDAO {
	
	public TyTopicDAOImpl() {
		super(TyTopic.class);
	}

}
