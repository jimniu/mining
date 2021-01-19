package com.isd.dao.wechat.impl;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.wechat.WxWeixinDAO;
import com.isd.entity.wechat.WxWeixin;


public class WxWeixinDAOImpl extends GenericDAOImpl<WxWeixin, Integer> implements WxWeixinDAO {
	public WxWeixinDAOImpl() {
		super(WxWeixin.class);
	}


}
