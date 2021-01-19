package com.isd.dao.weibo;

import com.isd.dao.GenericDAO;
import com.isd.entity.weibo.WbUser;



public interface WbUserDAO extends GenericDAO<WbUser, Integer>{
	//获得已有用户的最大的ID号
	public Integer getMaxId();
	public WbUser findByWbid(Long wbid);
}
