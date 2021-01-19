<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%><%@taglib prefix="s" uri="/struts-tags"%><!DOCTYPE html>
<html>
<head lang="en">
    <link href="../css/style_chartview.css"   rel="stylesheet"   type="text/css"/>
    <title>可视数据_事件管理</title>
	<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery/jquery-ui.min.js"></script>	
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
            <li class="bt1"><a href="<s:url action="site/chartview"/>">数据挖掘</a></li>
            <li class="bt2"><a href="javascript:;"  class="bt2_vtd">事件管理</a></li>
            <!--<li class="bt3"><a href="<s:url action="site/negative"/>">负面管理</a></li>-->
        </ul>
    </div>
    <div class="excellent_sjgl">
        <h2>事件扩散TOP5省市：</h2>
        <ul id="top5">
            <li class="text1"></li>
            <li class="text2"></li>
            <li class="text3"></li>
            <li class="text4"></li>
            <li class="text5"></li>
        </ul>
    </div>
    <div class="kssj_pic" id="map" style="position:absolute;" data-url="<s:url action="site/eventmap"/>">
    </div>
    <div class="kssj_sjgl" data-url="<s:url action="site/eventsummary"/>" id="summary">
    </div>
    <div class="kssj_one">
        <div class="kssj_one_title">12个月内事件数据分析</div>
        <!--  
        <div class="kssj_one_link">
            <span class="text1">文章</span>
            <span class="text2">站点</span>
            <div class="clear"></div>
        </div>
        -->
        <div class="kssj_one_pic" >
        	<canvas id="chart"  width="306"  height="130"  style="margin-left:0px;" data-url="<s:url action="site/eventchart"/>"></canvas>
        </div>
    </div>
</div>
</body>
</html>
