<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="../layout/head.jsp"%>
		<title>报表中心-集成报表</title>
	</head>
	<body>
		<div class="box" id="loading" data-url="<s:property value="url" escape="false"/>">
			<%@ include file="../layout/top.jsp"%>
			<div class="width_box">
				<img src="../images/working.gif" style="display:none;" id="working" width="100"/>
			</div>
			<%@ include file="../layout/footer.jsp"%>
		</div>
	</body>
</html>