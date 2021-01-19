<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	// 数据挖掘分析
    var myChart = echarts.init(document.getElementById('columnchart'));
	var option = {
		title : {
			show:false,
			left:'center',
	        text: '栏目分析'
	    },
        // 默认色板
		color:chartcolor,	    
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
	        data: [<s:iterator value="list" status="st" id="lst">'<s:property value="#lst[1]"/>'<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    series : [
	        {
	            name: '栏目名称',
	            type: 'pie',
	            label:{
	            	normal:{
	            		position:'outside',
	            		formatter:"{b} :\n{c} ({d}%)"
	            	}
	            },
	            radius: ['30%','70%'],
	            center: ['55%', '50%'],
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

	//保存栏目名称与栏目ID的对应关系
	var columns = {<s:iterator value="list" status="st" id="lst">"<s:property value="#lst[1]"/>":"<s:property value="#lst[0]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>};
	var module  = "<s:property value="module"/>";
	myChart.on('click', function (params) {
		var columnid = columns[params.name];
		var url = "<s:url action="track/more"/>";
		if(module=="wb"){
			url = "<s:url action="weibo/more"/>";
		}else if(module=="wx"){
			url = "<s:url action="wechat/more"/>";
		}else if(module=="lt"){
			url = "<s:url action="bbs/more"/>";
		}
		window.open(url+"?column.id="+columnid);
	});
