package com.isd.dao.manage.impl;

import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.manage.TUserDAO;
import com.isd.entity.mining.TUser;


public class TUserDAOImpl extends GenericDAOImpl<TUser, Integer> implements TUserDAO {
	
	public TUserDAOImpl() {
		super(TUser.class);
	}
	
	//列出匹配的结果列表
	public List<TUser> list(TUser tuser, Integer offset, Integer pagesize){
		String hql = "from TUser  where 1=1 ";
		String part = this.genSql(tuser);
		if(part!=null){
			hql += part;
		}
		hql += "  order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(TUser tuser){
		String hql = "select count(id) from TUser where 1=1 ";
		String part = this.genSql(tuser);
		if(part!=null){
			hql += part;
		}
		return this.getCountByHql(hql);
	}
	
	private String genSql(TUser  tuser){
		String result = "";
		if(tuser==null){
			return null;
		}
//		
//		if(user.getName().length()>0){
//			result += "and name='"+user.getName()+"' ";
//		}
//		if(user.getCellphone().length()>0){
//			result += "and cellphone='"+user.getCellphone()+"' ";
//		}		
//		if(user.getEmail().length()>0){
//			result += "and email='"+user.getEmail()+"' ";
//		}
//		if(user.getStatus()!=null&&user.getStatus()<3){
//			result += "and status='"+user.getStatus()+"' ";
//		}
//		if(user.getTemp().equals("0")){
//			result += "and expire>'"+new Date((new java.util.Date()).getTime())+"' ";
//		}else if (user.getTemp().equals("1")){
//			result += "and expire<='"+new Date((new java.util.Date()).getTime())+"' ";
//		}
		
		return result;
	}
}
