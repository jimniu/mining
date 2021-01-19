<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('historybar'));
	var option = {
		color:chartcolor,	
	    tooltip : {
	        trigger: 'axis',
	        axisPointer : {            // 坐标轴指示器，坐标轴触发有效
	            type : 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
	        }
	    },
	    grid: {
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis : [
	        {
	            type : 'category',
	            data : [<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[0]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>],
	            axisTick: {
	                alignWithLabel: true
	            }
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series : [
	        {
	            name:'媒体/用户数量',
	            type:'line',
	            smooth:true,
	            barWidth: '%50',
	            data:[<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        },		    
	        {
	            name:'文章数量',
	            type:'bar',
	            barWidth: '%50',
	            data:[<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
        
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);