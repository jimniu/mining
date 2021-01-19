package com.isd.service.weibo;

import com.isd.entity.weibo.WbUser;




public interface WbUserService {
	//根据ID返回
	public WbUser findByWbid(Long id);
	//整理用户的地域信息
	//public WbUser modifyArea(WbUser user);
}
