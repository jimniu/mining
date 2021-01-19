package com.isd.dao.mining;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.Partner;

public interface PartnerDAO extends GenericDAO<Partner, Integer>{
	//根据访问的域名来提取合作伙伴
	public Partner findByUrl(String url);
	//列出合作伙伴
	public List<Partner> list(int offset, int pagesize);
}
