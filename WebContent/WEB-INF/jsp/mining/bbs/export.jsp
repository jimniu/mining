<%@page contentType="application/vnd.ms-excel; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	</head>
	<body>
	<table border="1">
	<tr>
		<td>网站名称</td>
		<td>标题</td>
		<td>发布时间</td>
		<td>链接</td>
	</tr>
	<s:iterator value="list" status="st" id="lst">
	<tr>
		<td><s:property value="#lst[2]"/></td>
		<td><s:property value="#lst[1]"/></td>		
		<td><s:date name="#lst[4]" format="yyyy-MM-dd" /></td>
		<td><a href="<s:property value="#lst[3]"/>" target="_blank"><s:property value="#lst[3]"/></a></td>
	</tr>
	</s:iterator>	
	</table>
	</body>
</html>
