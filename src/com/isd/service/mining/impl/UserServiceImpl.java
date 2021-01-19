package com.isd.service.mining.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.mining.MessageDAO;
import com.isd.dao.mining.UserDAO;
import com.isd.entity.manage.Message;
import com.isd.entity.mining.User;
import com.isd.service.mining.UserService;
import com.isd.util.C;


public class UserServiceImpl implements UserService{
	@Autowired 
	private UserDAO userDAO;
	@Autowired
	private MessageDAO messageDAO;
	
	
	//用户注册
	public Integer addUser(User user) {
		// TODO Auto-generated method stub
		user.setCtime(new Date());
		user.setUtime(new Date());
		return userDAO.save(user);
	}

	public void delete(Integer userid){
		userDAO.delete(userid);
	}

	//检查用户名是否存在
	public Integer checkName(String name) {
		// TODO Auto-generated method stub
		return userDAO.findByProperty("name", name).size();
	}

	//验证用户
	public int checkUser(String name, String email) {
		// TODO Auto-generated method stub
		return userDAO.checkUser(name, email);
	}

	//根据HQL返回结果集
	public List<User> findByHql(String hql) {
		// TODO Auto-generated method stub
		return userDAO.findByHql(hql);
	}

	//ID查询
	public User findById(Integer id) {
		// TODO Auto-generated method stub
		return userDAO.findById(id);
	}

	//条件查询
	public List<User> findByProperty(String key, Object value) {
		// TODO Auto-generated method stub
		return userDAO.findByProperty(key, value);
	}

	//从session中获得登录后的user对象
	public User getMe() {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute(C.SESSION_USER)!=null && session.getAttribute(C.SESSION_USER) instanceof User){
			return (User)session.getAttribute(C.SESSION_USER);
		}else{
			return null;
		}		
	}

	//用户登录
	public String login(String name, String password,String rememberme) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		
		String result = "";
		
		// 无论是否登陆成功，都保存用户的邮件地址
		if(name!=null&&name.trim()!=null){
			try{
				C.setCookie(C.COOKIE_EMAIL, name, C.COOKIE_LIFE);
			}catch(Exception e){
				e.printStackTrace();
			}
		}	
		
		List<User> list = userDAO.findByProperty("name", name);
		if(list==null||list.size()<1){
			result = "用户不存在";
		} else {
			User user = list.get(0);
			if(!password.equals(user.getPassword())){
				result = "密码不正确"; 
			} else{				
				session.setAttribute(C.SESSION_USER, user);
				user.setUtime(new Date());
				user.setIpaddr(request.getRemoteAddr());
				userDAO.update(user);
				if(user.getStatus()==2){
					result = "账户已失效，请联系管理员";
					return result;
				}
				if(user.getStatus()==0||user.getExpire()==null){
					result = "账户在审核中，请耐心等待";
					return result;
				}
			
				if(user.getExpire().getTime()<new Date().getTime()){
					result = "账户已过期，请联系管理员";
				}else {
					List<Message> mlist = messageDAO.findLatest();
					if(mlist!=null&&mlist.size()>0){
						Message message = (Message) mlist.get(0);
						session.setAttribute("banner_mess", message.getTitle());
					}

					//user的对象存入session，作为判断用户是否登录的依据
					session.setAttribute(C.SESSION_USER, user);

					if(rememberme.equals("1")){
						C.setCookie(C.COOKIE_TOKEN, C.genToken(user.getId()), 365*24*3600);
					}else{
						C.setCookie(C.COOKIE_TOKEN, C.genToken(user.getId()));
					}
					result = "SUCCESS";
				}
			}
		}		
		return result;
	}
	
	//根据token登录
	public void login(String token){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		
		Integer uid = C.getIDFromToken(token);
		if(token.equals(C.genToken(uid))){
			User user = userDAO.findById(uid);
			if(user==null||user.getStatus()==2||user.getExpire().getTime()<new Date().getTime()){
				//删除token，用户手动登录
				C.removeCookie(C.COOKIE_TOKEN);
			}else {
				session.setAttribute(C.SESSION_USER, user);

				List<Message> mlist = messageDAO.findLatest();
				if (mlist != null && mlist.size() > 0) {
					Message message = (Message) mlist.get(0);
					session.setAttribute("banner_mess", message.getTitle());
				}
			}
		}
	}
	
	//根据用户ID登录
	public void login(Integer uid){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		
		User user = userDAO.findById(uid);
		session.setAttribute(C.SESSION_USER, user);
	}

	//修改
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		userDAO.update(user);
		//session.setAttribute(C.SESSION_USER, user);
	}
	//列出匹配的结果列表
	public List<User> list(User user, Integer offset, Integer pagesize){
		return userDAO.list(user, offset, pagesize);
	}

	//查询匹配到的用户数
	public Integer count(User user){
		return userDAO.count(user);
	}

	public List<User> findAll(){
		return userDAO.findAll();
	}
}
