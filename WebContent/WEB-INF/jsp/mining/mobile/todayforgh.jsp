<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scake=1.0,user-scalable=no" />  
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="fromat-detecition" content="telephone=no" />
<title>高和分享每日更新</title>
<link href="../css/style_mobile.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
  var COOKIEID = "<s:property value="cookieid" />";
  var MODULE   = "<s:property value="modulename" />";
  var PAGE     = "<s:property value="pagename" />";
  var PREFIX   = "<s:property value="prefix" />";
  var relation = "<s:property value="relation" />";
  var module   = "<s:property value="module" />";
  var pageindex= <s:property value="pageindex" />;
  var pagesize = <s:property value="pagesize" />;
  var days     = <s:property value="days" />;
  var baseurl  = "<s:url action="mobile/content"/>";
  var ERRMSG   = "";
</script>
</head>

<body class="list">
<div class="box">
	<div class="link">
		<div class="nav">
	    	<div class="nav_left">高和分享</div>
	        <div class="nav_right">
	        	<a href="javascript:;">
	        		<img src="../images/nav_bt.jpg"/>
	        	</a>
	        </div>
	        <div class="clear"></div>
	    </div>
	    <div class="nav_list" style="display:none;">
	    	<!--<p><a href="<s:url action="mobile/logout"/>">退出</a></p>-->
	    </div>
    </div>
	<div class="title">
    	<div class="left">
    		<a href="<s:url action="mobile/today"/>?days=<s:property value="days"/>&module=<s:property value="module"/>&pageindex=0&pagesize=<s:property value="pagesize"/>" class="vtd">
    			<span class="img"><img src="../images/gx_bt.png"/></span>
    			<span>每日更新</span>
    		</a>
    	</div>
        <div class="right">
        	<a href="<s:url action="mobile/focus"/>?days=<s:property value="days"/>&module=<s:property value="module"/>">
        		<span class="img"><img src="../images/jd_bt.png"/></span>
        		<span>每日焦点</span>
        	</a>
        </div>
        <div class="clear"></div>
    </div>
    <div class="fl_gh">
    	<span class="tit">分类：</span>
        <span><a href="javascript:;" data-module="wb">微博</a></span>
        <span><a href="javascript:;" data-module="xw" class="vtd">媒体</a></span>
        <span><a href="javascript:;" data-module="wx">微信</a></span>
        <span><a href="javascript:;" data-module="lt">论坛</a></span>
        <div class="clear"></div>
    </div>
    <div class="list">
        <div class="list_top">
        	<div class="left">
                <span><a href="javascript:;" data-relation="0" class="vtd">全部</a></span>
                <!--<span><a href="javascript:;" data-relation="1">自有</a></span>
                <span><a href="javascript:;" data-relation="2">竞品</a></span> -->
                <div class="clear"></div>
            </div>
            <div class="right">今日更新：<span id="total"></span></div>
            <div class="clear"></div>
        </div>
    </div>
    <div class="list" id="main">
        	<div class="jzgd"><a href="javascript:;">加载更多...</a></div>
    </div>
	<!--  
    <div class="list">
        <div class="isd">
        	<p>Copyright&nbsp;&copy;&nbsp;2010 艾数达科技</p>
            <p>010-64473805</p>
        </div>
    </div>
    -->
</div>
<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/base.js?ord=<s:property value="version" />"></script>
<script type="text/javascript" src="../js/mining/<s:property value="modulename" />.js?ord=<s:property value="version" />" charset="UTF-8"></script>
</body>
</html>
