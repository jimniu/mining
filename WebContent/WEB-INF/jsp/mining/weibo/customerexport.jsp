<%@page contentType="application/vnd.ms-excel; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8"/>
		<title>微博挖掘-用户管理</title>
	</head>
	<body>
		<table width="805" cellpadding="0" cellspacing="0" border="1">
			<tr class="title">
				<td width="140">
					微博名称
				</td>
				<td width="100">
					粉丝数量
				</td>
				<td width="100">
					微博数量
				</td>
				<td width="110">
					收录时间
				</td>
				<td width="100">
					所属任务
				</td>
			</tr>
			<s:iterator value="list" id="lst" status="st">
				<tr>
					<td>
						<a href="http://weibo.com/u/<s:property value="wbid"/>"
							target="_blank"><s:property value="nickname" />
						</a>
					</td>
					<td>
						<s:property value="fans" />
					</td>
					<td>
						<s:property value="feed" />
					</td>
					<td>
						<s:date name="ctime" format="yyyy-MM-dd HH:mm" />
					</td>
					<td>
						<s:property value="column.name" />
					</td>
				</tr>
			</s:iterator>
		</table>
	</body>
</html>

