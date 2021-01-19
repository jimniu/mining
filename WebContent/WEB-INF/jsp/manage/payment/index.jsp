<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>艾数达</title> 
		<%@ include file="../layout/head.jsp"%>
		<link href="../css/jquery-ui.min.css" rel="stylesheet" type="text/css" />
	</head>
	<body>
	<div class="box">
	<%@ include file="../layout/top.jsp"%>
	<div class="cont">
		<%@ include file="../layout/left.jsp"%>
		<div class="right">
			<div class="right_text"> 
				<form  action="<s:url action="payment/manage_index"/>"  method="get"  id="form">
					<div class="top">
						<label>名称：</label><input type="text"  id="name"  name="user.name"/>
						<label>手机：</label><input type="text"  id="cellphone"  name="user.cellphone"/>
						<label class="long">审核状态：</label>
							<select name="user.status"><option value="3">请选择</option><option value="0">待审核</option><option value="1">已审核</option></select>
						<label class="long">创建时间：</label><input type="text"  style="width:110px; "  name="user.temp"  id="datepicker"  value="<s:property value="tempctime"/>"/>
						<label class="dao">到</label><input  type="text"    style="width:110px; " name="user.extra"   id="datepicker1"   value="<s:property value="temputime"/>"/>
					</div>
					<div class="button">
						<a href="javascript:;"  id="search">搜索</a>
						<a href="javascript:;"  id="delete" >删除</a>
					</div>
				</form>		
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th width="50"><input  type="checkbox"   id="selectAll"/></th><th width="147">用户名</th><th width="347">服务时间</th><th width="127">审核状态</th><th width="192">创建时间</th><th width="50">&nbsp;审核</th>
						</tr>
						<s:iterator  value="list" status="st">
						<tr>
							<td><input  type="checkbox"  name="paymentid"  value="<s:property value="id"/>"/></td>
							<td><s:property value="uname"/></td>
							<td><s:date name="utime"  format="yyyy-MM-dd" />   ~  <s:date name="effect"  format="yyyy-MM-dd " /></td>
							<td><s:if test="status == 0 ">待审核</s:if><s:else>已审核</s:else></td>
							<td><s:date name="ctime"  format="yyyy-MM-dd    HH:mm:ss" /></td>
							<td><a href="<s:url action="payment/manage_info"><s:param name='payment.id'   value="id"></s:param><s:param name='payment.uid'   value="uid"></s:param></s:url>"><img src="../images/xg_button.jpg" /></a></td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="fy_div_box">
					<div class="fy_div"   data-pagesize="12"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="total"/>" 
							data-urlbase="../payment/manage_index.shtml?user.name=<s:property value="user.name"/>&user.cellphone=<s:property value="user.cellphone"/>&user.status=<s:property value="user.status"/>&user.temp=<s:property value="user.temp"/>&user.extra=<s:property value="user.extra"/>">
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
