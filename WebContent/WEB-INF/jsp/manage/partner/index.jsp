<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>艾数达</title> 
		<%@ include file="../layout/head.jsp"%>
	</head>
	<body>
	<div class="box">
	<%@ include file="../layout/top.jsp"%>
	<div class="cont">
		<%@ include file="../layout/left.jsp"%>
		<div class="right">
			<div class="right_text"> 
				<form  action="<s:url action="partner/manage_index"/>" method="get"  id="form">
					<div class="button">
						<a href="<s:url action="partner/manage_add"/>">增加</a>
					</div>
				</form>		
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th width="70">客户</th>
							<th width="144">域名</th>
							<th>网站标题</th>
							<th width="70">标题图片</th>
							<th width="100">首页标题图片</th>
							<th width="50">logo</th>
							<th width="50">状态</th>
							<th width="150">修改时间</th>
							<th width="50">修改</th>
						</tr>
						<s:iterator  value="list" status="st">
						<tr>
							<td><s:property value="name"/></td>
							<td><s:property value="domain"/></td>
							<td><s:property value="title"/></td>
							<td>
								<s:if test='banner!=""&&banner!=null'>
									<img src="<s:property value="banner"/>" height="12" width="68"/>
								</s:if>
								<s:else>
									<img src="../images/logo_text2.png" height="12"/>
								</s:else>
							</td>
							<td bgcolor="#000000">
								<s:if test='homebanner!=""&&homebanner!=null'>
									<img src="<s:property value="homebanner"/>" height="12" width="68"/>
								</s:if>
								<s:else>
									<img src="../images/logo_text1.png" height="12"/>
								</s:else>
							</td>
							<td><img src="<s:property value="logo"/>" width="40"/></td>
							<td><s:if test="status==0">无效</s:if><s:else>有效</s:else></td>
							<td><s:date name="utime" format="yyyy-MM-dd HH:mm:ss" /></td>
							<td><a href="<s:url action="partner/manage_edit"/>?partner.id=<s:property value="id"/>"><img src="../images/xg_button.jpg" /></a></td>
						</tr>
						</s:iterator>
					</table>
				</div>
			</div>
		</div>
	</div>
	<div class="footer">
		Copyright&nbsp;&copy;&nbsp;2010&nbsp;艾数达科技&nbsp;京ICP备09063988号
	</div>
	<%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
