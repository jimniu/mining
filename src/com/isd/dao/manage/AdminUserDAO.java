package com.isd.dao.manage;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.manage.AdminUser;


public interface AdminUserDAO extends GenericDAO<AdminUser, Integer>{
	
	//列出匹配的结果列表
	public List<AdminUser> list(AdminUser au, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(AdminUser au);
}
