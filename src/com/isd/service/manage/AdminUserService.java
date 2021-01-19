package com.isd.service.manage;

import java.util.List;

import com.isd.entity.manage.AdminUser;


public interface AdminUserService {
	
	//从session中获得登录后的adminUser对象
	public AdminUser getMe();
	//根据账号密码登录
	public String login(String name, String password);
	//列出匹配的结果列表
	public List<AdminUser> list(AdminUser au, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(AdminUser au);	
	//检查用户名是否存在
	public Integer checkName(String name);
	//删除用户
	public void delete(Integer id);
	//获取用户信息
	public AdminUser getUserInfo(Integer id);	
	//用户注册
	public Integer addAdminUser(AdminUser adminUser);
	//修改
	public void updateAdminUser(AdminUser adminUser);
	
}
