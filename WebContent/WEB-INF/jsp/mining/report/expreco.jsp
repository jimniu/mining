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
		<td>数据类型</td>
		<td>网站名称</td>
		<td>标题</td>
		<td>链接</td>
		<td>发布时间</td>
		<td>抓取时间</td>
	</tr>
	<s:iterator value="list" status="st" id="lst">
	<tr>
		<td>
			<s:if test='module=="xw"'>
			新闻数据
			</s:if>
			<s:elseif test='module=="wx"'>
			腾讯微信
			</s:elseif>
			<s:elseif test='module=="lt"'>
			论坛数据
			</s:elseif>
			<s:elseif test='module=="wb"'>
			新浪微博
			</s:elseif>
			<s:elseif test='module=="zmt"'>
			自媒体
			</s:elseif>
		</td>
		<td><s:property value="sitename"/></td>
		<td><s:property value="title"/></td>		
		<td>
			<s:if test='module=="wx"'>
				<a href="<s:property value="host"/><s:url action="wechat/detail"/>?result.id=<s:property value="pageid"/>"><s:property value="host"/><s:url action="wechat/detail"/>?result.id=<s:property value="pageid"/></a>
			</s:if>
			<s:else>
				<a href="<s:property value="url"/>" target="_blank"><s:property value="url"/></a>
			</s:else>
			
		</td>
		<td><s:date name="ptime" format="yyyy-MM-dd" /></td>
		<td><s:date name="ctime" format="yyyy-MM-dd HH:mm:ss" /></td>
	</tr>
	</s:iterator>	
	</table>
	</body>
</html>