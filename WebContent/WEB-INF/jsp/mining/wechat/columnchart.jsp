<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	// 数据挖掘分析
    var myChart = echarts.init(document.getElementById('columnchart'));
	var option = {
		title : {
			show:false,
			left:'center',
	        text: '数据挖掘分析'
	    },
		color:chartcolor,		    
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	show:true,
	        top:"top",
	        itemWidth:14,
	        itemHeight:14,	        
	        data: [<s:iterator value="list" status="st" id="lst">'<s:property value="#lst[1]"/>'<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    series : [
	        {
	            name: '数量',
	            type: 'pie',
	            radius : '73%',
	            center: ['50%', '60%'],
	            label:{
	            	normal:{
	            		position:'outside',
	            		formatter:"{b} : {c}\n{d}%"
	            	}
	            },
	            data:[
	            	<s:iterator value="list" status="st" id="lst">{value:<s:property value="#lst[2]"/>,name:'<s:property value="#lst[1]"/>'}<s:if test='!#st.last'>,</s:if></s:iterator>
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 5,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	$("#columnreport span.from").html("<s:property value="from"/>");
	$("#columnreport span.to").html("<s:property value="to"/>");
