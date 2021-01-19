package com.isd.service.weibo;




public interface WbUsertagService {
	//保存用户标签
	public void save(Long wbid, Integer tagid);
	public void save(Long wbid, String name);
}
