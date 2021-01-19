package com.isd.dao.weibo;

import com.isd.dao.GenericDAO;
import com.isd.entity.weibo.WbUsertag;



public interface WbUsertagDAO extends GenericDAO<WbUsertag, Integer>{
	public boolean exists(Long wbid, Integer tagid);
}
