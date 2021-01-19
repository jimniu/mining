package com.isd.dao.manage.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.manage.AdminUserDAO;
import com.isd.entity.manage.AdminUser;

 
public class AdminUserDAOImpl extends GenericDAOImpl<AdminUser, Integer> implements AdminUserDAO {
	
	public AdminUserDAOImpl() {
		super(AdminUser.class);
	}
		
	//列出匹配的结果列表
	public List<AdminUser> list(AdminUser au, Integer offset, Integer pagesize){
		String hql = "from AdminUser  where 1=1 ";
		String part = this.genSql(au);
		if(part!=null){
			hql += part;
		}
		return this.findPageByHql(hql, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(AdminUser au){
		String hql = "select count(id) from AdminUser where 1=1 ";
		String part = this.genSql(au);
		if(part!=null){
			hql += part;
		}
		return this.getCountByHql(hql);
	}
	
	private String genSql(AdminUser  au){
		String result = "";
		if(au==null){
			return null;
		}
		
		if(au.getName().length()>0){
			result += "and name='"+au.getName()+"' ";
		}
		if(au.getPhone().length()>0){
			result += "and phone='"+au.getPhone()+"' ";
		}		
		if(au.getEmail().length()>0){
			result += "and email='"+au.getEmail()+"' ";
		}
		
		return result;
	}
}
