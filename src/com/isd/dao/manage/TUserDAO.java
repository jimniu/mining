package com.isd.dao.manage;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.TUser;

public interface TUserDAO extends GenericDAO<TUser, Integer>{
	
	//列出匹配的结果列表
	public List<TUser> list(TUser tuser, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(TUser tuser);
}
