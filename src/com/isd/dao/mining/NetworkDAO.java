package com.isd.dao.mining;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.Network;

public interface NetworkDAO extends GenericDAO<Network, Integer>{
	//列出账户
	public List<Network> list(int offset, int pagesize);
	public List<Network> list(Integer status, int offset, int pagesize);	
	//账户数
	public Integer total();
	public Integer total(Integer status);
}
