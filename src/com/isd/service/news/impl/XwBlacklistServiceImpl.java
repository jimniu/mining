package com.isd.service.news.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.news.XwBlacklistDAO;
import com.isd.service.news.XwBlacklistService;

public class XwBlacklistServiceImpl implements XwBlacklistService{
	@Autowired
	private XwBlacklistDAO blacklistDAO;
	

}
