package com.isd.service.mining;

import com.isd.entity.mining.Mobloc;

public interface MoblocService {
	//根据手机号提取地域信息
	public Mobloc findByMobile(String mobile);
}
