<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
var myChart = echarts.init(document.getElementById('stockchart'));

var option = {
    tooltip : {
        trigger: 'axis'
    },
    legend: {
	    show:true,
	    top:"top",
	    itemWidth:14,
	    itemHeight:14,    
        data: function(){
	        var array = [];
			<s:iterator value="columnlist" status="st" id="lst">
				<s:if test="title!=''">
					array.push("<s:property value="title"/>");
				</s:if>
			</s:iterator>
			return array;
        }()    
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
            data : [<s:iterator value="timelist" status="st" id="lst">"<s:property value="#lst"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
	series: [
		<s:iterator value="columnlist" status="outerst" id="outerlst">
		{
	    	name: '<s:property value="title"/>',
	        type: 'line',
	        smooth:true,
	        data: [<s:iterator value="timelist" status="innerst" id="innerlst">"<s:property value="#hashmap.get(#innerlst+'_'+#outerlst.id)"/>"<s:if test='!#innerst.last'>,</s:if></s:iterator>]
		}
	    <s:if test='!#outerst.last'>,</s:if>
	    </s:iterator>
	]
};

myChart.setOption(option);