<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scake=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="fromat-detecition" content="telephone=no"/>
    <title>历史推荐</title>
    <meta name="description" content="历史推荐"/>
    <link href="../css/style_mobile.css?ord=<s:property value="version" />" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="http://res.wx.qq.com/open/js/jweixin-1.0.0.js"></script>
    <script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
</head>
<body class="list">
<div class="box">
     <div>
        <img src=src="../images/eagle_banner.jpg" width="100%"/>
     </div>
	 <div id="main">	
		<s:iterator value="list" status="st" id="lst">
			<div class="list_one">
				<div  class="list_one_text">
					<a href="<s:url action="mobile/recommend"/>?recommend.id=<s:property value="id"/>"><s:property value="title"/></a>
				</div>
				<div class="list_one_ly">
					<div class="left"><s:date name="ctime" format="YYYY-MM-dd HH:mm"/></div>
                    <div class="right"></div>
                    <div class="clear"></div>
				</div>
				<div class="clear"/>
			</div>
		</s:iterator>
	 </div>
     
     <div class="gzh">
		<div class="tit">请持续关注</div>
		<div class="img"><img src="<s:property value="#user.barcode"/>"></div>
		<div class="name">微信号：<s:property value="#user.weixin"/></div>
     </div>
  
    
    <div class="list">
        <div class="isd">
            <p>Powered By 鹰眼微信通</p>
            <!--<p>010-64473805</p>-->
        </div>
    </div>
</div>
</body>
</html>

