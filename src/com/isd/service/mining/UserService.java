package com.isd.service.mining;

import java.util.List;

import com.isd.entity.mining.User;


public interface UserService {
	//根据账号密码登录
	public String login(String name, String password, String rememberme);
	//根据token登录
	public void login(String token);
	//根据用户ID登录
	public void login(Integer uid);
	//从session中获得登录后的user对象
	public User getMe();
	//用户注册
	public Integer addUser(User user);
	//修改用户信息
	public void updateUser(User user);
	public void delete(Integer userid);
	//检查用户名是否存在
	public Integer checkName(String name);
	//验证用户
	public int checkUser(String name,String email);
	//根据HQL返回结果集
	public List<User> findByHql(String hql);
	//条件查询
	public List<User> findByProperty(String key, Object value);
	//ID查询
	public User findById(Integer id);
	//列出匹配的结果列表
	public List<User> list(User user, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(User user);

	public List<User> findAll();
}
