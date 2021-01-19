package com.isd.service.weibo.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.weibo.WbUsertagDAO;
import com.isd.entity.weibo.WbUsertag;
import com.isd.service.weibo.WbTagService;
import com.isd.service.weibo.WbUsertagService;


public class WbUsertagServiceImpl implements WbUsertagService{
	@Autowired
	private WbUsertagDAO usertagDAO;
	@Autowired
	private WbTagService tagService;
	
	//保存用户标签
	public void save(Long wbid, String name){
		if(name.trim().length()>0){
			Integer tagid = tagService.findByName(name);
			this.save(wbid, tagid);
		}
	}
	
	//保存用户标签
	public void save(Long wbid, Integer tagid){
		//判断是否已经保存用户此标签
		boolean existed = usertagDAO.exists(wbid, tagid);
		if(!existed){
			WbUsertag WbUsertag = new WbUsertag();
			WbUsertag.setWbid(wbid);
			WbUsertag.setTagid(tagid);
			usertagDAO.save(WbUsertag);
		}
	}
	
}
