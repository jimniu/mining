package com.isd.service.news.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.news.XwSiteDAO;
import com.isd.entity.news.XwSite;
import com.isd.service.news.XwSiteService;

public class XwSiteServiceImpl implements XwSiteService{
	@Autowired
	private XwSiteDAO siteDAO;
	
	//将站点内容放入hash表，便于通过ID查询站点信息
	public HashMap<Integer, XwSite> findAllHash(){
		List<XwSite> list = siteDAO.findAll();
		HashMap<Integer, XwSite> hash = new HashMap<Integer, XwSite>();
		for(int i=0; i<list.size(); i++){
			XwSite site = list.get(i);
			hash.put(site.getId(), site);
		}
		return hash;
	}
}
