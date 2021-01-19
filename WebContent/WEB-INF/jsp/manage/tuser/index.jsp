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
					<!-- 
					<div class="top">
						<label>名称：</label><input type="text"  id="name"  name="user.name"/>
						<label>手机：</label><input type="text"  id="cellphone"  name="user.cellphone"/>
						<label>邮箱：</label><input type="text"  id="email"   name="user.email"/>
						<label class="long">审核状态：</label>
							<select name="user.status"><option value="3">请选择</option><option value="0">待审核</option><option value="1">已审核</option><option value="2">禁止</option></select>
						<label class="long">过期时间：</label>
							<select name="user.temp"><option value="2">请选择</option><option value="0">未过期</option><option value="1">已过期</option></select>
					</div>
					 -->
					<div class="button">
					<!-- 	<a href="javascript:;"  id="search">搜索</a>  -->
						<a href="javascript:;"  id="delete" >删除</a>
					</div>
				 </form>	
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th width="50"><input  type="checkbox"   id="selectAll"/></th><th width="107">姓名</th><th width="187">企业名称</th><th width="127">手机</th><th width="130">固定电话</th><th width="192">邮箱</th><th width="136">申请时间</th><th width="50">&nbsp;删除</th>
						</tr>
						<s:iterator  value="list" status="st">
						<tr>
							<td><input  type="checkbox"  name="tuserid"  value="<s:property value="id"/>"/></td>
							<td><s:property value="name"/></td>						
							<td><s:property value="company"/></td>
							<td><s:property value="mobile"/></td>
							<td><s:property value="tellphone"/></td>
							<td><s:property value="email"/></td>
							<td><s:date name="ctime"  format="yyyy-MM-dd" /></td>
							<td><a href="<s:url action="tuser/manage_delete"><s:param name='temp'   value="id"></s:param></s:url>"><img src="../images/xg_button.jpg" /></a></td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="fy_div_box">
					<div class="fy_div"   data-pagesize="12"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="total"/>" 
							data-urlbase="../tuser/manage_index.shtml">
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
