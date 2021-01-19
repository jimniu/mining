package com.isd.service.wechat.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.wechat.WxWeixinDAO;
import com.isd.entity.wechat.WxWeixin;
import com.isd.service.wechat.WxWeixinService;

public class WxWeixinServiceImpl implements WxWeixinService{
	@Autowired
	private WxWeixinDAO weixinDAO;
	
	public HashMap<Integer, WxWeixin> findAllHash(){
		List<WxWeixin> list = weixinDAO.findAll();
		HashMap<Integer, WxWeixin> hash = new HashMap<Integer, WxWeixin>();
		for(int i=0; i<list.size(); i++){
			WxWeixin weixin = list.get(i);
			hash.put(weixin.getId(), weixin);
		}
		return hash;
	}
}
