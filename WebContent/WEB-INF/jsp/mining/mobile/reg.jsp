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

<body class="index_reg">
<div class="index">
	<div class="reglogo">
		<!--  
    	<span class="img"><img src="../images/zhuangling.png"/></span>
    	-->
        <span class="text"><s:property value="oem.title" /></span>
        <div class="clear"></div>
    </div>
    <div id="regdiv" >
        <form action="javascript:;"  id="loginform">
        <div class="reg_text">
            <div class="yhm"><input type="text" name="tuser.name" data-value="帐号"/></div>
            <div class="yhm"><input type="text" name="tuser.company" data-value="公司名称"/></div>
            <div class="yhm"><input type="text" name="tuser.mobile" data-value="手机"/></div>
            <div class="yhm"><input type="text" name="tuser.tellphone" data-value="固话"/></div>
            <div class="yhm"><input type="text" name="tuser.email" data-value="邮箱"/></div>
            <div class="yhm"><input  type="text"  id="checkCode" data-value="验证码"/>
                <img border=0  width="10" src="<s:url action="user/getcode"/>"/>
            </div>
            <div class="dl"><a href="javascript:;" id="loginbtn">注册</a></div>
        </div>
        </form>
    </div>
    <div id="infodiv" class="regback">
       <p> 您的信息已经提交成功，稍后我们会联系您！</p>
	   <p> 010-64473805</p> 
    </div>
	
    <div class="isd2"><p>Powered by <s:property value="oem.title" />微信通</p></div>
	
    
	
</div>
</body>
<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/layer/layer.js"></script>
<script type="text/javascript" src="../js/base.js?ord=<s:property value="version" />"></script>
<script type="text/javascript" src="../js/mining/<s:property value="modulename" />.js?ord=<s:property value="version" />" charset="UTF-8"></script>
</html>


 