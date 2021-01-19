<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	//历史挖掘数量分析
    var myChart = echarts.init(document.getElementById('historypie'));
	var option = {
		title : {
			show:false,
			left:'center',
	        text: '历史数据'
	    },
		color: chartcolor,		    
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
	    legend: {
	    	show:true,
	        orient: 'vertical',
	        top:50,
	        left: 'left',
	        itemWidth:14,
	        itemHeight:14,
	        data: ['微博', '微信', '新闻', '社区']
	    },	    
	    series : [
	        {
	            name: '历史数据',
	            type: 'pie',
	            label:{
	            	normal:{
	            		position:'outside',
	            		formatter:"{b}:\n{c}({d}%)"
	            	}
	            },
	            radius : '70%',
	            center: ['55%', '50%'],
	            data:[
	            	{name:'微博', value:<s:property value="wbhistory"/>},
	            	{name:'微信', value:<s:property value="wxhistory"/>},
	            	{name:'新闻', value:<s:property value="xwhistory"/>},
	            	{name:'社区', value:<s:property value="lthistory"/>}
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
