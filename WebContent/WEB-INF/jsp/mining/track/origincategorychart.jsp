<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
	var myChart = echarts.init(document.getElementById('origincategorychart'));
	option = {
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
	        data: [<s:iterator value="catelist" status="st" id="lst">"<s:property value="#catehash[#lst[0]].name"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	
	  	calculable : false,    
	    series : [
	        {
	            name: '栏目占比',
	            type: 'pie',
				selectedMode: 'single',
            	radius : '80%',
				center: ['50%', '55%'],
				
	            // for funnel
	            x: '20%',
	            width: '40%',
	            funnelAlign: 'right',
	            max: 1548,
	            
	            itemStyle : {
	                normal : {
	                    label : {
	                        position : 'outer',
	                        formatter:"{b}:\n({d}%)"
	                    }
	                }
	            },	            
	            data:[
	            	<s:iterator value="catelist" status="st" id="lst">{name:'<s:property value="#catehash[#lst[0]].name"/>', value:<s:property value="#lst[1]"/>}<s:if test='!#st.last'>,</s:if></s:iterator>
	            ]
	        }
	    ]	 
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);