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
				<form  action="<s:url action="message/manage_index"/>" method="get"  id="form">
					<div class="gonggao_text">
						<label>标题：</label><input type="text"  value="<s:property value="temptitle"/>"    name="message.title"/>
						<label>内容：</label><input type="text"   value="<s:property value="tempcontent"/>"  name="message.content"/>
					</div>
					<div class="button">
						<a href="javascript:;"  id="search">搜索</a>
						<a href="<s:url action="message/manage_add"/>">增加</a>
						<a href="javascript:;"  id="delete"  pos="<s:property value="mynetwork.id" />">删除</a>
					</div>
				</form>		
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th width="50"><input  type="checkbox"   id="selectAll"/></th><th width="344">标题</th><th width="77">发布状态</th><th width="192">时间</th><th width="50">&nbsp;修改</th>
						</tr>
						<s:iterator  value="list" status="st">
						<tr>
							<td><input  type="checkbox"  name="messageid"  value="<s:property value="id"/>"/></td>
							<td><s:property value="title"/></td>
							<td><s:if test="status == 0 ">未发布</s:if><s:else>已发布</s:else></td>
							<td><s:date name="ctime"  format="yyyy-MM-dd    HH:mm:ss" /></td>
							<td><a href="<s:url action="message/manage_info"><s:param name='message.id'  value="id"></s:param></s:url>"><img src="../images/xg_button.jpg" /></a></td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="fy_div_box">
					<div class="fy_div"   data-pagesize="12"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="total"/>" 
							data-urlbase="../message/manage_index.shtml?message.title=<s:property value="message.title"/>&message.content=<s:property value="message.content"/>">
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
