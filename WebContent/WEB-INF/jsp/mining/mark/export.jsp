<%@page contentType="application/vnd.ms-excel; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>自媒体数据挖掘</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	</head>

	<body>
	<table width="937" border="1" cellpadding="0" cellspacing="0">
		<tr>
			<th>标题</th>
			<th>链接地址</th>
			<th>收藏时间</th>
		</tr>
		<s:iterator value="list" status="st">
		<tr>
			<td>
				<s:property value="title"/>
			</td>
			<td>
				<s:property value="address"/>
			</td>
			<td>
				<s:date name="ctime" format="yyyy-MM-dd" />
			</td>	
			</tr>
		</s:iterator>
	</table>
	</body>
</html>
