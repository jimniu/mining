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
				<form>
				<div class="button">
					<a href="javascript:;"  id="add" onclick="javascript:history.back();">返回</a>
				</div>
				</form>
				<div class="table_div">
					<table width="918" border="0" cellpadding="0" cellspacing="0" style="margin-top: 15px;">
						<tr>
							<th width="100">内容类型</th>
							<th width="120">关键词数量</th>
							<th>关键词列表</th>
						</tr>
						<tr>
							<td>微博</td>
							<td><s:property value="wbcount"/></td>
							<td><s:property value="wbkeyword"/></td>
						</tr>
						<tr>
							<td>微信</td>
							<td><s:property value="wxcount"/></td>
							<td><s:property value="wxkeyword"/></td>
						</tr>
						<tr>
							<td>新闻</td>
							<td><s:property value="xwcount"/></td>
							<td><s:property value="xwkeyword"/></td>
						</tr>
						<tr>
							<td>论坛</td>
							<td><s:property value="ltcount"/></td>
							<td><s:property value="ltkeyword"/></td>
						</tr>	
						<tr>
							<td>负面</td>
							<td><s:property value="fmcount"/></td>
							<td><s:property value="fmkeyword"/></td>
						</tr>
						<tr>
							<td>事件</td>
							<td><s:property value="sjcount"/></td>
							<td><s:property value="sjkeyword"/></td>
						</tr>
						<tr>
							<td>总计</td>
							<td colspan="2"><s:property value="total"/></td>
						</tr>				
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
