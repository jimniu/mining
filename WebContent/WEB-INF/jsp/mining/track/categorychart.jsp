<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	//历史挖掘数量分析
    var myChart = echarts.init(document.getElementById('categorychart'));
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
	        data: [<s:iterator value="catelist" status="st" id="lst">"<s:property value="#catehash[#lst[0]].name"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	
	    calculable : false,    
	    series : [
	        {
	            name: '栏目占比',
	            type: 'pie',
				selectedMode: 'single',
            	radius : [0, 100],
				center: ['60%', '55%'],
				
	            // for funnel
	            x: '20%',
	            width: '40%',
	            funnelAlign: 'right',
	            max: 1548,
	            
	            itemStyle : {
	                normal : {
	                    label : {
	                        position : 'inner',
	                        formatter:"{b}:\n({d}%)"
	                    },
	                    labelLine : {
	                        show : false
	                    }
	                }
	            },	            
	            data:[
	            	<s:iterator value="columnlist" status="st" id="lst">{name:'<s:property value="#columnhash[#lst[0]].title"/>', value:<s:property value="#lst[1]"/>}<s:if test='!#st.last'>,</s:if></s:iterator>
	            ]
	        },
	        {
	            name:'访问来源',
	            type:'pie',
	            radius : [130, 200],
	            center: ['60%', '55%'],
	            
	            // for funnel
	            x: '60%',
	            width: '35%',
	            funnelAlign: 'left',
	            max: 1048,
	            label:{
	            	normal:{
	            		position:'outside',
	            		formatter:"{b}:\n({d}%)"
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