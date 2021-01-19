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
				<form  action="<s:url action="auser/manage_index"/>" method="get"  id="form">
					<div class="gonggao_text">
						<label>名称：</label><input type="text"  id="name"  name="adminUser.name"/>
						<label>手机：</label><input type="text"  id="phone"  name="adminUser.phone"/>
						<label>邮箱：</label><input type="text"  id="email"   name="adminUser.email"/>
					</div>
					<div class="button">
						<a href="javascript:;"  id="search">搜索</a>
						<a href="<s:url action="auser/manage_add"/>">增加</a>
						<a href="javascript:;"  id="delete"  pos="<s:property value="myself.id" />">删除</a>
					</div>
				</form>		
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th width="50"><input  type="checkbox"   id="selectAll"/></th><th width="147">用户账号</th><th width="147">名称</th><th width="127">手机</th><th width="192">邮箱</th><th width="50">&nbsp;修改</th>
						</tr>
						<s:iterator  value="list" status="st">
						<tr>
							<td><input  type="checkbox"  name="auserid"  value="<s:property value="id"/>"/></td>
							<td><s:property value="name"/></td>
							<td><s:property value="fullName"/></td>
							<td><s:property value="phone"/></td>
							<td><s:property value="email"/></td>
							<td><a href="<s:url action="auser/manage_userinfo"><s:param name='adminUser.id'  value="id"></s:param></s:url>"><img src="../images/xg_button.jpg" /></a></td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="fy_div_box">
					<div class="fy_div"   data-pagesize="12"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="total"/>" 
							data-urlbase="../auser/manage_index.shtml?adminUser.name=<s:property value="adminUser.name"/>&adminUser.phone=<s:property value="adminUser.phone"/>&adminUser.email=<s:property value="adminUser.email"/>">
					</div>			
					<div class="fy_div_right"><span>共<s:property value="total"/>条</span>
					<span>到第</span><input type="text"  id="page"/><span>页</span><input class="a_qd_button" type="button" value="确定"  id="goto"/></div>
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
