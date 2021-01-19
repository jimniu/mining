<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
 	<%@ include file="../layout/head.jsp"%>
    <title>报表中心-报表详情</title>
</head>
<body>
<div class="box">
	<%@ include file="../layout/top.jsp"%>
    <div class="width_box">
    	<div class="bbzx_list">
        	<div class="bbzx_list_width">
                <div class="bbzx_list_title">
                    <div class="bbzx_list_title_left"><s:property value="oem.title" />集成报表（<s:property value="range"/>的<s:property value="scope"/>内容）</div>
                    <div class="bbzx_list_title_right">
                        <span><a href="<s:url action="report/export"/>" target="_blank">生成PDF</a></span>
                        <span>报表生成时间：<s:property value="today"/></span>
                    </div>
                </div>
                <s:iterator value="module" status="st" id="lst">
                <div class="bbzx_list_one" data-url="../report/<s:property/>.shtml?days=<s:property value="days"/>&relation=<s:property value="relation"/>">
                	<img src="../images/working.gif" width="100"/>
                </div>
                </s:iterator>
            </div>
        </div>
    </div>
	<%@ include file="../layout/footer.jsp"%>
</div>
</body>
</html>