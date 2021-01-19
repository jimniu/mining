package com.isd.service.bbs.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.bbs.LtSiteDAO;
import com.isd.entity.bbs.LtSite;
import com.isd.service.bbs.LtSiteService;

public class LtSiteServiceImpl implements LtSiteService{
	@Autowired
	private LtSiteDAO weixinDAO;
	
	public HashMap<Integer, LtSite> findAllHash(){
		List<LtSite> list = weixinDAO.findAll();
		HashMap<Integer, LtSite> hash = new HashMap<Integer, LtSite>();
		for(int i=0; i<list.size(); i++){
			LtSite weixin = list.get(i);
			hash.put(weixin.getId(), weixin);
		}
		return hash;
	}
}
