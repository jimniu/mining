package com.isd.interceptor;

import org.springframework.beans.factory.annotation.Autowired;

import com.isd.entity.mining.User;
import com.isd.service.mining.UserService;
import com.isd.util.C;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class AuthorityInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3756445397738229507L;

	/**
	 * 拦截器，检查用户是否已经登陆
	 */

	@Autowired
	private UserService userService;
	
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		// 从session中提取user对象，如果没有，则自动从cookie中提取数据自动实现登录，否则返回null
		User myself = userService.getMe();
		
		if(myself==null){
			String token = C.getCookie(C.COOKIE_TOKEN);
			
			if(token==null){
				token = C.getParameter("token");
			}
			if(token!=null){
				userService.login(token);
				myself = userService.getMe();
			}
		}
		
		if (null == myself) {
			return "home";//跳转到登陆页
		}
		return invocation.invoke();
	}
}

