<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>事件管理-事件详情</title>
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
                <%@ include file="../layout/left.jsp"%>
                <div class="list_right">
                    <%@ include file="guide.jsp"%>
                    <div class="wbwj_dsj_box">
                    	<div class="wbwj_dsj_box">
                        <div class="wbwj_title">事件详情</div>
                        <div class="sjgl_sjcl">
                            <div class="sjgl_sjcl_one">
                                <div class="sjcl_one_center_xq">
                                	<h2><s:property value="column.title"/></h2>
                                    <ul>
										<li>
                                        	<span class="left">影响：</span>
                                            <span class="right">微博数量：<span class="color"><s:property value="wbcount"/></span></span>
                                            <div class="clear"></div>
                                        </li>
                                    	<li>微信数量：<span class="color"><s:property value="wxcount"/></span></li>
                                    	<li>
                                        	<span class="left">&nbsp;</span>
                                            <span class="right">新闻数量：<span class="color"><s:property value="xwcount"/></span></span>
                                            <div class="clear"></div>
                                        </li>
                                    	<li>论坛数量：<span class="color"><s:property value="ltcount"/></span></li>
                                        <li>
                                            <span class="left">时间：</span>
                                            <span class="right"><s:property value="column.occurred"/></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li>
                                            <span class="left">地点：</span>
                                            <span class="right"><s:property value="column.province"/><s:property value="column.city"/></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li class="text">
                                            <span class="left">人物：</span>
                                            <span class="right"><s:property value="column.role"/></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li class="text">
                                            <span class="left">关键词：</span>
                                            <span class="right"><s:property value="column.keyword"/></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li class="text">
                                            <span class="left">说明：</span>
                                            <span class="right"><s:property value="column.intro"/></span>
                                            <div class="clear"></div>
                                        </li>
                                        <li class="text" id="trenddiv">
                                            <span class="left">分析：</span>
                                            <span class="right">
                                            	<select name="column.title" data-id="<s:property value="column.id"/>">
					                            	<option value="wb">微博</option>
					                            	<option value="wx">微信</option>
					                                <option value="xw">新闻</option>
												</select>
                                                <div class="clear"></div>
                                            </span>
                                            <div class="clear"></div>
                                        </li>
                                        <li class="text" style="width:730px" >
                                        	<div id="trendchart" style="width:730px; height:550px; margin-left:0px;"></div>
                                        </li>
                                        <div class="clear"></div>
                                    </ul>
                                </div>
                                <div class="clear"></div>
                            </div>
                        </div>
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
