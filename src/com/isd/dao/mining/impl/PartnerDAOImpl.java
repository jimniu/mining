package com.isd.dao.mining.impl;


import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.PartnerDAO;
import com.isd.entity.mining.Partner;


public class PartnerDAOImpl extends GenericDAOImpl<Partner, Integer> implements PartnerDAO {
	
	public PartnerDAOImpl() {
		super(Partner.class);
	}

	//根据访问的域名来提取合作伙伴
	public Partner findByUrl(String url){
		String hql = "from Partner where domain='"+url+"' and status=1";
		List<Partner> list = this.findByHql(hql);
		if(list.size()>0){
			return list.get(0);
		}else{
			return null;
		}
	}
	
	//列出合作伙伴
	public List<Partner> list(int offset, int pagesize){
		String hql = "from Partner order by id desc";
		return this.findPageByHql(hql, offset, pagesize);
	}
}
