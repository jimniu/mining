<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>关于我们</title>
    <%@ include file="../layout/head.jsp"%>
</head>
<body>
<div class="box">
    <%@ include file="../layout/top.jsp"%>
    <div class="width_box">
        <%@ include file="../layout/message.jsp"%>
        <div class="list">
            <div class="list_top"><img src="../images/list_top.jpg"/></div>
            <div class="list_center">
                <div class="list_left">
                    <div class="yhxx_left">
                        <ul>
                            <li><a href="<s:url action="site/about"/>" class="yhxx_left_vtd">关于我们</a></li>
                            <li><a href="<s:url action="site/problem"/>">常见问题</a></li>
                            <li><a href="<s:url action="site/joinus"/>">加入我们</a></li>
                        </ul>
                    </div>
                </div>
                <div class="list_right">
                    <div class="list_right_title">关于我们</div>
                    <div class="gywm_list">
                        <div class="gywm_pic"><img src="../images/gywm_pic.jpg"/></div>
                        <div class="gywm_text">
                            <p><s:property value="oem.title" />是一个SaaS大数据软件平台以互联网全网为数据基础，透过艾数达科技大数据挖掘引擎核心技术进行数据挖掘与数据分析，为企业提供精准的互联网目标大数据收集、大数据整合和大数据分析。同时帮助企业利用数据进行市场调研、品牌监测分析、产品监测分析、市场销售数据监测分析、事件营销活动数据监测、媒体传播监测分析和用户互动数据营销。</p>
                        </div>
                    </div>
                </div>
                <div class="clear"></div>
            </div>
            <div class="list_top"><img src="../images/list_bottom.jpg"/></div>
        </div>
    </div>
    <%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>
