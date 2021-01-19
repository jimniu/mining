<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html >
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width,inital-scale=1.0,minimum-scale=1.0,maximum-scake=1.0,user-scalable=no"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="fromat-detecition" content="telephone=no"/>
    <title><s:property value="#user.name"/>推荐:<s:property value="recommend.title"/></title>
    <meta name="description" content="<s:property value="recommend.summary"/>"/>
    <link href="../css/style_mobile.css?ord=<s:property value="version" />" rel="stylesheet" type="text/css"/>
    <script type="text/javascript" src="../js/jquery/jquery-1.8.3.min.js"></script>
</head>
<body class="list">
<div class="box">
    <s:if test="recommend==null">
        <div class="err">
            客官，您来晚了，要访问的推荐已经没影了！
        </div>
    </s:if>
    <s:else>

        <s:if test='recommend.clkurl==null||recommend.clkurl.indexOf("http")==-1'>
            <div>
                <img src="<s:property value="recommend.imgurl"/>" width="100%"/>
            </div>
            <div class="recomm">
                <div class="tit"><s:property value="recommend.title"/></div>
                <div class="summary"><s:property value="recommend.summary"/></div>
                <div class="clear"></div>
            </div>
        </s:if>
        <s:else>
            <div>
                <a href="<s:property value="recommend.clkurl"/>">
                    <img src="<s:property value="recommend.imgurl"/>" width="100%"/>
                </a>
            </div>
            <div class="recomm">
                <div class="tit"><a href="<s:property value="recommend.clkurl"/>"><s:property value="recommend.title"/></a></div>
                <div class="summary"><a href="<s:property value="recommend.clkurl"/>"><s:property value="recommend.summary"/></a></div>
                <div class="clear"></div>
            </div>
        </s:else>

        <div id="main">
            <s:iterator value="list" status="st" id="lst">
                <div class="list_one">
                    <div class="list_one_text">
                        <a href="<s:property value="url"/>" target="_blank">
                            <s:property value="title"/>
                        </a>
                    </div>
                    <div class="list_one_name">
                        <s:if test="siteimg!=null">
                    		<a href="<s:property value="siteurl"/>"><img src="<s:property value="siteimg"/>" width="30"></a>&nbsp;&nbsp;
                    	</s:if>
                    	<s:property value="sitename"/>
                    </div>
                    <div class="list_one_ly">
                        <div class="left">
                        	<s:date name="ptime" format="YYYY-MM-dd HH:mm"/>
                        	<s:if test='module=="wx"'>
                        	微信
                        	</s:if>
                        	<s:elseif test='module=="wb"'>
                        	微博
                        	</s:elseif>
                        	<s:elseif test='module=="xw"'>
                        	媒体
                        	</s:elseif>
                        </div>
                        <div class="right"></div>
                        <div class="clear"></div>
                    </div>
                    <s:if test='comment!=null&&comment!=""'>
                    <div class="list_one_text" style="font-size: 0.875em;color: #8b8a89;">
                    	【推荐语】：<br/>
                    	<s:property value="comment"/>
                    </div>
                    </s:if>
                </div>
            </s:iterator>
        </div>
    </s:else>

    <s:if test="recommend!=null">
        <div class="gzh">
            <div class="tit">请持续关注</div>
            <div class="img"><img src="<s:property value="mysetting.wxbarcode"/>"></div>
            <div class="name">微信号：<s:property value="mysetting.wxaccount"/></div>
        </div>
    </s:if>
    <s:if test="recommend.reward==1">
        <div class="shang">
            <div class="img"><img src="../images/shang.png"></div>
            <div class="tit">共456人打赏</div>
        </div>
    </s:if>
    <s:if test="recommend!=null">
        <div class="tj">
            <span class="tit">阅读：<s:property value="recommend.readno"/></span>
            <span class="tit"><a href="javascript:;" data-praise="<s:property value="recommend.id"/>">点赞</a>：<span
                    id="praise"><s:property value="recommend.praise"/></span></span>
        </div>
    </s:if>
    <div class="list">
        <div class="isd">
            <p>Powered By <s:property value="oem.title" />微信通</p>
            <!--<p>010-64473805</p>-->
        </div>
    </div>
</div>

</body>
</html>


