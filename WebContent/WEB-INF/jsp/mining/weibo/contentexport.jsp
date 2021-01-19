<%@page contentType="application/vnd.ms-excel; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>微博数据挖掘</title>
	</head>

	<body>
	<table width="937" border="1" cellpadding="0" cellspacing="0">
		<tr>
        	<td>微博名称</td>
            <td width="500">微博内容</td>
            <td width="500">微博链接</td>
            <td width="80">转发数</td>
            <td width="80">评论数</td>
            <td width="80">点赞数</td>
            <td width="220">发布时间</td>
		</tr>		
		<s:iterator value="resultlist" status="st" id="lst">
            <tr>
                <td class="link" data-wbid="<s:property value="#lst[0].wbid"/>" nowrap>
					<a href="http://weibo.com/u/<s:property value="#lst[0].wbid"/>" target="_blank"><s:property value="#lst[1].nickname"/></a>                                    
				</td>
                <td><s:property value="#lst[0].content"/></td>
                <td><a href="http://weibo.com<s:property value="#lst[0].url"/>">http://weibo.com<s:property value="#lst[0].url"/></a></td>
                <td><s:property value="#lst[0].forward"/></td>
                <td><s:property value="#lst[0].comment"/></td>
                <td><s:property value="#lst[0].praise"/></td>
                <td><s:date name="#lst[0].ptime" format="yyyy年MM月dd日 HH:mm" /></td>
       		</tr>
		</s:iterator>
	</table>
	</body>
</html>
