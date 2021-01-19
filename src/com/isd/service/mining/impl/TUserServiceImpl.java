package com.isd.service.mining.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.TUserDAO;
import com.isd.entity.mining.TUser;
import com.isd.service.mining.TUserService;


public class TUserServiceImpl implements TUserService{
	@Autowired 
	private TUserDAO tuserDAO;

	public void add(TUser tuser) {
		// TODO Auto-generated method stub
		tuserDAO.save(tuser);
	}
	
	
}
