﻿<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
	<link href="../css/style_chartview.css"   rel="stylesheet"   type="text/css"/>
	<title>可视数据</title>
	<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/map/lib/raphael-min.js"></script>
	<script type="text/javascript" src="../js/map/res/chinaMapConfig.js"></script>
	<script type="text/javascript" src="../js/map/map.js"></script>
	<script type="text/javascript" src="../js/chart/Chart.min.js"></script>
	<script type="text/javascript">
	  var COOKIEID = "<s:property value="cookieid" />";
	  var MODULE   = "<s:property value="modulename" />";
	  var PAGE     = "<s:property value="pagename" />";
	  var PREFIX   = "<s:property value="prefix" />";
	  var ERRMSG   = "<s:property value="errmsg" />";
	</script>
	<script type="text/javascript" src="../js/base.js"></script>	
	<script type="text/javascript" src="../js/mining/site.js"></script>
</head>
<body>
	<div class="kssj_box">
	    <div class="kssj"></div>
	    <div class="kssj_list"></div>
	    <div class="logo">
		    <span>
			    <a href="<s:url action="site/index"/>">
		    		<img src="<s:property value="oem.logo" />"/>
		    	</a>
		    </span>
		    <span>
				<s:if test='oem.banner!=""&&oem.banner!=null'>
					<img src="<s:property value="oem.homebanner"/>" height="46" width="273"/>
				</s:if>
				<s:else>
					<img src="../images/logo_text1.png" height="46"/>
				</s:else>
			</span>
	    </div>
	    <div class="kssj_link">
	        <ul>
	            <li class="bt1"><a href="javascript:;"  class="bt1_vtd">数据挖掘</a></li>
	            <li class="bt2"><a href="<s:url action="site/event"/>">事件管理</a></li>
	            <!--  <li class="bt3"><a href="<s:url action="site/negative"/>">负面管理</a></li>-->
	        </ul>
    	</div>
	    <div class="hot_news">
	    	<marquee width="400" scrollamount=4 data-url="<s:url action="site/ticker"/>"></marquee>
	    </div>
	    <div class="du_pic" id="map" style="position:absolute;" data-url="<s:url action="site/map"/>"></div>
	    <div class="excellent">
	        <h2><img src="../images/y_bt.png"></h2>
	        <ul class="MapControl">
	        </ul>
	    </div>
	    <div class="inferior">
	        <h2><img src="../images/l_bt.png"></h2>
	        <ul class="MapControl">
	        </ul>
	    </div>
	    <div class="mobile" id="summary" data-url="<s:url action="site/summary"/>">
	    </div>
	    <div class="analysis">
	        <div class="analysis_one">
	            <div class="analysis_one_title">微博用户关注度分析</div>
	            <div class="analysis_one_link">
	                <span class="text1">博文</span>
	                <span class="text2">用户</span>
	                <div class="clear"></div>
	            </div>
	            <div class="analysis_one_pic">
	            	<canvas id="wbchart" width="306" height="130" style="margin-left:0px;" data-url="<s:url action="site/wbchart"/>"></canvas>
	            </div>
	        </div>
	        <div class="analysis_one">
	            <div class="analysis_one_title">微信关注度分析</div>
	            <div class="analysis_one_link">
	                <span class="text1">文章</span>
	                <span class="text2">公众号</span>
	                <div class="clear"></div>
	            </div>
	            <div class="analysis_one_pic">
	            	<canvas id="wxchart" width="306" height="130" style="margin-left:0px;" data-url="<s:url action="site/wxchart"/>"></canvas>
	            </div>
	        </div>
	        <div class="analysis_one">
	            <div class="analysis_one_title">新闻媒体关注度分析</div>
	            <div class="analysis_one_link">
	                <span class="text1">新闻</span>
	                <span class="text2">媒体</span>
	                <div class="clear"></div>
	            </div>
	            <div class="analysis_one_pic">
	            	<canvas id="xwchart" width="306" height="130" style="margin-left:0px;" data-url="<s:url action="site/xwchart"/>"></canvas>
	            </div>
	        </div>
	        <div class="analysis_one">
	            <div class="analysis_one_title">社区用户关注度分析</div>
	            <div class="analysis_one_link">
	                <span class="text1">帖子</span>
	                <span class="text2">论坛</span>
	                <div class="clear"></div>
	            </div>
	            <div class="analysis_one_pic">
	            	<canvas id="ltchart" width="306" height="130" style="margin-left:0px;" data-url="<s:url action="site/ltchart"/>"></canvas>
	            </div>
	        </div>
	        <div class="clear"></div>
	    </div>
	</div>
</body>
</html>
