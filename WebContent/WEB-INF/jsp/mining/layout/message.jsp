<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<div class="asd_title">
	<s:if test="myself!=null">最新消息：<s:property value="#session.banner_mess"/></s:if>
</div>