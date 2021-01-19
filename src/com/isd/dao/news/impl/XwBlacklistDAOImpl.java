package com.isd.dao.news.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.news.XwBlacklistDAO;
import com.isd.entity.news.XwBlacklist;


public class XwBlacklistDAOImpl extends GenericDAOImpl<XwBlacklist, Integer> implements XwBlacklistDAO {
	public XwBlacklistDAOImpl() {
		super(XwBlacklist.class);
	}
	

}
