package com.isd.dao.bbs;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.bbs.LtSite;

public interface LtSiteDAO extends GenericDAO<LtSite, Integer>{
	//根据链接地址返回站点对象
	public LtSite findByUrl(String url);
	//站点列表
	public List<LtSite> list(int offset, int pagesize);
}
