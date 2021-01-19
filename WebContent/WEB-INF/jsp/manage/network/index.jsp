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
				<form  action="<s:url action="network/manage_index"/>" method="get"  id="searchform">
					<div class="button">
						<a href="<s:url action="network/manage_add"/>"  id="add" >新增账号</a>
						<select name="network.status" data-status="<s:property value="network.status"/>">
							<option value="">所有账户</option>
							<option value="1">活跃账户</option>
							<option value="0">非活账户</option>
						</select>
					</div>					
				</form>
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th width="40">ID</th>
							<th width="120">账号</th>
							<th width="60">类型</th>
							<th width="80">所属渠道</th>
							<th>公司名称</th>
							<th width="130">注册时间</th>
							<th width="80">审核状态</th>
							<th width="50">优先级</th>
							<th width="140">&nbsp;操作</th>
						</tr>
						<s:iterator  value="list" status="st">
						<tr>
							<td><s:property value="id"/></td>
							<td><s:property value="name"/></td>
							<td><s:if test="type==0">标准</s:if><s:elseif test="type==1">高级</s:elseif></td>
							<td><s:property value="#hash.get(partnerid).name"/></td>
							<td><s:property value="company"/></td>
							<td><s:date name="ctime"  format="yyyy-MM-dd hh:mm" /></td>
							<td><s:if test="status==0">非活动</s:if><s:elseif test="status==1">活动</s:elseif></td>
							<td><s:property value="priority"/></td>
							<td>
								<a href="<s:url action="network/manage_info"><s:param name='network.id'   value="id"></s:param></s:url>"><img src="../images/xg_button.jpg"  height="14" title="编辑账户"/></a>
								<a href="<s:url action="network/manage_keyword"><s:param name='network.id'   value="id"></s:param></s:url>"><img src="../images/yd_bt.jpg"  height="14" title="账户关键词"/></a>
								<a href="javascipt:;" data-delete="<s:property value="id"/>"><img src="../images/sc_bt.jpg" height="14" title="删除账户"/></a>
								<a href="<s:url action="network/manage_visit"/>?network.id=<s:property value="id"/>" target="_blank"><img src="../images/left_bt_title5.png" height="14" title="访问该账户"/></a>
							</td>
						</tr>
						</s:iterator>
					</table>
				</div>
				<div class="fy_div_box">
					<div class="fy_div"   data-pagesize="<s:property value="pagesize"/>"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="total"/>"
							data-urlbase="../network/manage_index.shtml?network.status=<s:property value="network.status"/>">
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
