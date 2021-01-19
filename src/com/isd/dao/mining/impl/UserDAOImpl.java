package com.isd.dao.mining.impl;

import java.util.Date;
import java.util.List;

import com.isd.dao.GenericDAOImpl;
import com.isd.dao.mining.UserDAO;
import com.isd.entity.mining.User;
import com.isd.util.C;


public class UserDAOImpl extends GenericDAOImpl<User, Integer> implements UserDAO {
	
	public UserDAOImpl() {
		super(User.class);
	}
		
	//获取没有用户基本信息的列表
	public List<User> findNewUser(Integer count){
		String hql = "from User where utime is null order by ctime asc";
		//从随机位置开始提取，避免冲突
		int offset = (int)(Math.random()*1000);
		return this.findPageByHql(hql, offset, count);
	}
	
	//根据用户粉丝的数量范围抓取
	public List<User> findByFans(Integer count, String maxfans, String minfans, Integer days){
		String hql = "from User where fans is not null ";
		if(maxfans!=null&&minfans!=null){
			hql += "and fans<"+maxfans+" and fans>"+minfans;
		}else if(maxfans!=null){
			hql += "and fans<"+maxfans;
		}else if(minfans!=null){
			hql += "and fans>"+minfans;
		}
		
		String date = C.getDate(days);
		hql += " and utime<'"+date+" 00:00:00' order by fans desc";
		return this.findPageByHql(hql, 0, count);
	}
	
	//根据用户粉丝的数量提取
	public List<User> findByFans(Integer count){
		String hql = "from User order by fans desc";
		return this.findPageByHql(hql, 0, count);
	}
	
	//用户总数
	public Integer total(){
		String hql = "select max(id) from User";
		return this.getCountByHql(hql);
	}
	//已经抓取的用户总数
	public Integer fetched(){
		String hql = "select count(*) from User where utime is not null";
		return this.getCountByHql(hql);
	}
	
	//指定日期抓取的新用户数
	public Integer created(int days){
		String date = C.getDate(days);

		String hql = "select count(*) from User where ctime>='"+date+" 00:00:00' and ctime<='"+date+" 23:59:59'";		
		return this.getCountByHql(hql);
	}
	
	//指定日期抓取的新用户数
	public Integer updated(int days){
		String date = C.getDate(days);

		String hql = "select count(*) from User where utime>='"+date+" 00:00:00' and utime<='"+date+" 23:59:59'";
		return this.getCountByHql(hql);
	}

	public Integer addUser(User user) {		
		return this.save(user);
	}

	public void updateUser(User user) {
		// TODO Auto-generated method stub
		this.update(user);
	}

	public int checkUser(String name, String email) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User where name ='"+name+"' and email = '"+email+"'";
		return this.getCountByHql(hql);
	}

	//列出匹配的结果列表
	public List<User> list(User user, Integer offset, Integer pagesize){
		String hql = "from User  where 1=1 ";
		String part = this.genSql(user);
		if(part!=null){
			hql += part;
		}
		hql += "  order by ctime desc";
		return this.findPageByHql(hql, offset, pagesize);
	}

	//查询匹配到的用户数
	public Integer count(User user){
		String hql = "select count(id) from User where 1=1 ";
		String part = this.genSql(user);
		if(part!=null){
			hql += part;
		}
		return this.getCountByHql(hql);
	}

	private String genSql(User  user){
		String result = "";
		if(user==null){
			return null;
		}

		if(user.getName().length()>0){
			result += "and name like '%"+user.getName()+"%' ";
		}
		if(user.getMobile().length()>0){
			result += "and mobile like '%"+user.getMobile()+"%' ";
		}
		if(user.getEmail().length()>0){
			result += "and email like '%"+user.getEmail()+"%' ";
		}
		if(user.getStatus()!=null&&user.getStatus()<3){
			result += "and status='"+user.getStatus()+"' ";
		}
		if(user.getType()==null){

		}else if(user.getType()==0){
			result += "and expire>'"+ C.dateToString(new Date())+"' ";
		}else if (user.getType()==1){
			result += "and expire<='"+C.dateToString(new Date())+"' ";
		}

		return result;
	}
	
}
