<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	var data = {
		labels: [<s:iterator value="xwlist" status="st" id="lst">"<s:property value="#lst[0].substring(5)"/>"<s:if test='!#st.last'>,</s:if></s:iterator>],
		datasets: [
			{
				label: "",
				fillColor: "rgba(89,171,157,1)",
				strokeColor: "rgba(89,171,157,0.7)",
				highlightFill: "rgba(89,171,157,0.85)",
				highlightStroke: "rgba(89,171,157,1)",
				data: [<s:iterator value="wxlist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
			},				
			{
				label: "",
				fillColor: "rgba(255,66,0,1)",
				strokeColor: "rgba(255,66,0,0.7)",
				highlightFill: "rgba(255,66,0,0.85)",
				highlightStroke: "rgba(255,66,0,1)",
				data: [<s:iterator value="wblist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
			},
									{
				label: "",
				fillColor: "rgba(68,137,202,1)",
				strokeColor: "rgba(68,137,202,0.7)",
				highlightFill: "rgba(68,137,202,0.85)",
				highlightStroke: "rgba(68,137,202,1)",
				data: [<s:iterator value="xwlist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
			},				
			{
				label: "",
				fillColor: "rgba(174,93,160,1)",
				strokeColor: "rgba(174,93,160,0.7)",
				highlightFill: "rgba(174,93,160,0.85)",
				highlightStroke: "rgba(174,93,160,1)",
				data: [<s:iterator value="ltlist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
			}	
		]
	};	
