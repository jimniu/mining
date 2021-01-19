package com.isd.interceptor;

import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class InternalLinkInterceptor extends AbstractInterceptor {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7694857748581357661L;

	/**
	 * 拦截器，检查上一个地址是否是本网站的链接，用于防止盗链或者作弊。比如用户从其他网站或本次访问
	 * 加入该拦截器的链接时，将不成功
	 */

	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		ActionContext ctx = invocation.getInvocationContext();//获得上下文
		HttpServletRequest request = (HttpServletRequest) ctx.get(ServletActionContext.HTTP_REQUEST);
		
		//提取url的前缀
		String prefix = request.getRequestURL().toString();
		prefix = prefix.substring(0, prefix.indexOf(request.getServletPath()));
		ctx.put("prefix", prefix);

		
		//上一页面的地址
		String referer = request.getHeader("Referer");
		//服务器的域名
		String host = request.getServerName();

		if(referer == null || referer.indexOf(host)==-1){
			ctx.put("message", "该地址不支持外链，请通过站内链接访问："+request.getRequestURL());
			return "message";
		}
		return invocation.invoke();
	}
}
