<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
$.get('../js/echarts/china.json', function (chinaJson) {
    echarts.registerMap('china', chinaJson);
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('mapchart'));
	option = {
	    tooltip: {
	        trigger: 'item'
	    },
	    visualMap: {
	        min: 0,
	        max: 0<s:property value="#list[0][1]"/>,
	        left: 'left',
	        top: 'bottom',
	        text: ['高','低'],           // 文本，默认为数值文本
	        calculable: true
	    },
	    series: [
	        {
	            name: '微博用户数',
	            type: 'map',
	            mapType: 'china',
	            roam: false,
	            label: {
	                normal: {
	                    show: true
	                },
	                emphasis: {
	                    show: true
	                }
	            },
	            data:[
	            	<s:iterator value="list" status="st" id="lst">
	            	{name: '<s:property value="#hash[#lst[0]].name"/>',value: <s:property value="#lst[1]"/> }
	            	<s:if test='!#st.last'>,</s:if>
	            	</s:iterator>
	            ]
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
});
