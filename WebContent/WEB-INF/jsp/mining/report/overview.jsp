<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<h2>
	报表总揽
</h2>
<div class="bbzx_list_one_width">
	<div class="bbzx_list_zl">
		<div class="bbzx_list_zl_left">
			<div class="one">
				<h3>
					微博数据报表
				</h3>
				<div id="wbchart" style="width: 395px;height:200px;margin-left:0px;">
				</div>
			</div>
			<div class="num">
				<div class="num_left">
					<span class="numb" id="wbusertotal">0</span>
					<span>用户数:</span>
				</div>
				<div class="num_right">
					<span>微博数:</span>
					<span class="numb" id="wbpagetotal">0</span>
				</div>
			</div>
		</div>
		<div class="bbzx_list_zl_right">
			<div class="one">
				<h3>
					微信数据报表
				</h3>
				<div id="wxchart" style="width: 395px;height:200px;margin-left:0px;"></div>
			</div>
			<div class="num">
				<div class="num_left">
					<span class="numb" id="wxusertotal">0</span>
					<span>微信公众号数:</span>
				</div>
				<div class="num_right">
					<span>微信文章数:</span>
					<span class="numb" id="wxpagetotal">0</span>
				</div>
			</div>
		</div>
	</div>
	<div class="bbzx_list_zl">
		<div class="bbzx_list_zl_left">
			<div class="one">
				<h3>
					媒体数据报表
				</h3>
				<div id="xwchart" style="width: 395px;height:200px;margin-left:0px;"></div>
			</div>
			<div class="num">
				<div class="num_left">
					<span class="numb" id="xwusertotal">0</span>
					<span>媒体数:</span>
				</div>
				<div class="num_right">
					<span>文章数:</span>
					<span class="numb" id="xwpagetotal">0</span>
				</div>
			</div>
		</div>
		<div class="bbzx_list_zl_right">
			<div class="one">
				<h3>
					论坛数据报表
				</h3>
				<div id="ltchart" style="width: 395px;height:200px;margin-left:0px;"></div>
			</div>
			<div class="num">
				<div class="num_left">
					<span class="numb" id="ltusertotal">0</span>
					<span>论坛数:</span>
				</div>
				<div class="num_right">
					<span>帖子数:</span>
					<span class="numb" id="ltpagetotal">0</span>
				</div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('wbchart'));
	var option = {
		color:chartcolor,
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	    	left:"left",
	    	itemWidth:14,
	        itemHeight:14,
	        data: ['用户', '博文']
	    },
	    grid: {
	        left: '10px',
	        right: '10px',
	        top: '40px',
	        height:'150px',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        data: [<s:iterator value="wblist" status="st" id="lst">"<s:property value="#lst[0].substring(5)"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    barGap:'0%',
	    series: [
	        {
	            name: '用户',
	            type: 'line',
	            data: [<s:iterator value="wblist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        },
	        {
	            name: '博文',
	            type: 'bar',
	            barWidth: '50%',
	            data: [<s:iterator value="wblist" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	//统计并显示总数
	var usercount = 0;
	var pagecount = 0;
	for(var i=0; i<option.series[0].data.length; i++){
		usercount += Number(option.series[0].data[i]);
		pagecount += Number(option.series[1].data[i]);
	}
	$("#wbusertotal").html(usercount);
	$("#wbpagetotal").html(pagecount);	    
</script>

<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
    var myChart = echarts.init(document.getElementById('wxchart'));
	var option = {
		color:chartcolor,
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	    	left:"left",
	        itemWidth:14,
	        itemHeight:14,	    	
	        data: ['公众号', '文章']
	    },
	    grid: {
	        left: '10px',
	        right: '10px',
	        top: '40px',
	        height:'150px',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        data: [<s:iterator value="wxlist" status="st" id="lst">"<s:property value="#lst[0].substring(5)"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    barGap:'0%',
	    series: [
	        {
	            name: '公众号',
	            type: 'line',
	            data: [<s:iterator value="wxlist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        },
	        {
	            name: '文章',
	            type: 'bar',
	            barWidth: '50%',
	            data: [<s:iterator value="wxlist" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	//统计并显示总数
	var usercount = 0;
	var pagecount = 0;
	for(var i=0; i<option.series[0].data.length; i++){
		usercount += Number(option.series[0].data[i]);
		pagecount += Number(option.series[1].data[i]);
	}
	$("#wxusertotal").html(usercount);
	$("#wxpagetotal").html(pagecount);	    
</script>

<script type="text/javascript">
	//新闻数据
    var myChart = echarts.init(document.getElementById('xwchart'));
	var option = {
		color:chartcolor,
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	    	left:"left",
	    	itemWidth:14,
	        itemHeight:14,
	        data: ['媒体', '文章']
	    },
	    grid: {
	        left: '10px',
	        right: '10px',
	        top: '40px',
	        height:'150px',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        data: [<s:iterator value="xwlist" status="st" id="lst">"<s:property value="#lst[0].substring(5)"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    barGap:'0%',
	    series: [
	        {
	            name: '媒体',
	            type: 'line',
	            data: [<s:iterator value="xwlist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        },
	        {
	            name: '文章',
	            type: 'bar',
	            barWidth: '50%',
	            data: [<s:iterator value="xwlist" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	//统计并显示总数
	var usercount = 0;
	var pagecount = 0;
	for(var i=0; i<option.series[0].data.length; i++){
		usercount += Number(option.series[0].data[i]);
		pagecount += Number(option.series[1].data[i]);
	}
	$("#xwusertotal").html(usercount);
	$("#xwpagetotal").html(pagecount);	    
</script>

<script type="text/javascript">
	// 论坛
    var myChart = echarts.init(document.getElementById('ltchart'));
	var option = {
		color:chartcolor,
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    legend: {
	    	left:"left",
	    	itemWidth:14,
	        itemHeight:14,
	        data: ['论坛', '帖子']
	    },
	    grid: {
	        left: '10px',
	        right: '10px',
	        top: '40px',
	        height:'150px',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        data: [<s:iterator value="ltlist" status="st" id="lst">"<s:property value="#lst[0].substring(5)"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    barGap:'0%',
	    series: [
	        {
	            name: '论坛',
	            type: 'line',
	            data: [<s:iterator value="ltlist" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        },
	        {
	            name: '帖子',
	            type: 'bar',
	            barWidth: '50%',
	            data: [<s:iterator value="ltlist" status="st" id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    myChart.setOption(option);
	//统计并显示总数
	var usercount = 0;
	var pagecount = 0;
	for(var i=0; i<option.series[0].data.length; i++){
		usercount += Number(option.series[0].data[i]);
		pagecount += Number(option.series[1].data[i]);
	}
	$("#ltusertotal").html(usercount);
	$("#ltpagetotal").html(pagecount);	    
</script>
