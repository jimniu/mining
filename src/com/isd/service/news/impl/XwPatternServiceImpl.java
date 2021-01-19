package com.isd.service.news.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.news.XwPatternDAO;
import com.isd.entity.news.XwPattern;
import com.isd.service.news.XwPatternService;

public class XwPatternServiceImpl implements XwPatternService{
	@Autowired
	private XwPatternDAO patternDAO;
	
	public XwPattern findById(Integer id){
		return patternDAO.findById(id);
	}
	
	public List<XwPattern> list(Integer siteid){
		return patternDAO.list(siteid);
	}
}
