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
				<form  action="<s:url action="user/manage_index"/>" method="get"  id="form">
					<div class="top">
						<label>名称：</label><input type="text"  id="name"  name="user.name" value="<s:property value="user.name"/>"/>
						<label>手机：</label><input type="text"  id="mobile"  name="user.mobile" value="<s:property value="user.mobile"/>"/>
						<label>邮箱：</label><input type="text"  id="email"   name="user.email" value="<s:property value="user.email"/>"/>
						<label class="long">审核状态：</label>
							<select name="user.status" data-value="<s:property value="user.status"/>">
								<option value="">请选择</option>
								<option value="0">待审核</option>
								<option value="1">已审核</option>
								<option value="2">禁用</option>
							</select>
						<label class="long">过期时间：</label>
							<select name="user.type" data-value="<s:property value="user.type"/>">
								<option value="">请选择</option>
								<option value="0">未过期</option>
								<option value="1">已过期</option>
							</select>
					</div>
					<div class="button">
						<a href="javascript:;"  id="search">搜索</a>
					</div>
				</form>		
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th width="40">ID</th>
							<th width="120">用户账号</th>
							<th width="50">类型</th>
							<th>所属账号</th>
							<th width="127">手机</th>
							<th width="130">注册时间</th>
							<th width="68">审核状态</th>
							<th width="70">&nbsp;操作</th>
						</tr>
						<s:iterator  value="list" status="st">
						<tr>
							<td><s:property value="id"/></td>
							<td><s:property value="name"/></td>						
							<td><s:if test="type==0">访客</s:if><s:elseif test="type==1">用户</s:elseif></td>
							<td><s:property value="#hash.get(networkid).name"/></td>
							<td><s:property value="mobile"/></td>
							<td><s:date name="ctime"  format="yyyy-MM-dd hh:mm" /></td>
							<td><s:if test="status==0">待审核</s:if><s:elseif test="status==1">已审核</s:elseif><s:else>禁用</s:else></td>
							<td>
								<a href="<s:url action="user/manage_userinfo"><s:param name='user.id'   value="id"></s:param></s:url>"><img src="../images/xg_button.jpg" /></a>
								<a href="javascipt:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" height="14" /></a>
							</td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="fy_div_box">
					<div class="fy_div"   data-pagesize="<s:property value="pagesize"/>"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="total"/>"
							data-urlbase="../user/manage_index.shtml?user.name=<s:property value="user.name"/>&user.mobile=<s:property value="user.mobile"/>&user.email=<s:property value="user.email"/>&user.status=<s:property value="user.status"/>&user.type=<s:property value="user.type"/>">
					</div>
					<div class="fy_div_right"><span>共<s:property value="total"/>条</span>
					<span>到第</span><input type="text"  id="page"/><span>页</span><input class="a_qd_button" type="button" value="确定" id="goto"/></div>
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
