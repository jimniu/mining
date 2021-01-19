<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>

    <script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
    <title><s:property value="content.title"/></title>
</head>
<body>

	<div class="t_gai" style=" font-size:1.25em; color:#2d2c2c;width:90%; margin:20px auto;overflow:hidden">
		
		<div class="t_center" style="width:100%;">
			<!--<div class="t_bt">
				<a href="javascript:;"><img src="../images/t_bt.png" /></a>
			</div>-->
			<h3 style="text-align:center;margin:0px auto;overflow:hidden;padding-top:20px"><s:property value="content.title"/></h3>
			<p style="font-size:0.875em;  text-align:center;margin:20px auto;overflow:hidden">
			[<s:property value="content.media"/>]
			<s:date name="content.ctime" format="yyyy-MM-dd" />
			</p>
			<div class="t_content" style="font-size:1em;  width:100%; margin:0px auto;">
				<s:property value="content.content" escape="false"/>
			</div>
		</div>
		
	</div>
</body>
</html>
