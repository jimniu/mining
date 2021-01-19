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
				<form  method="get"  id="form">
					<div class="top">
						<label class="long">审核状态：</label>
						<select name="recommend.status" data-value="<s:property value="recommend.status"/>">
							<option value="0">待审核</option>
							<option value="1">已审核</option>
						</select>
					</div>
				</form>
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0">
						<tr>
							<th>推荐名称</th><th width="127">审核状态</th><th width="192">创建时间</th>
							<th width="50">&nbsp;审核</th>
						</tr>
						<s:iterator  value="list" status="st">
							<tr>
								<td>
									<a href="javascript:;" data-preview="<s:url action="recommend/detail"/>?recommend.id=<s:property value="id"/>">
									<s:property value="title"/>
									</a>
								</td>
								<td><s:if test="status == 0 ">待审核</s:if><s:else>已审核</s:else></td>
								<td><s:date name="ctime"  format="yyyy-MM-dd    HH:mm:ss" /></td>
								<td><a href="javascript:;" data-verify="<s:property value="id"/>" data-status="<s:property value="status"/>"><img src="../images/xg_button.jpg" /></a></td>
							</tr>
						</s:iterator>
					</table>
				</div>
				<div class="fy_div_box">
					<div class="fy_div"   data-pagesize="<s:property value="pagesize"/>"  data-pageindex="<s:property value="pageindex"/>"  data-total="<s:property value="total"/>"
						 data-urlbase="../recommend/manage_index.shtml?recommend.status=<s:property value="recommend.status"/>">
					</div>
					<div class="fy_div_right"><span>共<s:property value="total"/>条</span>
					</div>
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