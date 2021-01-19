<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
var myChart = echarts.init(document.getElementById('trendchart'));

var option = {
    tooltip : {
        trigger: 'axis'
    },
    legend: {
	    show:true,
	    top:"topright",
	    itemWidth:14,
	    itemHeight:14,    
        data: ['转发','评论','意见领袖']
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
            boundaryGap : false,
            data : [<s:iterator value="datalist" status="st" id="lst">"<s:property value="#lst[0]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
        {
            name:'转发',
            type:'line',
            smooth:true,
            data:[<s:iterator value="datalist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        },
        {
            name:'评论',
            type:'line',
            smooth:true,
            data:[<s:iterator value="datalist" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        },
        {
            name:'意见领袖',
            type:'scatter',
            smooth:true,
            data:[<s:iterator value="datalist" status="st" id="lst">"<s:property value="#viphash.get(#lst[0])"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        }
    ]
};

myChart.setOption(option);
	
