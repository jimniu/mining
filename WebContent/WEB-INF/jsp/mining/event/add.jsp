<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>事件管理-新建事件</title>
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
                    <div class="wbwj_title">事件预警</div>
                    <form action="<s:url action="event/save"/>" method="post">
                    <div class="wbwj_sjfx_top">
                    	<h2>建立事件</h2>
                        <ul>
                            <li>
                                <span class="title">名称：</span>
                                <span class="text"><input name="column.title" type="text" data-value="事件的名称"></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">时间：</span>
                                <span class="text2"><input name="column.occurred" type="text" data-value="事件发生的时间"></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title"> 地点：</span>
                                <span class="text2">
                                	<input type="text" name="column.province" style="display:none;">
                                    <select id="province">
                                        <option>省份</option>
                                    </select>
                                </span>
                                <span class="text2">
                                    <select name="column.city" id="city">
                                        <option>市、区</option>
                                    </select>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">人物：</span>
                                <span class="text"><input name="column.role" type="text" data-value="事件涉及的人物"></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">关键词：</span>
                                <span class="text"><input type="text" name="column.keyword" data-value="多词用“,”“+”“-”隔开，分别代表“或”“与”“不包含”。最多<s:property value="mysetting.columnkeyword"/>组关键词，多余关键词将移除。" data-status="<s:property value="mysetting.keywordchange-column.modified"/>" data-cap="<s:property value="mysetting.columnkeyword"/>" data-total="<s:property value="mysetting.totalkeyword"/>"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">说明：</span>
                                <span class="text4"><textarea name="column.intro"></textarea></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">顺序：</span>
                                <span class="text"><input name="column.serno" type="text" value="999"></span>
                                <div class="clear"></div>
                            </li>                            
                            <li>
                            <span class="left"></span>
                            <span class="right">
                                <span class="tj">
                                	<input type="submit" id="submit" style="display:none;"/>
                                	<a href="javascript:;" id="btnSubmit">提交</a>
                                </span>
                                <span class="cz"><a href="<s:url action="event/list"/>">返回</a></span>
                                <div class="clear"></div>
                            </span>
                            </li>
                        </ul>
                    </div>
                    </form>
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
