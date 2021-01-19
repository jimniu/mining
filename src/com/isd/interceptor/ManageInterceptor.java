package com.isd.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.isd.entity.manage.AdminUser;
import com.isd.service.manage.AdminUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class ManageInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3756445397738229507L;

	/**
	 * 拦截器，检查用户是否已经登陆
	 */

	@Autowired
	private AdminUserService adminUserService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();//获得上下文
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		HttpSession session = request.getSession(true);

		AdminUser au = adminUserService.getMe();
		
		if (null == au) {
			return "admin";//跳转到登陆页
		}

		return invocation.invoke();
	}
}

