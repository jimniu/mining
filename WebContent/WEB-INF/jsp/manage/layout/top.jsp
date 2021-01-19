<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<div class="header_tow">
		<div class="header_tow_samil">
		<div class="logo"><a href="<s:url action="auser/manage_index"/>"><img src="../images/logo_.jpg" /></a></div>
		<div class="right"><span><a  href="<s:url action="auser/manage_userinfo"><s:param name='adminUser.id'  value="#session.adminUser.id"></s:param></s:url>">你好，<s:property value="#session.adminUser.name" /></a></span><a href="<s:url action="auser/manage_logout"/>"><img src="../images/header_tow_but_.jpg" /></a></div>
		</div>
</div>