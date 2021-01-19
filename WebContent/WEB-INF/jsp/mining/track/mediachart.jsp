<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

	//媒体数量分析
    var myChart = echarts.init(document.getElementById('mediachart'));
	var option = {
	    tooltip : {
	        trigger: 'item',
	        formatter: "{b} <br/>{a} : {c}"
	    },
		color: chartcolor,		    
	    legend: {
	    	show:true,
	        top:10,
	        itemWidth:14,
	        itemHeight:14,
	        data: [<s:iterator value="catelist" status="st" id="lst">"<s:property value="#catehash[#lst[0]].name"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	
	    calculable : true,   
	  	xAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    yAxis : [
	        {
	            type : 'category',
	            data : [<s:iterator value="datelist" status="st" id="lst">"<s:property/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
	    ],

	    series : [
			<s:iterator value="catelist" status="st" id="lst">
			{
				name:'<s:property value="#catehash[#catelist[#st.index][0]].name"/>',
	            type:'bar',
	            barWidth: '%50',
	            stack: '总量',
	            itemStyle : { normal: {label : {show: true, position: 'insideRight'}}},
	            data:[
	            	<s:iterator value="datelist" status="stin" id="lstin">	            	
	            	<s:property value="#datahash[#lstin+#lst[0]]"/>	            	
	            	<s:if test='!#stin.last'>,</s:if>
	            	</s:iterator>
	            ]
			}
			<s:if test='!#st.last'>,</s:if>
			</s:iterator>
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);