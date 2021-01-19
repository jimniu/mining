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
		<td>发布时间</td>
		<td>抓取时间</td>
		<td>链接</td>
		<td>关键词</td>
		<td>转发原发</td>
		<td>关注数/阅读数</td>
		<td>粉丝数/点赞数</td>
		<td>微博数</td>
	</tr>
	<s:iterator value="xwcontentlist" status="st" id="lst">
	<tr>
		<td>新闻数据</td>
		<td><s:property value="sitename"/></td>
		<td><s:property value="title"/></td>		
		<td><s:date name="ptime" format="yyyy-MM-dd" /></td>
		<td><s:date name="ctime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><a href="<s:property value="url"/>" target="_blank"><s:property value="url"/></a></td>
		<td><s:property value="#xwcolumnhash[columnid].title"/></td>
		<td><s:if test="origin==1">原发</s:if><s:elseif test="origin==2">转发</s:elseif></td>
		<td></td>
		<td></td>
		<td></td>
	</tr>
	</s:iterator>	
	<s:iterator value="wxcontentlist" status="st" id="lst">
	<tr>
		<td>腾讯微信</td>
		<td><s:property value="wxname"/></td>
		<td><s:property value="title"/></td>		
		<td><s:date name="ptime" format="yyyy-MM-dd" /></td>
		<td><s:date name="ctime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><a href="<s:property value="host"/><s:url action="wechat/detail"/>?result.id=<s:property value="id"/>"><s:property value="host"/><s:url action="wechat/detail"/>?result.id=<s:property value="id"/></a></td>
		<td><s:property value="#wxcolumnhash[columnid].title"/></td>
		<td></td>
		<td><s:property value="readcount"/></td>
		<td><s:property value="likecount"/></td>
		<td></td>
	</tr>
	</s:iterator>
	<s:iterator value="wbcontentlist" status="st" id="lst">
	<tr>
		<td>新浪微博</td>
		<td><s:property value="#lst[1].nickname"/></td>
		<td><s:property value="#lst[0].content"/></td>		
		<td><s:date name="#lst[0].ptime" format="yyyy-MM-dd" /></td>
		<td><s:date name="#lst[0].ctime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><a href="http://weibo.com<s:property value="#lst[0].url"/>" target="_blank">http://weibo.com<s:property value="#lst[0].url"/></a></td>
		<td><s:property value="#wbcolumnhash[#lst[0].columnid].title"/></td>
		<td></td><td></td>
		<td><s:property value="#lst[1].follow"/></td>
		<td><s:property value="#lst[1].fans"/></td>
		<td><s:property value="#lst[1].feed"/></td>
	</tr>
	</s:iterator>		
	<s:iterator value="ltcontentlist" status="st" id="lst">
	<tr>
		<td>论坛数据</td>
		<td><s:property value="sitename"/></td>
		<td><s:property value="title"/></td>		
		<td><s:date name="ptime" format="yyyy-MM-dd" /></td>
		<td><s:date name="ctime" format="yyyy-MM-dd HH:mm:ss" /></td>
		<td><a href="<s:property value="url"/>" target="_blank"><s:property value="url"/></a></td>
		<td><s:property value="#ltcolumnhash[columnid].title"/></td>
		<td></td>
		<td></td>
		<td></td>
		<td></td>		
	</tr>
	</s:iterator>	
	
	</table>
	</body>
</html>