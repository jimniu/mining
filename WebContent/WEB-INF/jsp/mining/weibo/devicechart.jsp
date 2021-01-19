<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
var myChart = echarts.init(document.getElementById('devicechart'));

var option = {
    color:chartcolor,
    angleAxis: {
        type: 'category',
        data: [<s:iterator value="datalist" status="st" id="lst">"<s:property value="#lst[0]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>],
        z: 10
    },
    radiusAxis: {
    },
    polar: {
    },
    series : [
        {
            name:'转发者',
            type:'bar',
            coordinateSystem: 'polar',
            stack: 'a',
            data:[<s:iterator value="datalist" status="st" id="lst"><s:property value="#lst[1]"/><s:if test='!#st.last'>,</s:if></s:iterator>]
        }
    ]
};

myChart.setOption(option);
	
