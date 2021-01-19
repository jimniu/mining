<%@page contentType="application/vnd.ms-excel; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="utf-8">
	</head>
	<body>
	<table border="1">
	<s:if test='#mod=="xw"'>
		<tr>
			<td>数据类型</td>
			<td>标题</td>
			<td>传播次数</td>
			<td>链接</td>
			<td>发布时间</td>
			<td>抓取时间</td>
			<td>网站名称</td>
			<td>关键词</td>
		</tr>
		<s:iterator value="list" status="st" id="lst">
		<tr>
			<td>新闻数据</td>
			<td><a href="<s:property value="#lst[1]"/>"><s:property value="#lst[0]"/></a></td>
			<td><s:property value="#lst[3]"/></td>		
			<td><a href="<s:property value="#lst[1]"/>" target="_blank"><s:property value="#lst[1]"/></a></td>
			<td><s:date name="#lst[2]" format="yyyy-MM-dd" /></td>
			<td><s:date name="#lst[6]" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td><s:property value="#lst[4]"/></td>
			<td><s:property value="#hash[#lst[7]].title"/></td>
		</tr>
		</s:iterator>	
	</s:if>
	<s:if test='#mod=="wx"'>
		<tr>
			<td>数据类型</td>
			<td>标题</td>
			<td>传播次数</td>
			<td>链接</td>
			<td>发布时间</td>
			<td>抓取时间</td>
			<td>公众号</td>
			<td>关键词</td>
		</tr>
		<s:iterator value="list" status="st" id="lst">
		<tr>
			<td>微信数据</td>
			<td><a href="<s:property value="#lst[1]"/>"><s:property value="#lst[0]"/></a></td>
			<td><s:property value="#lst[3]"/></td>		
			<td><a href="<s:property value="host"/><s:url action="wechat/detail"/>?result.id=<s:property value="#lst[8]"/>" target="_blank"><s:property value="host"/><s:url action="wechat/detail"/>?result.id=<s:property value="#lst[8]"/></a></td>
			<td><s:date name="#lst[2]" format="yyyy-MM-dd" /></td>
			<td><s:date name="#lst[6]" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td><s:property value="#lst[4]"/></td>
			<td><s:property value="#hash[#lst[7]].title"/></td>
		</tr>
		</s:iterator>	
	</s:if>	
	<s:if test='#mod=="lt"'>
		<tr>
			<td>数据类型</td>
			<td>标题</td>
			<td>传播次数</td>
			<td>链接</td>
			<td>发布时间</td>
			<td>抓取时间</td>
			<td>网站名称</td>
			<td>关键词</td>
		</tr>
		<s:iterator value="list" status="st" id="lst">
		<tr>
			<td>论坛数据</td>
			<td><a href="<s:property value="#lst[1]"/>"><s:property value="#lst[0]"/></a></td>
			<td><s:property value="#lst[3]"/></td>		
			<td><a href="<s:property value="#lst[1]"/>" target="_blank"><s:property value="#lst[1]"/></a></td>
			<td><s:date name="#lst[2]" format="yyyy-MM-dd" /></td>
			<td><s:date name="#lst[6]" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td><s:property value="#lst[4]"/></td>
			<td><s:property value="#hash[#lst[7]].title"/></td>
		</tr>
		</s:iterator>	
	</s:if>	
	<s:if test='#mod=="wb"'>
		<tr>
			<td>数据类型</td>
			<td>内容</td>
			<td>转发次数</td>
			<td>链接</td>
			<td>发布时间</td>
			<td>抓取时间</td>
			<td>网站名称</td>
			<td>关键词</td>
		</tr>
		<s:iterator value="list" status="st" id="lst">
		<tr>
			<td>微博数据</td>
			<td><a href="<s:property value="#lst[1]"/>"><s:property value="#lst[0]"/></a></td>
			<td><s:property value="#lst[3]"/></td>		
			<td><a href="http://weibo.com<s:property value="#lst[1]"/>" target="_blank">http://weibo.com<s:property value="#lst[1]"/></a></td>
			<td><s:date name="#lst[2]" format="yyyy-MM-dd" /></td>
			<td><s:date name="#lst[5]" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td><s:property value="#lst[4]"/></td>
			<td><s:property value="#hash[#lst[6]].name"/></td>
		</tr>
		</s:iterator>	
	</s:if>	
	<s:if test='#mod=="wxr"'>
		<tr>
			<td>数据类型</td>
			<td>标题</td>
			<td>阅读次数</td>
			<td>链接</td>
			<td>发布时间</td>
			<td>抓取时间</td>
			<td>公众号</td>
			<td>关键词</td>
		</tr>
		<s:iterator value="list" status="st" id="lst">
		<tr>
			<td>微信数据</td>
			<td><s:property value="#lst[0]"/></td>
			<td><s:property value="#lst[3]"/></td>		
			<td><a href="<s:property value="#lst[1]"/>" target="_blank"><s:property value="#lst[1]"/></a></td>
			<td><s:date name="#lst[2]" format="yyyy-MM-dd" /></td>
			<td><s:date name="#lst[6]" format="yyyy-MM-dd HH:mm:ss" /></td>
			<td><s:property value="#lst[4]"/></td>
			<td><s:property value="#hash[#lst[7]].title"/></td>
		</tr>
		</s:iterator>	
	</s:if>	
	</table>
	</body>
</html>