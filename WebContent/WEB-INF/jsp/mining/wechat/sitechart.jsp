<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
var myChart = echarts.init(document.getElementById('sitechart'));

var option = {
    tooltip : {
    	trigger : "axis",
		formatter: function(params) {  
        	var myseries = option.series;  
            var res= "";
            for(i = 0; i < params.length; i++){
            	res += params[i].marker;
                res += myseries[params[i].seriesIndex].group[params[i].dataIndex]+"： "+myseries[params[i].seriesIndex].data[params[i].dataIndex]+"条<br/>";
			}
			return res;  
		}
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
            data : [<s:iterator begin="1" end="count" status="st" id="lst">"<s:property value="#st.count"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
        }
    ],
    yAxis : [
        {
            type : 'value'
        }
    ],
    series : [
    	<s:iterator value="datalist" status="ost" id="outer">
    	{
    		name:"<s:property value="#columnlist[#ost.index].title"/>",
    		group:[<s:iterator value="#outer" status="ist" id="inner">"<s:property value="#inner[0]"/>"<s:if test='!#ist.last'>,</s:if></s:iterator>],
    		data:[<s:iterator value="#outer" status="ist" id="inner"><s:property value="#inner[1]"/><s:if test='!#ist.last'>,</s:if></s:iterator>],
    		smooth:true,
    		type: 'line'
    	}<s:if test='!#ost.last'>,</s:if>    	
		</s:iterator>
    ]
};

myChart.setOption(option);
	


