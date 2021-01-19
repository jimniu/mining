<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
    <title>事件管理-编辑事件</title>
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
                    	<h2>编辑事件</h2>
                        <ul>
                            <li>
                                <span class="title">名称：</span>
                                <span class="text"><input name="column.title" type="text" value="<s:property value="column.title"/>"></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">时间：</span>
                                <span class="text2"><input name="column.occurred" type="text"  value="<s:property value="column.occurred"/>"></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title"> 地点：</span>
                                <span class="text2">
                                	<input type="text" name="column.province" style="display:none;">
                                    <select id="province" data-value="<s:property value="column.province"/>">
                                        <option>省份</option>
                                    </select>
                                </span>
                                <span class="text2">
                                    <select name="column.city" id="city"  data-value="<s:property value="column.city"/>">
                                        <option>市、区</option>
                                    </select>
                                </span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">人物：</span>
                                <span class="text"><input name="column.role" type="text" value="<s:property value="column.role"/>"></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">关键词：</span>
                                <span class="text"><input name="column.keyword" value="<s:property value="column.keyword"/>"/></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">说明：</span>
                                <span class="text4"><textarea name="column.intro"><s:property value="column.intro"/></textarea></span>
                                <div class="clear"></div>
                            </li>
                            <li>
                                <span class="title">顺序：</span>
                                <span class="text"><input name="column.serno" type="text" value="<s:property value="column.serno"/>"></span>
                                <div class="clear"></div>
                            </li>                            
                            <li>
                            <span class="left"></span>
                            <span class="right">
                                <span class="tj">
                                	<input type="hidden" name="column.id" value="<s:property value="column.id"/>">
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
