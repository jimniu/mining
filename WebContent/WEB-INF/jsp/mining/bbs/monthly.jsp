﻿<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	var data = {
		labels: [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[0]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>],
		datasets: [
			{
				label: "",
				fillColor: "rgba(174,93,160,1)",
				strokeColor: "rgba(174,93,160,0.7)",
				highlightFill: "rgba(174,93,160,0.85)",
				highlightStroke: "rgba(174,93,160,1)",
				data: [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
			}
		]
	};	
