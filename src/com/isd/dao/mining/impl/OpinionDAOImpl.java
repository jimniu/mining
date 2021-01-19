package com.isd.dao.mining.impl;

import java.util.Date;
import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.OpinionDAO;
import com.isd.entity.mining.Opinion;

public class OpinionDAOImpl extends GenericDAOImpl<Opinion, Integer> implements OpinionDAO {
	
	public OpinionDAOImpl() {
		super(Opinion.class);
	}
	
	public Opinion findByUserModulePageid(Integer networkid, String module, Long pageid){
		String hql = "from Opinion where networkid="+networkid+" and module='"+module+"' and pageid="+pageid;
		List<Opinion> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	public List<Opinion> findByNetworkModulePtime(Integer networkid, String module, Date from, Date to){
		String hql = "from Opinion where networkid="+networkid+" and module='"+module+"' and ptime>='"+from.toString()+"' and ptime<='"+to.toString()+"'";
		List<Opinion> list = this.findByHql(hql);
		return list;
	}

}
