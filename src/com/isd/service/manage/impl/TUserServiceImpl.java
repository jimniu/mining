package com.isd.service.manage.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.manage.TUserDAO;
import com.isd.entity.mining.TUser;
import com.isd.service.manage.TUserService;



public class TUserServiceImpl implements TUserService{
	@Autowired 
	private TUserDAO tuserDAO;
	
	//列出匹配的结果列表
	public List<TUser> list(TUser tuser, Integer offset, Integer pagesize){
		return tuserDAO.list(tuser, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(TUser tuser){
		return tuserDAO.count(tuser);
	}
	
	//删除用户
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		tuserDAO.delete("id", id);
	}
	
	//修改用户
	public void updateTUser(TUser tuser) {
		// TODO Auto-generated method stub
		tuserDAO.update(tuser);
	}

}
