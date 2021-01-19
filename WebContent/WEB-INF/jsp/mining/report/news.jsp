<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<h2>
	媒体挖掘报表
</h2>
<div class="bbzx_list_one_width">
	<div class="bbzx_list_hy">
		<h3>
			<span></span>媒体统计
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					媒体总数：
					<span><s:property value="mediacount"/></span>个
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="40">
						序号
					</td>
					<td width="250" class="left">
						媒体名称
					</td>
					<td width="80">
						地区
					</td>
					<td width="80">
						报道次数
					</td>
					<td class="left">
						网站
					</td>
				</tr>
				<s:iterator value="medialist" status="st" id="lst">
				<tr>
					<td>
						<s:property value="#st.index+1" />
					</td>
					<td class="left">
						<s:property value="#lst[0].name" />
					</td>
					<td>
						<s:property value="@com.isd.util.C@id2province(#lst[0].provinceid)" />
					</td>
					<td>
						<s:property value="#lst[1]" />
					</td>
					<td class="left">
						http://www.<s:property value="#lst[0].url" />
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>新闻文章
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					新闻文章总数：
					<span><s:property value="contentcount"/></span>篇
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="40">
						序号
					</td>
					<td width="250" class="left">
						媒体名称
					</td>
					<td class="left">
						文章标题
					</td>
					<td width="80">
						发布日期
					</td>
				</tr>
				<s:iterator value="contentlist" status="st" id="lst">
				<tr>
					<td>
						<s:property value="#st.index+1" />
					</td>
					<td class="left">
						<s:property value="sitename" />
					</td>
					<td class="left">
						<s:property value="title" />
					</td>
					<td>
						<s:date name="ptime" format="yyyy-MM-dd" />
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>数据分析
		</h3>
		<div class="bbzx_list_wx_sj">
			<h4>
				数据挖掘分析
			</h4>
			<div class="bt legend">
			</div>
			<div class="pic">
				<div id="xwcolumnchart" style="width:870px; height:450px;margin-left:0px;"></div>
			</div>
		</div>
		<div class="bbzx_list_wx_sj">
			<h4>
				媒体关注分析
			</h4>
			<div class="pic">
				<div id="xwsitechart" style="width:870px; height:450px;margin-left:0px;"></div>
			</div>
		</div>
		<div class="bbzx_list_wx_sj">
			<h4>
				数据存量趋势分析
			</h4>
			<div class="pic">
				<div id="xwstockchart" style="width:870px; height:450px;margin-left:0px;"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	// 基于准备好的dom，初始化echarts实例
    var columnchart = echarts.init(document.getElementById('xwcolumnchart'));
	var columnoption = {
		color:chartcolor,
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            type: 'shadow'
	        }
	    },
	    grid: {
	    	top: '3%',
	        left: '3%',
	        right: '4%',
	        bottom: '3%',
	        containLabel: true
	    },
	    xAxis: {
	        type: 'category',
	        data: [<s:iterator value="columnreport" status="st" id="lst">"<s:property value="#lst[1]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    barGap:'0%',
	    series: [
	        {
	            name: '文章数量',
	            type: 'bar',
	            barWidth: '%50',
	            data: [<s:iterator value="columnreport" status="st"  id="lst">"<s:property value="#lst[2]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        },
	        {
	            name: '媒体数量',
	            type: 'bar',
	            barWidth: '%50',
	            data: [<s:iterator value="columnreport" status="st"  id="lst">"<s:property value="#lst[3]"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    columnchart.setOption(columnoption);
</script>

<script type="text/javascript">
	var sitechart = echarts.init(document.getElementById('xwsitechart'));
	
	var siteoption = {
		    tooltip : {
		    	trigger : "axis",
				formatter: function(params) {  
		        	var myseries = siteoption.series;  
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
			    top:"0",
			    left:"center",
			    itemWidth:14,
			    itemHeight:14,    
		        data: function(){
			        var array = [];
					<s:iterator value="xwcolumnlist" status="st" id="lst">
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
		        top:"35",
		        containLabel: true
		    },
		    xAxis: {
		        type: 'category',
		        axisLabel:{
		        	interval:'0',
		        	rotate:0
		        },
		        data : [<s:iterator begin="1" end="30" status="st" id="lst">"<s:property value="#st.count"/>"<s:if test='!#st.last'>,</s:if></s:iterator>]
		    },	    
		    yAxis: {
		        type: 'value',
		        boundaryGap: [0, 0.01]
		    },
		    series : [
		    	<s:iterator value="sitereport" status="ost" id="outer">
		    	{
		    		'name':'<s:property value="#xwcolumnlist[#ost.index].title"/>',
		    		'data':[<s:iterator value="#outer" status="ist" id="inner"><s:property value="#inner[1]"/><s:if test='!#ist.last'>,</s:if></s:iterator>],
		    		'group':[<s:iterator value="#outer" status="ist" id="inner">'<s:property value="#inner[0]"/>'<s:if test='!#ist.last'>,</s:if></s:iterator>],
		    		'smooth':true,
		    		'type': 'line'
		    	}<s:if test='!#ost.last'> , </s:if>    	
				</s:iterator>
		    ]
		};
		// 使用刚指定的配置项和数据显示图表。
	    sitechart.setOption(siteoption);
	
	sitechart.setOption(siteoption);

</script>

<script type="text/javascript">
	var stockchart = echarts.init(document.getElementById('xwstockchart'));
	
	var stockoption = {
	    tooltip : {
	        trigger: 'axis'
	    },
	    legend: {
		    show:true,
		    left:"center",
		    itemWidth:14,
		    itemHeight:14,    
	        data: function(){
		        var array = [];
				<s:iterator value="xwcolumnlist" status="st" id="lst">
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
	            data : [<s:iterator value="timelist" status="st" id="lst">"<s:property value="#lst"/>"<s:if test='!#st.last'> , </s:if></s:iterator>]
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value'
	        }
	    ],
	    series: [
	    	<s:iterator value="xwcolumnlist" status="outerst" id="outerlst">
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
	
	stockchart.setOption(stockoption);
</script>