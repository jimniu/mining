package com.isd.dao.mining;

import java.util.List;

import com.isd.dao.GenericDAO;
import com.isd.entity.mining.User;



public interface UserDAO extends GenericDAO<User, Integer>{
	
	//根据用户抓取的时间排序
	public List<User> findNewUser(Integer count);
	//根据用户粉丝的数量范围抓取
	public List<User> findByFans(Integer count, String maxfans, String minfans, Integer days);
	//根据用户粉丝的数量提取
	public List<User> findByFans(Integer count);	
	//用户总数
	public Integer total();
	//已经抓取的用户总数
	public Integer fetched();
	//指定日期抓取的新用户数
	public Integer created(int days);
	//指定日期抓取的新用户数
	public Integer updated(int days);
	//用户注册
	public Integer addUser(User user);
	//修改
	public void updateUser(User user);
	
	public int checkUser(String name,String email);
	//列出匹配的结果列表
	public List<User> list(User user, Integer offset, Integer pagesize);
	//查询匹配到的用户数
	public Integer count(User user);
}
