package com.isd.service.news;

import java.util.HashMap;

import com.isd.entity.news.XwSite;

public interface XwSiteService {
	//将站点内容放入hash表，便于通过ID查询站点信息
	public HashMap<Integer, XwSite> findAllHash();
}
