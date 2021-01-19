package com.isd.dao.mark;

import com.isd.dao.GenericDAO;
import com.isd.entity.mark.ZmtTag;

public interface ZmtTagDAO extends GenericDAO<ZmtTag, Integer>{
	
	
	public Integer count(int id);
	
}
