<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
        <h2><s:property value="myself.name"/>@<s:property value="mynetwork.name"/>历史数据</h2>
        <h3><s:property value="today" /></h3>
        <h4><s:property value="#column.title"/>:</h4>
        <ul>
            <li><span>总数：</span><s:property value="total"/></li>
            <li class="right">
            	<s:if test="#column.status==1">
            	黄色预警
            	</s:if>
            	<s:elseif test="#column.status==2">
            	橙色预警
            	</s:elseif>
            	<s:elseif test="#column.status==3">
            	红色预警
            	</s:elseif>
            	<s:else>
            	常规状态
            	</s:else>
            </li>
            <li><span>微博：</span><s:property value="wbcount"/></li>
            <li  class="right"><span>微信：</span><s:property value="wxcount"/></li>
            <li><span>新闻：</span><s:property value="xwcount"/></li>
            <li  class="right"><span>论坛：</span><s:property value="ltcount"/></li>
            <div class="clear"></div>
        </ul>
