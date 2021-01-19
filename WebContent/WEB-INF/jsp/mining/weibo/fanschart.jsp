<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
var myChart = echarts.init(document.getElementById('fanschart'));

var option = {
    tooltip : {
        trigger: 'axis'
    },
    legend: {
	    show:true,
	    top:"topright",
	    itemWidth:14,
	    itemHeight:14,    
        data: ['转发者','评论者']
    },
	color:chartcolor,	    
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis : [
        {
            type : 'category',
            data : [<s:iterator value="datalist" status="st" id="lst">"<s:property value="#rangehash.get(#lst[0])"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'转发者',
            type:'bar',
            smooth:true,
            data:[<s:iterator value="datalist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        },
        {
            name:'评论者',
            type:'bar',
            smooth:true,
            data:[<s:iterator value="datalist" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        }
    ]
};

myChart.setOption(option);
	
