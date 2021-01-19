<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>艾数达</title>
		<%@ include file="../layout/head.jsp"%>
	</head>
	<body>
<div class="box_admin">
	<div class="admin_ceng">
		<div class="ceng">
			<div class="left"><a href="<s:url action="site/manage_index"/>"><img src="../images/admin_logo.png" /></a></div>
			<div class="right">
				<p class="title">后台管理系统</p>
				<form >
					<p><input class="yhm_inp"  type="text"   id="name"  /></p>
					<p><input class="mm_inp"  type="password"   id="password"/></p>
					<div>
						<a class="dl_button"  id="submit"><img src="../images/admin_dl_button.jpg" /></a><a href="javascript:;">忘记密码？</a>
					</div>
				</form>
			</div>
		</div>
		<div class="ceng_bottom">Copyright © 2010 艾数达科技 京ICP备09063988号</div>
		<%@ include file="../layout/footer.jsp"%>
	</div>
</div>
</body>
</html>
