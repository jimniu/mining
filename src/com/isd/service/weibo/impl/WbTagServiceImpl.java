package com.isd.service.weibo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.weibo.WbTagDAO;
import com.isd.entity.weibo.WbTag;
import com.isd.service.weibo.WbTagService;


public class WbTagServiceImpl implements WbTagService{
	@Autowired
	private WbTagDAO tagDAO;
	
	//保存tag或直接提取tag的id
	public Integer findByName(String name){
		Integer tagid;
		WbTag tag = tagDAO.findByName(name);
		if(tag==null){
			tag = new WbTag();
			tag.setName(name);
			tagid = tagDAO.save(tag);
		}else{
			tagid = tag.getId();
		}
		return tagid;
	}
}
