<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html >
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scake=1.0,user-scalable=no" />  
<meta name="apple-mobile-web-app-capable" content="yes" />
<meta name="fromat-detecition" content="telephone=no" />
<title>每日焦点</title>
<link href="../css/style_mobile.css?ord=<s:property value="version" />" rel="stylesheet" type="text/css" />
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
  var baseurl  = "<s:url action="mobile/focus"/>";
  var ERRMSG   = "";
</script>
</head>
<body class="list">
<div class="box">
	<div class="link">
		<div class="nav">
	    	<div class="nav_left">欢迎<span><span><s:property value="myself.name"/>@<s:property value="mynetwork.name"/></span></span>！</div>
	        <div class="nav_right">
	        	<a href="javascript:;">
	        		<img src="../images/nav_bt.jpg"/>
	        	</a>
	        </div>
	        <div class="clear"></div>
	    </div>
	    <div class="nav_list" style="display:none;">
	    	<p><a href="<s:url action="mobile/logout"/>">退出</a></p>
	    </div>
    </div>
	<div class="title">
    	<div class="left">
    		<a href="<s:url action="mobile/today"/>?days=0&module=<s:property value="module"/>&pageindex=0&pagesize=<s:property value="pagesize"/>">
    			<span class="img"><img src="../images/gx_bt.png"/></span>
    			<span>每日更新</span>
    		</a>
    	</div>
        <div class="right">
        	<a href="<s:url action="mobile/focus"/>?days=0&module=<s:property value="module"/>" class="vtd">
        		<span class="img"><img src="../images/jd_bt.png"/></span>
        		<span>每日焦点</span>
        	</a>
        </div>
        <div class="clear"></div>
    </div>
    <div class="fl">
    	<span class="tit">分类：</span>
        <span><a href="<s:url action="mobile/focus"/>?days=<s:property value="days"/>&module=wb" data-module="wb">微博</a></span>
        <span><a href="<s:url action="mobile/focus"/>?days=<s:property value="days"/>&module=xw" data-module="xw">媒体</a></span>
        <span><a href="<s:url action="mobile/focus"/>?days=<s:property value="days"/>&module=wx" data-module="wx">微信</a></span>
        <span><a href="<s:url action="mobile/focus"/>?days=<s:property value="days"/>&module=lt" data-module="lt">论坛</a></span>
        <div class="clear"></div>
    </div>
    <div class="list">
    	<div class="list_top">
        	<div class="left">Top20</div>
            <div class="right">
            	<s:if test="days==0">
            	<a href="<s:url action="mobile/focus"/>?days=2&module=<s:property value="module"/>">昨日数据</a>
            	</s:if>
            	<s:else>
            	<a href="<s:url action="mobile/focus"/>?days=0&module=<s:property value="module"/>">今日数据</a>
            	</s:else>
            	
            </div>
            <div class="clear"></div>
        </div>
	</div>
	<div id="main">
		<s:if test='module=="xw"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="<s:property value="#lst[1]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="list_one_name">[<s:property value="#lst[4]"/>]</div>
            <div class="list_one_ly">
            	<div class="left"><s:date name="#lst[2]" format="MM-dd HH:mm" /></div>
            	<div class="right">传播数：<a href="javascript:;" data-url="<s:url action="mobile/similar"/>?days=<s:property value="days"/>&module=xw&pageid=<s:property value="#lst[5]"/>"><s:property value="#lst[3]"/></a></div>
            	<div class="clear"></div>
            </div>
        </div>
        </s:iterator>
        </s:if>
		<s:if test='module=="wx"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="<s:property value="#lst[1]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="list_one_name">[<s:property value="#lst[4]"/>]</div>
            <div class="list_one_ly">
            	<div class="left"><s:date name="#lst[2]" format="MM-dd HH:mm" /></div>
            	<div class="right">传播数：<a href="javascript:;" data-url="<s:url action="mobile/similar"/>?days=<s:property value="days"/>&module=wx&pageid=<s:property value="#lst[5]"/>"><s:property value="#lst[3]"/></a></div>
            	<div class="clear"></div>
            </div>
        </div>
        </s:iterator>
        </s:if>    
		<s:if test='module=="wb"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="<s:property value="#lst[1]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="list_one_name">[<s:property value="#lst[4]"/>]</div>
            <div class="list_one_ly">
            	<div class="left"><s:date name="#lst[2]" format="MM-dd HH:mm" /></div>
            	<div class="right">传播数：<s:property value="#lst[3]"/></div>
            	<div class="clear"></div>
            </div>
        </div>
        </s:iterator>
        </s:if>   
		<s:if test='module=="lt"'>
		<s:iterator value="list" status="st" id="lst"> 
        <div class="list_one">
        	<div class="list_one_text">
        		<a href="<s:property value="#lst[1]"/>" target="_blank">
        			<s:property value="#lst[0]"/>
        		</a>
        	</div>
            <div class="list_one_name">[<s:property value="#lst[4]"/>]</div>
            <div class="list_one_ly">
            	<div class="left"><s:date name="#lst[2]" format="MM-dd HH:mm" /></div>
            	<div class="right">传播数：<a href="javascript:;" data-url="<s:url action="mobile/similar"/>?days=<s:property value="days"/>&module=lt&pageid=<s:property value="#lst[5]"/>"><s:property value="#lst[3]"/></a></div>
            	<div class="clear"></div>
            </div>
			
            </div>
        </div>
        </s:iterator>
        </s:if>
        <!--  
	    <div class="list">
	        <div class="isd">
	        	<p>Copyright&nbsp;&copy;&nbsp;2010 艾数达科技</p>
	            <p>010-64473805</p>
	        </div>
	    </div>
	    -->        
    </div>
</div>
</body>
<script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
<script type="text/javascript" src="../js/base.js?ord=<s:property value="version" />"></script>
<script type="text/javascript" src="../js/mining/<s:property value="modulename" />.js?ord=<s:property value="version" />" charset="UTF-8"></script>
</html>
