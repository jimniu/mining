<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scake=1.0,user-scalable=no" />  
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="fromat-detecition" content="telephone=no" />
<title>首页</title>
<link href="../css/style_mobile.css?ord=<s:property value="version" />" rel="stylesheet" type="text/css" />
<script type="text/javascript">
  var COOKIEID = "<s:property value="cookieid" />";
  var MODULE   = "<s:property value="modulename" />";
  var PAGE     = "<s:property value="pagename" />";
  var PREFIX   = "<s:property value="prefix" />";
  var ERRMSG   = "";
</script>
</head>

<body class="index_bei">
<div class="index">
	<div class="logo">
		<!--  
    	<span class="img"><img src="../images/zhuangling.png"/></span>
    	-->
        <span class="text">鹰眼大数据挖掘平台</span>
        <div class="clear"></div>
    </div>
    <form action="javascript:;"  id="loginform">
    <div class="reg_text">
        <div class="yhm"><input type="text" name="user.name" value="账号"     data-value="帐号"/></div>
        <div class="yhm"><input type="text" name="user.name" value="公司名称" data-value="公司名称"/></div>
		<div class="yhm"><input type="text" name="user.name" value="手机"     data-value="手机"/></div>
		<div class="yhm"><input type="text" name="user.name" value="固话"     data-value="固话"/></div>
		<div class="yhm"><input type="text" name="user.name" value="邮箱"     data-value="邮箱"/></div>
        <div class="dl"><a href="javascript:;" id="loginbtn">注册</a></div>
    </div>
    </form>
    <!--  
    <div class="index_footer">联系电话：010-64473805</div>
    -->
	<div class="list">
        <div class="isd">
            <p>Powered By 鹰眼微信通</p>
            <p>010-64473805</p>
        </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/layer/layer.js"></script>
<script type="text/javascript" src="../js/base.js?ord=<s:property value="version" />"></script>
<script type="text/javascript" src="../js/mining/<s:property value="modulename" />.js?ord=<s:property value="version" />" charset="UTF-8"></script>
</html>


 