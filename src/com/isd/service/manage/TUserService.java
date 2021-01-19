package com.isd.service.manage;

import java.util.List;

import com.isd.entity.mining.TUser;

public interface TUserService {
	//列出匹配的结果列表
	public List<TUser> list(TUser tuser, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(TUser tuser);	
	//删除用户
	public void delete(Integer id);
	//修改
	public void updateTUser(TUser tuser);
}
