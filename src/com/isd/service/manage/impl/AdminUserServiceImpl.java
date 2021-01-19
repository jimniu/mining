package com.isd.service.manage.impl;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.dao.manage.AdminUserDAO;
import com.isd.entity.manage.AdminUser;
import com.isd.service.manage.AdminUserService;
import com.isd.util.C;


public class AdminUserServiceImpl implements AdminUserService{
	
	@Autowired 
	private AdminUserDAO adminUserDAO;
	
	//从session中获得登录后的adminUser对象
	public AdminUser getMe(){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(false);
		if(session!=null && session.getAttribute(C.SESSION_ADMINUSER)!=null && session.getAttribute(C.SESSION_ADMINUSER) instanceof AdminUser){
			return (AdminUser)session.getAttribute(C.SESSION_ADMINUSER);
		}else{
			return null;
		}		
	}
	
	//用户登录
	public String login(String name, String password){
		HttpServletRequest request = ServletActionContext.getRequest();
		HttpSession session = request.getSession(true);
		
		String result = "";
	
		List<AdminUser> list = adminUserDAO.findByProperty("name", name);
		if(list==null||list.size()<1){
			result = "用户不存在";
		} else {
			AdminUser auser = list.get(0);
			if(!password.equals(auser.getPassword())){
				result = "密码不正确"; 
			} else {						
				auser.setUtime(new Date());
 				auser.setIpaddress(request.getRemoteAddr());		
				
				adminUserDAO.update(auser);
				if(auser.getStatus()==0){
					result = "正在审核，请耐心等待";
				} else {
					//adminUser的对象存入session，作为判断用户是否登录的依据			
					session.setAttribute(C.SESSION_ADMINUSER, auser);
					result = "SUCCESS";
				}							
			}
		}		
		return result;
	}
	
	//列出匹配的结果列表
	public List<AdminUser> list(AdminUser au, Integer offset, Integer pagesize){
		return adminUserDAO.list(au, offset, pagesize);
	}
	
	//查询匹配到的用户数
	public Integer count(AdminUser au){
		return adminUserDAO.count(au);
	}
	
	//检查用户名是否存在
	public Integer checkName(String name) {
		// TODO Auto-generated method stub
		return adminUserDAO.findByProperty("name", name).size();
	}
	
	//删除用户
	public void delete(Integer id) {
		// TODO Auto-generated method stub
		adminUserDAO.delete("id", id);
	}
	
	//获取用户信息
	public AdminUser getUserInfo(Integer id) {
		// TODO Auto-generated method stub
		return adminUserDAO.get(id);
	}
	
	//增加用户
	public Integer addAdminUser(AdminUser adminUser) {
		// TODO Auto-generated method stub		
		return adminUserDAO.save(adminUser);
	}

	//修改用户
	public void updateAdminUser(AdminUser adminUser) {
		// TODO Auto-generated method stub
		adminUserDAO.update(adminUser);
	}

}
