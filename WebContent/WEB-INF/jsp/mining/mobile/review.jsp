<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scake=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="fromat-detecition" content="telephone=no"/>
	<title><s:property value="#network.name"/></title>
    <link href="../css/style_mobile.css?ord=<s:property value="version" />" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
</head>
<body class="list">
<div class="box">
     <div>
        <img src="<s:property value="#setting.headimage"/>" width="100%"/>
     </div>

    <div id="main">
	
    </div>  
    <div class="gzh">
		<div class="tit">请持续关注</div>
		<div class="img"><img src="<s:property value="#setting.wxbarcode"/>"></div>
		<div class="name">微信号：<s:property value="#setting.wxaccount"/></div>
     </div>
  
    
    <div class="list">
        <div class="isd">
            <div class="left">Powered By  </div>
			<div class="right"><img src="../images/yywxt.png"></div>
            <div class="clear"></div>
        </div>
    </div>


</div>
 

<script type="text/javascript">
    pageindex = 0;

    function load(pageno){
		
        $.get("<s:url action="mobile/history"/>?recommend.networkid=<s:property value="recommend.networkid"/>&recommend.id=<s:property value="recommend.id"/>&pageindex="+pageno+"&ord="+new Date().getTime(),function(data){
            var content = $("#main").html();
		
			$("#main").html(content+data);
        })
    }

    load(pageindex);

    //判断页面滚动到顶部和底部
    $(window).scroll(function(){
        var doc_height = $(document).height();
        var scroll_top = $(document).scrollTop();
        var window_height = $(window).height();

        if(scroll_top == 0){
        }else if(scroll_top + window_height >= doc_height){
            pageindex ++;
            load(pageindex);
        }
    });

</script>

</body>
</html>


