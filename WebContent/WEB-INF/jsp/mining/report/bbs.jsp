<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<h2>
	论坛挖掘报表
</h2>
<div class="bbzx_list_one_width">
	<div class="bbzx_list_lt">
		<h3>
			<span></span>社区论坛名称
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					社区论坛总数：
					<span><s:property value="mediacount"/></span>个
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="40" nowrap="nowrap">
						序号
					</td>
					<td width="250" class="left" nowrap="nowrap">
						论坛名称
					</td>
					<td width="80" nowrap="nowrap">
						闪现次数
					</td>
					<td width="510" class="left">
						网站
					</td>
				</tr>
				<s:iterator value="medialist" status="st" id="lst">
				<tr>
					<td>
						<s:property value="#st.index+1" />
					</td>
					<td class="left">
						<s:property value="#lst[0]" />
					</td>
					<td>
						<s:property value="#lst[2]" />
					</td>
					<td class="left">
						<s:property value="#lst[1]" />
					</td>
				</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>帖子
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					帖子总数：
					<span><s:property value="contentcount"/></span>条
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
						论坛名称
					</td>
					<td class="left">
						帖子标题
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
				<div id="ltcolumnchart" style="width:870px; height:450px;margin-left:0px;"></div>
			</div>
		</div>
		<div class="bbzx_list_wx_sj">
			<h4>
				媒体关注分析
			</h4>
			<div class="pic">
				<div id="ltsitechart" style="width:870px; height:450px;margin-left:0px;"></div>
			</div>
		</div>
		<div class="bbzx_list_wx_sj">
			<h4>
				数据存量趋势分析
			</h4>
			<div class="pic">
				<div id="ltstockchart" style="width:870px; height:450px;margin-left:0px;"></div>
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	// 数据挖掘分析
    var columnchart = echarts.init(document.getElementById('ltcolumnchart'));
	var columnoption = {

	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
		color: chartcolor,	    
	    legend: {
	    	show:true,
	    	top:0,
	    	left:"center",
	        itemWidth:14,
	        itemHeight:14,
	        data: [<s:iterator value="columnreport" status="st" id="lst">'<s:property value="#lst[1]"/>'<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    series : [
	        {
	            name: '数量',
	            type: 'pie',
	            radius : ['40%', '73%'],
	            center: ['50%', '60%'],
	            label:{
	            	normal:{
	            		position:'outside',
	            		formatter:"{b} : {c}\n{d}%"
	            	}
	            },
	            data:[
	            	<s:iterator value="columnreport" status="st" id="lst">{value:<s:property value="#lst[2]"/>,name:'<s:property value="#lst[1]"/>'}<s:if test='!#st.last'>,</s:if></s:iterator>
	            ],
	            itemStyle: {
	                emphasis: {
	                    shadowBlur: 5,
	                    shadowOffsetX: 0,
	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
	                }
	            }
	        }
	    ]
	};
	// 使用刚指定的配置项和数据显示图表。
    columnchart.setOption(columnoption);

</script>

<script type="text/javascript">
	var sitechart = echarts.init(document.getElementById('ltsitechart'));
	
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
					<s:iterator value="ltcolumnlist" status="st" id="lst">
						<s:if test="title!=''">
							array.push("<s:property value="title"/>");
						</s:if>
					</s:iterator>
					return array;
		        }()    
		    },
			color:chartcolor,
		    grid: {
		        left: '25px',
		        right: '10px',
		        top: '70px',
		        height:'350px',
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
		    		'name':'<s:property value="#ltcolumnlist[#ost.index].title"/>',
		    		'data':[<s:iterator value="#outer" status="ist" id="inner"><s:property value="#inner[1]"/><s:if test='!#ist.last'>,</s:if></s:iterator>],
		    		'group':[<s:iterator value="#outer" status="ist" id="inner">'<s:property value="#inner[0]"/>'<s:if test='!#ist.last'>,</s:if></s:iterator>],
		    		'smooth':true,
		    		'type': 'line'
		    	}<s:if test='!#ost.last'> , </s:if>    	
				</s:iterator>
		    ]
		};
	
	sitechart.setOption(siteoption);
</script>

<script type="text/javascript">
	var stockchart = echarts.init(document.getElementById('ltstockchart'));
	
	var stockoption = {
		tooltip: {
			trigger: 'axis'
		},
	    legend: {
		    show:true,
		    top:0,
		    left:"center",
		    itemWidth:14,
		    itemHeight:14,    
	        data: function(){
		        var array = [];
				<s:iterator value="ltcolumnlist" status="st" id="lst">
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
	    xAxis : {
	        type: 'category',
	        axisLabel:{
	        	interval:'0',
	        	rotate:45
	        },
	        data: [<s:iterator value="timelist" status="st" id="lst">'<s:property value="#lst"/>'<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },
	    yAxis :{
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    series: [
	    	<s:iterator value="ltcolumnlist" status="outerst" id="outerlst">
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