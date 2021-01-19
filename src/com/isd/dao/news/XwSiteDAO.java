package com.isd.dao.news;

import com.isd.dao.GenericDAO;
import com.isd.entity.news.XwSite;


public interface XwSiteDAO extends GenericDAO<XwSite, Integer>{
	//根据链接地址返回站点对象
	public XwSite findByUrl(String url);
}
