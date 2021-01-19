package com.isd.action.manage;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.action.GenericAction;
import com.isd.entity.manage.AdminUser;
import com.isd.service.manage.AdminUserService;
import com.isd.util.C;


public class AdminUserAction extends GenericAction{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Autowired
	private AdminUserService adminUserService;
	private AdminUser adminUser;
	
	
	//登出
	public String logout(){
		session.removeAttribute(C.SESSION_ADMINUSER);
		return SUCCESS;
	}
	
	//登录
	public String login(){ 
		String result = adminUserService.login(adminUser.getName(), adminUser.getPassword());
		ctx.put("text", result);
		return TEXT;
	}
	
	//注册用户页面
	public String add(){
		return SUCCESS;
	}
	
	//管理员首页
	public String index(){ 
		List<AdminUser> list = adminUserService.list(adminUser, pageindex*12, 12);
		Integer total = adminUserService.count(adminUser);
		ctx.put("list", list);
		ctx.put("total", total.toString());	
		return SUCCESS;
	}
	
	//检测用户名是否已注册
	public String checkUserName(){
		AdminUser au = (AdminUser)session.getAttribute("au");
		if(au!=null && au.getName()!=null){
			if(adminUser.getName().equals(au.getName())){
				ctx.put("text", 0);
				return TEXT;
			}
		}
		Integer result = adminUserService.checkName(adminUser.getName());
		ctx.put("text", result);				
		return TEXT;
	}
	
	//保存注册用户
	public String save(){
		adminUser.setStatus(1);
		adminUser.setCtime(new Date());
		adminUserService.addAdminUser(adminUser);
		return SUCCESS;
	}
	
	//删除
	public String delete(){
		if(adminUser.getTemp()!=null&&adminUser.getTemp().length()>0){
			String scope = adminUser.getTemp();
			String[] aa = scope.split(",");
			AdminUser au = (AdminUser)session.getAttribute(C.SESSION_ADMINUSER);
			int total = aa.length;
		    for (int i = 0 ; i <aa.length ; i++ ) {		    
		      Integer id = Integer.parseInt(aa[i]);
		      if(id != au.getId()){
		    	  System.out.println("删除ID为 ： "+aa[i]); 
		    	  adminUserService.delete(id);
		      }else{
		    	  total = total - 1;
		      }		      
		    } 
			ctx.put("text", "删除了"+total+"条");
		}
		return TEXT;
	}
	
	//获得用户信息
	public String getUserInfo(){ 
		Integer id = adminUser.getId();		
		AdminUser au = adminUserService.getUserInfo(id);
		session.setAttribute("au", au);
		return SUCCESS;
	}

	//修改
	public String updateAdminUser(){
		AdminUser au = (AdminUser)session.getAttribute("au");
		au.setEmail(adminUser.getEmail());
		au.setName(adminUser.getName());
		au.setFullName(adminUser.getFullName());
		au.setPassword(adminUser.getPassword());
		au.setPhone(adminUser.getPhone());
		adminUserService.updateAdminUser(au);
		return SUCCESS;
	}

	public AdminUser getAdminUser() {
		return adminUser;
	}

	public void setAdminUser(AdminUser adminUser) {
		this.adminUser = adminUser;
	}
	
}
