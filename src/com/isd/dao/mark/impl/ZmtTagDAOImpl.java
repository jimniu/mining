package com.isd.dao.mark.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mark.ZmtTagDAO;
import com.isd.entity.mark.ZmtTag;

public class ZmtTagDAOImpl extends GenericDAOImpl<ZmtTag, Integer> implements  ZmtTagDAO {
	
	public ZmtTagDAOImpl() {
		super(ZmtTag.class);
	}


	public Integer count(int id ) {
		// TODO Auto-generated method stub
		String sql ="select count(id) from ZmtTag where uid= "+id;
		return this.getCountByHql(sql);
	}
	
}
