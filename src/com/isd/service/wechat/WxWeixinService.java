package com.isd.service.wechat;

import java.util.HashMap;

import com.isd.entity.wechat.WxWeixin;


public interface WxWeixinService {
	public HashMap<Integer, WxWeixin> findAllHash();
}
