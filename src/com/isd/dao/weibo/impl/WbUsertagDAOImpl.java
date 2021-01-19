package com.isd.dao.weibo.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.weibo.WbUsertagDAO;
import com.isd.entity.weibo.WbUsertag;


public class WbUsertagDAOImpl extends GenericDAOImpl<WbUsertag, Integer> implements WbUsertagDAO {
	public WbUsertagDAOImpl() {
		super(WbUsertag.class);
	}

	public boolean exists(Long wbid, Integer tagid){
		String hql = "from WbUsertag where wbid="+wbid+" and tagid="+tagid;
		return this.findByHql(hql).size() >0;
	}
}
