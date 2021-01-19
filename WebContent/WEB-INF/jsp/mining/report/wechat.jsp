<%@page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<h2>
	微信挖掘报表
</h2>
<div class="bbzx_list_one_width">
	<div class="bbzx_list_wx">
		<h3>
			<span></span>微信公众号统计
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li>
					公众号数：
					<span><s:property value="wxtotal" />
					</span>个
				</li>
				<li>
					关键词闪现总数：
					<span><s:property value="contenttotal" />
					</span>次
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="40">
						序号
					</td>
					<td  class="left">
						公共号名称
					</td>
					<td width="80">
						关键词次数
					</td>
				</tr>
				<s:iterator value="wxlist" status="st" id="lst">
					<tr>
						<td>
							<s:property value="#st.index+1" />
						</td>
						<td>
							<span><s:property value="#lst[0].name" />
							</span>
						</td>
						<td>
							<s:property value="#lst[1]" />
						</td>
					</tr>
				</s:iterator>
			</table>
		</div>
		<h3>
			<span></span>微信文章报表
		</h3>
		<div class="bbzx_list_wx_num">
			<ul>
				<li class="width">
					总计数量：
					<span><s:property value="contenttotal" />
					</span>篇
				</li>
			</ul>
		</div>
		<div class="bbzx_list_wx_table">
			<table width="880" border="0" cellspacing="0" cellpadding="0">
				<tr class="title">
					<td width="40">
						序号
					</td>
					<td width="170" class="left">
						公众号名称
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
						<td>
							<!--  
							<span>
								<img src="../common/showimage.shtml?url=<s:property value="logo"/>" width="20" />
							</span>
							-->
							<span><s:property value="wxname" />
							</span>
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
			<div id="wxcolumnchart" style="width:878px;height:400px;">
			</div>
		</div>
		<div class="bbzx_list_wx_sj">
			<div id="wxsitechart" style="width:878px;height:480px;margin-left:0px;"></div>
		</div>
		<div class="bbzx_list_wx_sj">
			<div id="wxstockchart" style="width:878px;height:480px;margin-left:0px;">
			</div>
		</div>
	</div>
</div>

<script type="text/javascript">
	// 数据挖掘分析
    var columnchart = echarts.init(document.getElementById('wxcolumnchart'));
	var columnoption = {
		title : {
			show:true,
			left:'center',
	        text: '数据挖掘分析'
	    },
	    tooltip : {
	        trigger: 'item',
	        formatter: "{a} <br/>{b} : {c} ({d}%)"
	    },
		color:chartcolor,	    
	    legend: {
	    	show:true,
	        orient: 'vertical',
	        left: 'left',
	        itemWidth:14,
	        itemHeight:14,
	        data: [<s:iterator value="columnreport" status="st" id="lst">'<s:property value="#lst[1]"/>'<s:if test='!#st.last'> ,</s:if></s:iterator>]
	    },	    
	    series : [
	        {
	            name: '访问来源',
	            type: 'pie',
	            radius : '70%',
	            center: ['50%', '60%'],
	            data:[
	            	<s:iterator value="columnreport" status="st" id="lst">{value:<s:property value="#lst[2]"/>,name:'<s:property value="#lst[1]"/>'}<s:if test='!#st.last'> ,</s:if></s:iterator>
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
	//var color=["#fe7e00","#fa4b4b","#de0327","#b4bb31","#70bd65","#1e9498"];
	
    var sitechart = echarts.init(document.getElementById('wxsitechart'));
	var siteoption = {
		title : {
			show:true,
			x:'center',
	        text: '媒体关注分析'
	    },
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
		    top:"30",
		    left:"center",
		    itemWidth:14,
		    itemHeight:14,    
	        data: function(){
		        var array = [];
				<s:iterator value="wxcolumnlist" status="st" id="lst">
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
	    		'name':'<s:property value="#wxcolumnlist[#ost.index].title"/>',
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
</script>

<script type="text/javascript">
    var stockchart = echarts.init(document.getElementById('wxstockchart'));
	var stockoption = {
		title : {
			show:true,
			x:'center',
	        text: '数据存量趋势分析'
	    },
	    tooltip: {
	        trigger: 'axis',
	        axisPointer: {
	            animation: false
	        }
	    },
		color: chartcolor,	    
	    legend: {
	    	left:"center",
	    	top:"30",
	    	itemWidth:14,
	        itemHeight:14,
	        data: [<s:iterator value="wxcolumnlist" status="st" id="lst">'<s:property value="#lst.title"/>'<s:if test='!#st.last'> , </s:if></s:iterator>]
	    },
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
	        	rotate:45
	        },
	        data: [<s:iterator value="timelist" status="st" id="lst">'<s:property value="#lst"/>'<s:if test='!#st.last'>,</s:if></s:iterator>]
	    },	    
	    yAxis: {
	        type: 'value',
	        boundaryGap: [0, 0.01]
	    },
	    series: [
	    	<s:iterator value="wxcolumnlist" status="outerst" id="outerlst">
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
	// 使用刚指定的配置项和数据显示图表。
    stockchart.setOption(stockoption);
</script>
