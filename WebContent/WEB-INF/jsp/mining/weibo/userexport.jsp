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
            <td width="80">粉丝数量</td>
            <td width="80">微博数量</td>
            <td width="80">闪现次数</td>
            <td width="100">区域</td>
		</tr>		
		<s:iterator value="resultlist" status="st" id="lst">
            <tr>
                <td class="link" data-wbid="<s:property value="#lst[1]"/>" nowrap>
					<a href="http://weibo.com/u/<s:property value="#lst[1]"/>" target="_blank"><s:property value="#lst[2]"/></a>                                    
				</td>
                <td><s:property value="#lst[5]"/></td>
                <td><s:property value="#lst[6]"/></td>
                <td><s:property value="#lst[8]"/></td>
                <td><s:property value="#provhash[#lst[3]].name"/> <s:property value="#cityhash[#lst[4]].name"/></td>
       		</tr>
		</s:iterator>
                                		
		<s:iterator value="list" status="st" id="lst">
		<tr>
			<td data-wbid="<s:property value="#lst[0].wbid"/>">
				<s:property value="#lst[0].nickname"/>
			</td>
			<td><a href="http://weibo.com<s:property value="#lst[0].homepage"/>" target="_blank">http://weibo.com<s:property value="#lst[0].homepage"/></a></td>
			<td>
				<s:if test='%{#lst[0].gender=="M"}'>男</s:if>
				<s:elseif test='%{#lst[0].gender=="F"}'>女</s:elseif>
			</td>
			<td>
				<s:property value="#lst[0].province"/> <s:property value="#lst[0].city"/>
			</td>
			<td>
				<s:property value="#lst[0].fans"/>
			</td>
			<td>
				<s:property value="#lst[0].feed"/>
			</td>	
			</tr>
		</s:iterator>
	</table>
	</body>
</html>
