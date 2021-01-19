<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('stockchart'));
	var option = {
		color:chartcolor,
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    grid: {
	    	top: '3%',
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        data: [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    barGap:'0%',
	    series: [
	        {
	            name: '博文',
	            type: 'bar',
	            barWidth: '%50',
	            data: [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        },
	        {
	            name: '用户',
	            type: 'bar',
	            barWidth: '%50',
	            data: [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[3]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }	        
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
