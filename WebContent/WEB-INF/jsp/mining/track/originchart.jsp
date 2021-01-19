<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	//历史挖掘数量分析
    var myChart = echarts.init(document.getElementById('originchart'));
	var option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
		color:chartcolor,		    
	    legend: {
	    	show:true,
	        orient: 'vertical',
	        top:50,
	        left: 'left',
	        itemWidth:14,
	        itemHeight:14,
	        data:["媒体转发","媒体原发"]
	    },

	    calculable : false,
 
	    series : [
	        {
	            name:'内容来源',
	            type:'pie',
	            label:{
	            	normal:{
	            		position:'outside',
	            		formatter:"{b}:\n({d}%)"
	            	}
	            },	            
	            radius : ['40%','80%'],
	            center: ['50%', '50%'],
	            data:[
	                {value:<s:property value="#list[0][1]"/>, name:'媒体转发'},
	                {value:<s:property value="#list[1][1]"/>, name:'媒体原发'}
	            ]
	        }
	    ]
	};
 
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);